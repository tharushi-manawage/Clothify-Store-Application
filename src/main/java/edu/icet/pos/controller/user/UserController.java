package edu.icet.pos.controller.user;

import edu.icet.pos.db.DBConnection;
import edu.icet.pos.dto.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class UserController {
    private static UserController instance;

    private UserController() {}

    public static UserController getInstance() {
        if (instance == null) {
            return instance = new UserController();
        }
        return instance;
    }

    public ObservableList<User> getAllUsers() {
        try {
            ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM user");
            ObservableList<User> userTableList = FXCollections.observableArrayList();
            while (resultSet.next()) {
                userTableList.add(
                        new User(
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
                        )
                );
            }
            return userTableList;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User searchUser(String userID) {
        try {
            PreparedStatement pStm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM user WHERE userID = ?");
            pStm.setString(1, userID);
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
}