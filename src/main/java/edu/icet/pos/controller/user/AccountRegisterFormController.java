package edu.icet.pos.controller.user;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.icet.pos.bo.BoFactory;
import edu.icet.pos.bo.custom.UserBo;
import edu.icet.pos.db.DBConnection;
import edu.icet.pos.dto.User;
import edu.icet.pos.util.BoType;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AccountRegisterFormController implements Initializable {
    public Label lblUserID;
    public JFXComboBox cmbUserType;
    public JFXTextField txtName;
    public DatePicker dateDateOfBirth;
    public JFXTextField txtEmailAddress;
    public JFXPasswordField txtPassword;
    public Label lblRegisteredDate;
    public JFXTextField txtNICNumber;
    public JFXTextField txtCity;
    public JFXComboBox cmbProvince;
    public JFXTextField txtContactNumber1;
    public JFXTextField txtContactNumber2;
    public JFXTextField txtUserStatus;
    public JFXButton btnUpdate;
    public JFXButton btnSubmit;

    private List<User> userList;

    private UserBo userBo = BoFactory.getInstance().getBo(BoType.USER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUserTypeDropMenu();
        loadRegisteredDate();
        loadProvinceDropMenu();
        //loadEmployees();
    }

    private void loadRegisteredDate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        lblRegisteredDate.setText(dateFormat.format(date));

//        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
//            LocalTime time = LocalTime.now();
//            lblTime.setText(time.getHour() + " : " + time.getMinute() + " : " + time.getSecond());
//        }),
//                new KeyFrame(Duration.seconds(1))
//        );
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();
    }

//    private void loadEmployees() {
//        userList = new ArrayList<>();
//
//        try {
//            ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM user");
//            while (resultSet.next()) {
//                User user = new User(
//                        resultSet.getString(1),
//                        resultSet.getString(2),
//                        resultSet.getString(3),
//                        resultSet.getDate(4),
//                        resultSet.getString(5),
//                        resultSet.getString(6),
//                        resultSet.getDate(7),
//                        resultSet.getString(8),
//                        resultSet.getString(9),
//                        resultSet.getString(10),
//                        resultSet.getString(11),
//                        resultSet.getString(12),
//                        resultSet.getString(13)
//                );
//
//                System.out.println(user);
//                userList.add(user);
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    private void loadUserTypeDropMenu() {
        ObservableList<Object> list = FXCollections.observableArrayList();
        list.add(new String("ADMIN"));
        list.add(new String("MALE"));
        list.add(new String("FEMALE"));
        cmbUserType.setItems(list);
    }

    private void loadProvinceDropMenu() {
        ObservableList<Object> list = FXCollections.observableArrayList();
        list.add(new String("NORTHERN"));
        list.add(new String("NORTH WESTERN"));
        list.add(new String("WESTERN"));
        list.add(new String("NORTH CENTRAL"));
        list.add(new String("CENTRAL"));
        list.add(new String("SABARAGAMUWA"));
        list.add(new String("EASTERN"));
        list.add(new String("UVA"));
        list.add(new String("SOUTHERN"));
        cmbProvince.setItems(list);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnSubmitOnAction(ActionEvent actionEvent) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        Date datee = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        //dateJoinedDate.setText(f.format(datee));
        Date d = null;

        try {
            date = format.parse(dateDateOfBirth.getValue().toString());


           //d = f.parse(dateJoinedDate.setText(f.format(datee)));


            User user = new User(
                    lblUserID.getText(),
                    cmbUserType.getValue().toString(),
                    txtName.getText(),
                    dateDateOfBirth.getValue(),
                    txtEmailAddress.getText(),
                    txtPassword.getText(),
                    lblRegisteredDate.getText(),
                    txtNICNumber.getText(),
                    txtCity.getText(),
                    cmbProvince.getValue().toString(),
                    txtContactNumber1.getText(),
                    txtContactNumber2.getText(),
                    txtUserStatus.getText()
            );

            System.out.println(user);

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pStm = connection.prepareStatement("INSERT INTO user VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pStm.setString(1,user.getUserID());
            pStm.setString(2,user.getUserType());
            pStm.setString(3,user.getName());
            pStm.setObject(4,user.getDob());
            pStm.setString(5,user.getEmail());
            pStm.setString(6,user.getPassword());
            pStm.setObject(7,user.getRegisteredDate());
            pStm.setString(8,user.getNicNumber());
            pStm.setString(9,user.getCity());
            pStm.setString(10,user.getProvince());
            pStm.setString(11,user.getContactNumber1());
            pStm.setString(12,user.getContactNumber2());
            pStm.setString(13,user.getUserStatus());

            pStm.execute();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        //boolean b = UserController.getInstance().addUser(user);
            boolean b = userBo.saveUser(user);
            System.out.println(b);
            if(b) {
                new Alert(Alert.AlertType.ERROR,"User Not Added!").show();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION,"User Added!").show();
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}