package lk.ijse.scms.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.scms.db.DBConnection;
import lk.ijse.scms.dto.SupplierDTO;
import lk.ijse.scms.dto.tm.SupplierTM;
import lk.ijse.scms.model.SupplierModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {
    private final static String URL = "jdbc:mysql://localhost:3306//scms";
    private final static Properties props = new Properties();

    static {
        props.setProperty("user","root");
        props.setProperty("password","1234");
    }

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtContactNo;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private TableView<SupplierTM> tblSupplier;

    @FXML
    private TableColumn<?, ?> colSupplier_id;

    @FXML
    private TableColumn<?, ?> colSupplier_name;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colContact_no;

    public AnchorPane loadFormContext;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getAll();
        setCellValueFactory();
        clearAll();
    }

    void clearAll() {
        txtId.setText(null);
        txtName.setText(null);
        txtAddress.setText(null);
        txtEmail.setText(null);
        txtContactNo.setText(null);
    }

    void setCellValueFactory() {
        colSupplier_id.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
        colSupplier_name.setCellValueFactory(new PropertyValueFactory<>("supplier_name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact_no.setCellValueFactory(new PropertyValueFactory<>("contactno"));
    }

    void getAll(){
        try {
            ObservableList<SupplierTM> obList = FXCollections.observableArrayList();
            List<SupplierDTO> supplierDTOList = SupplierModel.getAll();

            for (SupplierDTO supplierDTO : supplierDTOList){
                obList.add(new SupplierTM(
                        supplierDTO.getSupplier_id(),
                        supplierDTO.getSupplier_name(),
                        supplierDTO.getAddress(),
                        supplierDTO.getEmail(),
                        supplierDTO.getContactno()
                ));
            }
            tblSupplier.setItems(obList);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }
    public void btnSaveOnAction(ActionEvent actionEvent) {
        SupplierDTO supplierDTO = new SupplierDTO(txtId.getText(), txtName.getText(), txtAddress.getText(), txtEmail.getText(), txtContactNo.getText());
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Supplier " + "VALUE (?,?,?,?,?)");
            pstm.setString(1,supplierDTO.getSupplier_id());
            pstm.setString(2,supplierDTO.getSupplier_name());
            pstm.setString(3,supplierDTO.getAddress());
            pstm.setString(4,supplierDTO.getEmail());
            pstm.setString(5,supplierDTO.getContactno());

            int add = pstm.executeUpdate();

            if (add > 0){
                new Alert(Alert.AlertType.CONFIRMATION,"Saved", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Try agin", ButtonType.OK).show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        getAll();
        clearAll();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        SupplierDTO supplierDTO = new SupplierDTO(txtId.getText(), txtName.getText(), txtAddress.getText(), txtEmail.getText(), txtContactNo.getText());
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE Supplier SET " + "supplier_name= ?,address = ?,email = ?,contact_no = ? WHERE supplier_id = ?");

            pstm.setString(1,supplierDTO.getSupplier_name());
            pstm.setString(2,supplierDTO.getAddress());
            pstm.setString(3,supplierDTO.getEmail());
            pstm.setString(4,supplierDTO.getContactno());
            pstm.setString(5,supplierDTO.getSupplier_id());

            int add = pstm.executeUpdate();
            if (add > 0){
                new Alert(Alert.AlertType.CONFIRMATION,"Updated",ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Try agin",ButtonType.OK).show();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        getAll();
        clearAll();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE from Supplier WHERE supplier_id = ?");

            pstm.setString(1,txtId.getText());

            int add = pstm.executeUpdate();
            if (add > 0){
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted",ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Try agin",ButtonType.OK).show();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        getAll();
        clearAll();
    }


    public void btnSearchOnAction(ActionEvent actionEvent) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Supplier WHERE supplier_id = ?");

            pstm.setString(1,txtSearch.getText());

            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()){
                txtId.setText(resultSet.getString(1));
                txtName.setText(resultSet.getString(2));
                txtAddress.setText(resultSet.getString(3));
                txtEmail.setText(resultSet.getString(4));
                txtContactNo.setText(resultSet.getString(5));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}