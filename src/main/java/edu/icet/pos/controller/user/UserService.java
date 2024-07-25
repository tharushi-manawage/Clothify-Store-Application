package edu.icet.pos.controller.user;

import edu.icet.pos.dto.User;
import javafx.collections.ObservableList;

public interface UserService {
    ObservableList<User> getAllUsers();
    User searchUser(String userId);
    boolean addUser(User user);
}