package edu.icet.pos.controller.product;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class InventoryManageFormController {
    public Text txtProductID;
    public JFXButton btnAddProduct;
    public JFXComboBox cmbSupplierID;
    public JFXComboBox cmbCategory;
    public JFXComboBox cmbProductCode;
    public JFXTextField txtProductName;
    public JFXComboBox cmbSize;
    public JFXTextField txtPrice;
    public JFXTextField txtQuantityOnHand;
    public JFXTextField txtAddedDate;
    public JFXComboBox cmbProductID;
    public JFXButton btnSearch;
    public JFXButton btnUpdateProduct;
    public JFXButton btnDeleteProduct;
    public TableView tblInventoryDetails;
    public TableColumn colProductID;
    public TableColumn colSupplierID;
    public TableColumn colCategory;
    public TableColumn colCode;
    public TableColumn colProductName;
    public TableColumn colSize;
    public TableColumn colPrice;
    public TableColumn colQTY;
    public TableColumn colAddedDate;
    public JFXButton btnPrint;

    public void btnAddProductOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateProductOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteProductOnAction(ActionEvent actionEvent) {
    }

    public void btnPrintOnAction(ActionEvent actionEvent) {

    }
}
