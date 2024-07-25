package edu.icet.pos.controller.employee;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.icet.pos.controller.user.UserController;
import edu.icet.pos.db.DBConnection;
import edu.icet.pos.dto.Employee;
import edu.icet.pos.dto.User;
import edu.icet.pos.dto.tm.EmployeeTable01;
import edu.icet.pos.dto.tm.EmployeeTable02;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.*;
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

public class EmployeeManageFormController implements Initializable {
    public Label lblEmployeeID;
    public JFXButton btnRegisterEmployee;
    public JFXComboBox cmbUserID;
    public JFXButton btnSearch;
    public JFXTextField txtName;
    public JFXTextField txtEmailAddress;
    public JFXTextField dateAppointedDate;
    public JFXComboBox cmbStatus;
    public JFXButton btnUpdateEmployee;
    public JFXButton btnDeleteEmployee;
    public TableView tblEmployeeDetails1;
    public TableColumn colEmployeeID;
    public TableColumn colUserIDTbl1;
    public TableColumn colName;
    public TableColumn colEmail;
    public TableColumn colContactNumber1;
    public TableColumn colAppointedDate;
    public TableColumn colEmployeeStatus;
    public TableView tblEmployeeDetails2;
    public TableColumn colUserIDTbl2;
    public TableColumn colUserType;
    public TableColumn colDateOfBirth;
    public TableColumn colNICNumber;
    public TableColumn colCity;
    public TableColumn colProvince;
    public TableColumn colContactNumber2;
    public TableColumn colUserStatus;
    public JFXButton btnPrint;

    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private List<User> userList;
    private List<Employee> employeeList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        colUserIDTbl1.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContactNumber1.setCellValueFactory(new PropertyValueFactory<>("contactNumber1"));
        colAppointedDate.setCellValueFactory(new PropertyValueFactory<>("appointedDate"));
        colEmployeeStatus.setCellValueFactory(new PropertyValueFactory<>("employeeStatus"));

        colUserIDTbl2.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colUserType.setCellValueFactory(new PropertyValueFactory<>("userType"));
        colDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colNICNumber.setCellValueFactory(new PropertyValueFactory<>("nicNumber"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colContactNumber2.setCellValueFactory(new PropertyValueFactory<>("contactNumber2"));
        colUserStatus.setCellValueFactory(new PropertyValueFactory<>("userStatus"));

        generateEmployeeID();
        loadUserIDDropMenu();
        loadStatusDropMenu();
        loadEmployees();
        loadEmployeeTable01();
        loadEmployeeTable02();

        cmbUserID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            setUserData((String) newValue);
        });

