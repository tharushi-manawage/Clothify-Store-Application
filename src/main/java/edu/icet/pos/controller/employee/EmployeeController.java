package edu.icet.pos.controller.employee;

import edu.icet.pos.dto.Employee;
import edu.icet.pos.dto.User;
import edu.icet.pos.util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeController implements EmployeeService {
    private static EmployeeController instance;

    private EmployeeController() {}

    public static EmployeeController getInstance() {
        if (instance == null) {
            return instance = new EmployeeController();
        }
        return instance;
    }

    public ObservableList<Employee> getAllEmployeeUsers() {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM employee");
            ObservableList<Employee> employeeTableList = FXCollections.observableArrayList();
            while (resultSet.next()) {
                employeeTableList.add(
                        new Employee(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getDate(6).toLocalDate(),
                                resultSet.getString(7)
                        )
                );
            }
            return employeeTableList;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User searchEmployeeUser(String userId) {
        try {
            PreparedStatement pStm = CrudUtil.execute("SELECT * FROM user WHERE userID = ?");
            pStm.setString(1, userId);
            boolean execute = pStm.execute();
            if (execute) {
                ResultSet resultSet = pStm.getResultSet();
                while (resultSet.next()) {
                    return new User(
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
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean addEmployeeUser(Employee employee) {
        try {
            String SQL = "INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?, ?)";
            CrudUtil.execute(
                    SQL,
                    employee.getEmployeeID(),
                    employee.getUserID(),
                    employee.getName(),
                    employee.getEmail(),
                    employee.getContactNumber(),
                    employee.getAppointedDate(),
                    employee.getEmployeeStatus()
            );
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}