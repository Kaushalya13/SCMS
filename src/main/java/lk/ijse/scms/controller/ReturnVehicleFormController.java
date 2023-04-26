package lk.ijse.scms.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.scms.dto.CustomerDTO;
import lk.ijse.scms.dto.tm.ReturnVehicleTM;
import lk.ijse.scms.model.CustomerModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

public class ReturnVehicleFormController implements Initializable {

    @FXML
    private TableView<?> tblService;

    @FXML
    private TableColumn<?, ?> colService_job_id;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colCustomer_id;

    @FXML
    private TableColumn<?, ?> colCustomer_id1;

    @FXML
    private TableColumn<?, ?> colCustomer_id11;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private JFXComboBox<?> cmbVehicle_id;

    @FXML
    private TextField txtReceive_date;

    @FXML
    private TextField txtReturn_date;

    @FXML
    private JFXComboBox<?> cmbCustomer_id1;

    @FXML
    private TextField txtService_price;

    @FXML
    private Label lblTotal;

    @FXML
    private TextField txtVehicleId_return;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //setCellValueFactory();
    }

    /*void setCellValueFactory() {
        colService_job_id.setCellValueFactory(new PropertyValueFactory<>("service_job_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colCustomer_id.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
    }*/

    /*public void cmbCustomer_idOnAction(ActionEvent actionEvent) {
        customer_id = (String) cmbCust_id.getValue();
        try {
            CustomerDTO customerDTO = CustomerModel.search(customer_id);
            /*txtName.setText(customerDTO.getCustName());
            txtSupplierCompany.setText(supplier.getSupplier_company());
            txtId.setText(customer_id);*/


    public void cmbVehicle_idOnAction(ActionEvent actionEvent) {

    }

    public void btnNewVehicleOnAction(ActionEvent actionEvent) {

    }

    public void btnNewCustomerOnAction(ActionEvent actionEvent) {

    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {

    }

    public void cmbCustomer_idOnAction(ActionEvent actionEvent) {

    }

    public void btnReturnVehicleOnAction(ActionEvent actionEvent) {

    }

    public void btnCreateBillOnAction(ActionEvent actionEvent) {

    }
}
