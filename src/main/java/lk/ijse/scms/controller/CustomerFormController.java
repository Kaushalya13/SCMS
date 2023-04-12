package lk.ijse.scms.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lk.ijse.scms.db.DBConnection;
import lk.ijse.scms.dto.customerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerFormController {
    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtNic;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtContactNo;

    @FXML
    private JFXTextField txtUserId;

    @FXML
    private TableView<?> tblCustomer;

    @FXML
    private TableColumn<?, ?> colCustmoer_id;

    @FXML
    private TableColumn<?, ?> colCustomer_name;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colContact_no;


    public void btnSaveOnAction(ActionEvent actionEvent) {
        customerDTO custDTO = new customerDTO(txtId.getText(),txtName.getText(),txtNic.getText(),txtAddress.getText(),txtEmail.getText(),txtContactNo.getText(),txtUserId.getText());
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer " + "VALUE (?,?,?,?,?,?,?)");
            pstm.setString(1,custDTO.getCustId());
            pstm.setString(2,custDTO.getCustName());
            pstm.setString(3,custDTO.getNic());
            pstm.setString(4,custDTO.getAddress());
            pstm.setString(5,custDTO.getEmail());
            pstm.setString(6,custDTO.getContactno());
            pstm.setString(7,custDTO.getUserId());

            int add = pstm.executeUpdate();
            if (add > 0){
                new Alert(Alert.AlertType.CONFIRMATION,"Saved", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Not Saved", ButtonType.OK).show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {

    }
}
