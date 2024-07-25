package edu.icet.pos.controller.order;

import edu.icet.pos.dto.OrderDetail;
import edu.icet.pos.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailController {
    private static OrderDetailController instance;

    private OrderDetailController() {}

    public static OrderDetailController getInstance() {
        if (instance == null) {
            return  instance = new OrderDetailController();
        }
        return instance;
    }

    public boolean addOrderDetail(List<OrderDetail> orderDetailList) {
        boolean isAdded = false;
        for (OrderDetail orderDetail : orderDetailList) {
            isAdded = addOrderDetail(orderDetail);
            if (isAdded) {
                return true;
            }
        }
        return false;
    }

    public boolean addOrderDetail(OrderDetail orderDetail) {
        try {
            Object isAdded = CrudUtil.execute(
                    "INSERT INTO orderDetail VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                    orderDetail.getOrderID(),
                    orderDetail.getProductID(),
                    orderDetail.getItem(),
                    orderDetail.getSize(),
                    orderDetail.getQty(),
                    orderDetail.getUnitPrice(),
                    orderDetail.getDiscount(),
                    orderDetail.getSubTotal()
            );
            return (Boolean) isAdded;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}