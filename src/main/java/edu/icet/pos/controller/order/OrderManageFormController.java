package edu.icet.pos.controller.order;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.pos.controller.employee.EmployeeController;
import edu.icet.pos.db.DBConnection;
import edu.icet.pos.dto.*;
import edu.icet.pos.dto.tm.OrderCartTable;
import edu.icet.pos.dto.tm.OrderTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderManageFormController implements Initializable {
    public Label lblOrderID;
    public JFXButton btnPlaceOrder;
    public JFXComboBox cmbEmployeeID;
    public JFXTextField txtName;
    public JFXComboBox cmbPaymentType;
    public JFXComboBox cmbProductID;
    public JFXButton btnSearchProductID;
    public JFXTextField txtPurchasedItem;
    public JFXComboBox cmbSize;
    public JFXTextField txtQuantityOnHand;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtDiscount;
    public TextField txtBoughtQuantity;
    public JFXButton btnAddToCart;
    public JFXButton btnUpdate;
    public JFXButton btnClearRow;
    public Label lblOrderDate;
    public TableView tblOrderCart;
    public TableColumn colProductID;
    public TableColumn colItem;
    public TableColumn colSize;
    public TableColumn colQTY;
    public TableColumn colUnitPrice;
    public TableColumn colDiscount;
    public TableColumn colSubTotal;
    public Label lblTotalItems;
    public Label lblNetTotal;
    public JFXButton btnPrintBill;
    public JFXComboBox cmbOrderID;
    public JFXButton btnSearchOrderID;
    public JFXButton btnUpdateOrder;
    public JFXButton btnDeleteOrder;
    public TableView tblOrderDetails;
    public TableColumn colOrderID;
    public TableColumn colEmployeeID;
    public TableColumn colOperator;
    public TableColumn colTotalItems;
    public TableColumn colNetTotal;
    public TableColumn colPaymentType;
    public TableColumn colOrderDate;
    public JFXButton btnPrintReport;

    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    ObservableList<OrderCartTable> orderCartList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colProductID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        colItem.setCellValueFactory(new PropertyValueFactory<>("item"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));

        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        colOperator.setCellValueFactory(new PropertyValueFactory<>("operator"));
        colTotalItems.setCellValueFactory(new PropertyValueFactory<>("totalItems"));
        colNetTotal.setCellValueFactory(new PropertyValueFactory<>("netTotal"));
        colPaymentType.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

        generateOrderID();
        loadEmployeeIDDropMenu();
        loadPaymentTypeDropMenu();
        loadProductIDDropMenu();
        loadSizeDropMenu();
        loadOrderIDDropMenu();
        loadOrderTable();

        cmbOrderID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            setOrderData((String) newValue);
        });

        lblOrderDate.setText(dateFormat.format(date));
    }

    public void generateOrderID() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM orders");
            Integer count = 0;
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            if (count == 0) {
                lblOrderID.setText("OR0001");
            }
            String lastOrderID = "";
            ResultSet resultSet1 = connection.createStatement().executeQuery("SELECT OrderID\n" +
                    "FROM orders\n" +
                    "ORDER BY orderID DESC\n" +
                    "LIMIT 1;");
            if (resultSet1.next()) {
                lastOrderID = resultSet1.getString(1);
                Pattern pattern = Pattern.compile("[A-Za-z](\\d+)");
                Matcher matcher = pattern.matcher(lastOrderID);
                if (matcher.find()) {
                    int number = Integer.parseInt(matcher.group(1));
                    number++;
                    lblOrderID.setText(String.format("OR%04d", number));
                } else {
                    new Alert(Alert.AlertType.WARNING,"hello").show();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadEmployeeIDDropMenu() {
        ObservableList<Employee> allEmployees = EmployeeController.getInstance().getAllEmployeeUsers();
        ObservableList<Object> objects = FXCollections.observableArrayList();
        allEmployees.forEach(employee -> {
            objects.add(employee.getEmployeeID());
        });
        System.out.println(objects);
        cmbEmployeeID.setItems(objects);
    }

    public void loadPaymentTypeDropMenu() {
        ObservableList<Object> list = FXCollections.observableArrayList();
        list.add(new String("CASH"));
        list.add(new String("CREDIT CARD"));
        list.add(new String("DEBIT CARD"));
        list.add(new String("LOYALTY CARD"));
        list.add(new String("CHEQUE"));
        list.add(new String("GIFT VOUCHER"));
        cmbPaymentType.setItems(list);
    }

    private void loadProductIDDropMenu() {
//        ObservableList<Product> allProducts = ProductController.getInstance().getAllProducts();
//        ObservableList<Object> objects = FXCollections.observableArrayList();
//        allProducts.forEach(product -> {
//            objects.add(product.getProductID());
//        });
//        System.out.println(objects);
//        cmbProductID.setItems(objects);
    }

    public void loadSizeDropMenu() {
        ObservableList<Object> list = FXCollections.observableArrayList();
        list.add(new String("XXS"));
        list.add(new String("XS"));
        list.add(new String("S"));
        list.add(new String("M"));
        list.add(new String("L"));
        list.add(new String("XL"));
        list.add(new String("2XL"));
        list.add(new String("3XL"));
        list.add(new String("4XL"));
        list.add(new String("5XL"));
        list.add(new String("6XL"));
        cmbSize.setItems(list);
    }

    private void loadOrderIDDropMenu() {
        ObservableList<Order> allOrders = OrderController.getInstance().getAllOrders();
        ObservableList<Object> objects = FXCollections.observableArrayList();
        allOrders.forEach(orders -> {
            objects.add(orders.getOrderID());
        });
        System.out.println(objects);
        cmbOrderID.setItems(objects);
    }

    private void loadOrderTable() {
        ObservableList<OrderTable> orderTable = FXCollections.observableArrayList();
        ObservableList<Order> allOrders = OrderController.getInstance().getAllOrders();

        allOrders.forEach(orders -> {
            orderTable.add(
                    new OrderTable(
                            orders.getOrderID(),
                            orders.getEmployeeID(),
                            orders.getOperator(),
                            orders.getTotalItems(),
                            orders.getNetTotal(),
                            orders.getPaymentType(),
                            orders.getOrderDate()
                    )
            );
        });
        tblOrderDetails.setItems(orderTable);
    }

    private void setOrderData(String orderID) {
        Order orders = OrderController.getInstance().searchOrder(orderID);
        System.out.println(orders);

        cmbEmployeeID.setValue(orders.getEmployeeID());
        txtName.setText(orders.getOperator());
        cmbPaymentType.setValue(orders.getPaymentType());
        lblOrderDate.setText(orders.getOrderDate().toString());
        lblTotalItems.setText(String.valueOf(orders.getTotalItems()));
        lblNetTotal.setText(String.valueOf(orders.getNetTotal()));
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        try {
            String orderID = lblOrderID.getText();
            String employeeID = cmbEmployeeID.getValue().toString();
            String operator = txtName.getText();
            Integer totalItems = Integer.parseInt(lblTotalItems.getText());
            Double netTotal = Double.valueOf(lblNetTotal.getText());
            String paymentType = cmbPaymentType.getValue().toString();
            Date orderDate = dateFormat.parse(lblOrderDate.getText());

            List<OrderDetail> orderDetailList = new ArrayList<>();

            for (OrderCartTable orderCartTable : orderCartList) {
                String cartOrderID = lblOrderID.getText();
                String productID = orderCartTable.getProductID();
                String item = orderCartTable.getItem();
                String size = orderCartTable.getSize();
                Integer qty = orderCartTable.getQty();
                Double unitPrice = orderCartTable.getUnitPrice();
                Double discount = orderCartTable.getDiscount();
                Double subTotal = orderCartTable.getSubTotal();

                orderDetailList.add(new OrderDetail(cartOrderID, productID, item, size, qty, unitPrice, discount, subTotal));
            }

            Order order = new Order(orderID, employeeID, operator, orderDetailList, totalItems, netTotal, paymentType, orderDate);
            Boolean isOrderPlace = OrderController.getInstance().placeOrder(order);
            if (isOrderPlace) {
                generateOrderID();
                new Alert(Alert.AlertType.INFORMATION, "Order Placed!").show();
            }
            System.out.println(order);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSearchProductIDOnAction(ActionEvent actionEvent) {
    }

    public void txtAddToCartOnAction(ActionEvent actionEvent) {
        btnAddToCartOnAction(actionEvent);
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        String productID = (String) cmbProductID.getValue();
        String item = txtPurchasedItem.getText();
        String size = (String) cmbSize.getValue();
        Integer boughtQty = Integer.parseInt(txtBoughtQuantity.getText());
        Double unitPrice = Double.valueOf(txtUnitPrice.getText());
        Double discount = Double.valueOf(txtDiscount.getText());
        Double subTotal = (boughtQty * unitPrice) - ((boughtQty * unitPrice) * discount / 100);

        OrderCartTable orderCartTable = new OrderCartTable(productID, item, size, boughtQty, unitPrice, discount, subTotal);
        System.out.println(orderCartTable);

//        int stockQty = Integer.parseInt(txtQuantityOnHand.getText());
//        if (stockQty < boughtQty) {
//            new Alert(Alert.AlertType.WARNING, "Not enough quantity available in the stock!").show();
//            return;
//        }

        orderCartList.add(orderCartTable);
        tblOrderCart.setItems(orderCartList);
        calculateNetTotal();
        calculateTotalItems();
    }

    public void calculateTotalItems() {
        int totalItems = 0;
        for (OrderCartTable cartObj : orderCartList) {
            totalItems += cartObj.getQty();
        }
        lblTotalItems.setText(String.valueOf(totalItems));
    }

    public void calculateNetTotal() {
        double netTotal = 0;
        for (OrderCartTable cartObj : orderCartList) {
            netTotal += cartObj.getSubTotal();
        }
        lblNetTotal.setText(String.valueOf(netTotal));
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnClearRowOnAction(ActionEvent actionEvent) {
    }

    public void btnPrintBillOnAction(ActionEvent actionEvent) throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/todos/1");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        System.out.println(responseCode);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = bufferedReader.readLine()) != null) {
            response.append(inputLine);
        }
        bufferedReader.close();
        System.out.println(response);

        Gson gson = new Gson();
        Sample sample = gson.fromJson(response.toString(), Sample.class);
        System.out.println(sample);
    }

    public void btnSearchOrderIDOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOrderOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOrderOnAction(ActionEvent actionEvent) {
    }

    public void btnPrintReportOnAction(ActionEvent actionEvent) {
    }
}