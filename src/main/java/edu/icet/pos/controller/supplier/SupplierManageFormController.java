package edu.icet.pos.controller.supplier;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class SupplierManageFormController {
    public Text txtSupplierID;
    public JFXButton btnRegisterSupplier;
    public JFXTextField txtSupplierName;
    public JFXTextField txtEmailAddress;
    public JFXTextField txtCompany;
    public JFXTextField txtContactNumber;
    public JFXTextField txtSupplyingProduct;
    public JFXComboBox cmbSize;
    public JFXTextField txtDeliveredQuantity;
    public JFXTextField txtJoinedDate;
    public JFXComboBox cmbSupplierID;
    public JFXButton btnSearch;
    public JFXButton btnUpdateSupplier;
    public JFXButton btnDeleteSupplier;
    public TableView tblSupplierDetails;
    public TableColumn colSupplierID;
    public TableColumn colName;
    public TableColumn colEmail;
    public TableColumn colCompany;
    public TableColumn colContactNumber;
    public TableColumn colProduct;
    public TableColumn colSize;
    public TableColumn colQTY;
    public TableColumn colJoinedDate;
    public JFXButton btnPrint;

    public void btnRegisterSupplierOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateSupplierOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteSupplierOnAction(ActionEvent actionEvent) {
    }

    public void btnPrintOnAction(ActionEvent actionEvent) {

    }
}
