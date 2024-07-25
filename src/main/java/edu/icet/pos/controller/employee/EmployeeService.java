package edu.icet.pos.controller.employee;

import edu.icet.pos.dto.Employee;
import edu.icet.pos.dto.User;
import javafx.collections.ObservableList;

public interface EmployeeService {
    ObservableList<Employee> getAllEmployeeUsers();
    User searchEmployeeUser(String userId);
    boolean addEmployeeUser(Employee employee);
}