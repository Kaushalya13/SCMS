package lk.ijse.scms.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.scms.db.DBConnection;
import lk.ijse.scms.dto.CustomerDTO;
import lk.ijse.scms.dto.tm.CustomerTM;
import lk.ijse.scms.model.CustomerModel;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {
    private final static String URL = "jdbc:mysql://localhost:3306//scms";
    private final static Properties props = new Properties();

    static {
        props.setProperty("user","root");
        props.setProperty("password","1234");
    }

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
    private JFXTextField txtSearch;

    @FXML
    private TableView<CustomerTM> tblCustomer;

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
        txtNic.setText(null);
        txtAddress.setText(null);
        txtContactNo.setText(null);
    }

    void setCellValueFactory() {
        colCustmoer_id.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colCustomer_name.setCellValueFactory(new PropertyValueFactory<>("custName"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact_no.setCellValueFactory(new PropertyValueFactory<>("contactno"));
    }

    void getAll(){
        try {
            ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
            List<CustomerDTO> custDTOList = CustomerModel.getAll();

            for (CustomerDTO customerDTO : custDTOList){
                obList.add(new CustomerTM(
                        customerDTO.getCustId(),
                        customerDTO.getCustName(),
                        customerDTO.getNic(),
                        customerDTO.getAddress(),
                        customerDTO.getEmail(),
                        customerDTO.getContactno()

                ));
            }
            tblCustomer.setItems(obList);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }
    public void btnSaveOnAction(ActionEvent actionEvent) {
        CustomerDTO custDTO = new CustomerDTO(txtId.getText(),txtName.getText(),txtNic.getText(),txtAddress.getText(),txtEmail.getText(),txtContactNo.getText());
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer " + "VALUE (?,?,?,?,?,?)");
            pstm.setString(1,custDTO.getCustId());
            pstm.setString(2,custDTO.getCustName());
            pstm.setString(3,custDTO.getNic());
            pstm.setString(4,custDTO.getAddress());
            pstm.setString(5,custDTO.getEmail());
            pstm.setString(6,custDTO.getContactno());

            int add = pstm.executeUpdate();

            if (add>0 ){
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
        CustomerDTO custDTO = new CustomerDTO(txtId.getText(),txtName.getText(),txtNic.getText(),txtAddress.getText(),txtEmail.getText(),txtContactNo.getText());
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET " + "customer_name= ?,nic = ?,address = ?,email = ?,contact_no = ? WHERE customer_id = ?");

            pstm.setString(1,custDTO.getCustName());
            pstm.setString(2,custDTO.getNic());
            pstm.setString(3,custDTO.getAddress());
            pstm.setString(4,custDTO.getEmail());
            pstm.setString(5,custDTO.getContactno());
            pstm.setString(6,custDTO.getCustId());

            int add = pstm.executeUpdate();
            if (add > 0){
                new Alert(Alert.AlertType.CONFIRMATION,"Updated", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Try agin", ButtonType.OK).show();
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
            PreparedStatement pstm = connection.prepareStatement("DELETE from Customer WHERE customer_id = ?");

            pstm.setString(1,txtId.getText());

            int add = pstm.executeUpdate();
            if (add > 0){
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Try agin", ButtonType.OK).show();
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
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE customer_id = ? ");

            pstm.setString(1,txtSearch.getText());

            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()){
                txtId.setText(resultSet.getString(1));
                txtName.setText(resultSet.getString(2));
                txtNic.setText(resultSet.getString(3));
                txtAddress.setText(resultSet.getString(4));
                txtEmail.setText(resultSet.getString(5));
                txtContactNo.setText(resultSet.getString(6));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
