package edu.icet.pos.controller.order;

import edu.icet.pos.controller.product.InventoryController;
import edu.icet.pos.db.DBConnection;
import edu.icet.pos.dto.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class OrderController {
    private static OrderController instance;

    private OrderController() {}

    public static OrderController getInstance() {
        if (instance == null) {
            return instance = new OrderController();
        }
        return instance;
    }

    public ObservableList<Order> getAllOrders() {
        try {
            ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM orders");
            ObservableList<Order> orderTableList = FXCollections.observableArrayList();
            while (resultSet.next()) {
                orderTableList.add(
                        new Order(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getInt(4),
                                resultSet.getDouble(5),
                                resultSet.getString(6),
                                resultSet.getDate(7)//.toLocalDate()
                        )
                );
            }
            return orderTableList;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean placeOrder(Order order) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement pStm = connection.prepareStatement("INSERT INTO orders VALUES(?, ?, ?, ?, ?, ?, ?)");
            pStm.setString(1, order.getOrderID());
            pStm.setString(2, order.getEmployeeID());
            pStm.setString(3, order.getOperator());
            pStm.setInt(4, order.getTotalItems());
            pStm.setDouble(5, order.getNetTotal());
            pStm.setString(6,order.getPaymentType());
            pStm.setDate(7, (Date) order.getOrderDate());
            boolean isOrderAdded = pStm.executeUpdate() > 0;

            if (!isOrderAdded) {
                boolean isOrderDetail = OrderDetailController.getInstance().addOrderDetail(order.getOrderDetailList());
                if (isOrderDetail) {
                    boolean isStockUpdated = InventoryController.getInstance().updateStock(order.getOrderDetailList());
                    if (isStockUpdated) {

                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
    }

//    public Order searchOrder(String orderID) {
//        try {
//            PreparedStatement pStm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM orders WHERE orderID = ?");
//            pStm.setString(1,orderID);
//            boolean execute = pStm.execute();
//
//            if (execute) {
//                ResultSet resultSet = pStm.getResultSet();
//
//                while (resultSet.next()) {
//                    return new Order(
//                            resultSet.getString(1),
//                            resultSet.getString(2),
//                            resultSet.getString(3),
//                            resultSet.getInt(4),
//                            resultSet.getDouble(5),
//                            resultSet.getString(6),
//                            resultSet.getDate(4)//.toLocalDate()
//                    );
//                }
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
}