        dateAppointedDate.setText(dateFormat.format(date));
    }

    public void generateEmployeeID() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM employee");
            Integer count = 0;
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            if (count == 0) {
                lblEmployeeID.setText("EM0001");
            }
            String lastEmployeeID = "";
            ResultSet resultSet1 = connection.createStatement().executeQuery("SELECT EmployeeID\n" +
                    "FROM employee\n" +
                    "ORDER BY EmployeeID DESC\n" +
                    "LIMIT 1;");
            if (resultSet1.next()) {
                lastEmployeeID = resultSet1.getString(1);
                Pattern pattern = Pattern.compile("[A-Za-z](\\d+)");
                Matcher matcher = pattern.matcher(lastEmployeeID);
                if (matcher.find()) {
                    int number = Integer.parseInt(matcher.group(1));
                    number++;
                    lblEmployeeID.setText(String.format("EM%04d", number));
                } else {
                    new Alert(Alert.AlertType.WARNING,"Not generated Employee ID found!").show();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadUserIDDropMenu() {
        ObservableList<User> allUsers = UserController.getInstance().getAllUsers();
        ObservableList<Object> objects = FXCollections.observableArrayList();
        allUsers.forEach(user -> {
            objects.add(user.getUserID());
        });
        System.out.println(objects);
        cmbUserID.setItems(objects);
    }

    private void loadStatusDropMenu() {
        ObservableList<Object> list = FXCollections.observableArrayList();
        list.add(new String("ACTIVE"));
        list.add(new String("INACTIVE"));
        cmbStatus.setItems(list);
    }

    private void loadEmployees() {
        userList = new ArrayList<>();

        try {
            ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM user");
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4).toLocalDate(),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getDate(7).toLocalDate(),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getString(12),
                        resultSet.getString(13)
                );

                System.out.println(user);
                userList.add(user);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        employeeList = new ArrayList<>();

        try {
            ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM employee");
            while (resultSet.next()) {
                Employee employee = new Employee(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDate(6).toLocalDate(),
                        resultSet.getString(7)
                );

                System.out.println(employee);
                employeeList.add(employee);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadEmployeeTable01() {
        ObservableList<EmployeeTable01> employeeTable01 = FXCollections.observableArrayList();
        ObservableList<Employee> allEmployees = EmployeeController.getInstance().getAllEmployeeUsers();

        allEmployees.forEach(employee -> {
            employeeTable01.add(
                    new EmployeeTable01(
                        employee.getEmployeeID(),
                        employee.getUserID(),
                        employee.getName(),
                        employee.getEmail(),
                        employee.getContactNumber(),
                        employee.getAppointedDate(),
                        employee.getEmployeeStatus()
                    )
            );
        });
        tblEmployeeDetails1.setItems(employeeTable01);
    }

    private void loadEmployeeTable02() {
        ObservableList<EmployeeTable02> employeeTable02 = FXCollections.observableArrayList();
        ObservableList<User> allUsers = UserController.getInstance().getAllUsers();

        allUsers.forEach(user -> {
            employeeTable02.add(
                    new EmployeeTable02(
                        user.getUserID(),
                        user.getUserType(),
                        user.getDob(),
                        user.getNicNumber(),
                        user.getCity(),
                        user.getProvince(),
                        user.getContactNumber2(),
                        user.getUserStatus()
                    )
            );
        });
        tblEmployeeDetails2.setItems(employeeTable02);
    }

    private void setUserData(String userID) {
        User user = UserController.getInstance().searchUser(userID);
        System.out.println(user);

        txtName.setText(user.getName());
        txtEmailAddress.setText(user.getEmail());
        dateAppointedDate.setText(dateFormat.format(date));;
        cmbStatus.setValue(user.getUserStatus());
    }

    private void clearText() {
        cmbUserID.setValue(null);
        txtName.setText(null);
        txtEmailAddress.setText(null);
        dateAppointedDate.setText(null);
        cmbStatus.setValue(null);
    }

    public void btnRegisterEmployeeOnAction(ActionEvent actionEvent) {
        try {
            Employee employee = new Employee(
                    lblEmployeeID.getText(),
                    cmbUserID.getValue().toString(),
                    txtName.getText(),
                    txtEmailAddress.getText(),
                    txtEmailAddress.getText(),
                    dateFormat.parse(dateAppointedDate.getText()),
                    cmbStatus.getValue().toString()
            );

            boolean isAdded = EmployeeController.getInstance().addEmployeeUser(employee);
            System.out.println(isAdded);
            if (isAdded) {
                new Alert(Alert.AlertType.ERROR, "Employee not appointed!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee appointed!").show();
            }

            loadEmployeeTable01();
            loadEmployeeTable02();
            clearText();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //Date date = null;

//        Date date = new Date();
//        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//        dateAppointedDate.setText(f.format(date));
//
//        //Employee.txtContactNumber1.getText();
//
//        //date = dateAppointedDate.getText();
//
//        Employee employee = new Employee(
//                lblEmployeeID.getText(),
//                cmbUserID.getValue().toString(),
//                txtName.getText(),
//                txtEmailAddress.getText(),
//                txtEmailAddress.getText(),
//                dateAppointedDate.getText(),
//                cmbStatus.getValue().toString()
//        );
//
//        System.out.println(employee);

//        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pStm = connection.prepareStatement("INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?, ?)");
//            pStm.setString(1, employee.getEmployeeID());
//            pStm.setString(2, employee.getUserID());
//            pStm.setString(3, employee.getName());
//            pStm.setString(4, employee.getEmail());
//            pStm.setString(5, employee.getContactNumber());
//            pStm.setObject(6, employee.getAppointedDate());
//            pStm.setString(7, employee.getEmployeeStatus());
//
//            pStm.execute();
//
//            loadEmployees();
//            loadEmployeeTable01();
//            loadEmployeeTable02();
//            clearText();
//        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        User user = UserController.getInstance().searchUser(cmbUserID.getValue().toString());
        //System.out.println(user);

        setUserData(user.getUserID());
    }

    public void btnUpdateEmployeeOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteEmployeeOnAction(ActionEvent actionEvent) {
        try {
            boolean execute = DBConnection.getInstance().getConnection().createStatement().execute("DELETE FROM user WHERE userID = '" + cmbUserID.getValue().toString() + "'");

            loadEmployees();
            loadEmployeeTable01();
            loadEmployeeTable02();
            clearText();

            if (execute) {
                System.out.println("Employee Not Deleted!");
            } else {
                System.out.println("Employee Deleted!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnPrintOnAction(ActionEvent actionEvent) {
    }
}