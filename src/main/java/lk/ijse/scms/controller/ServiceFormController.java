package lk.ijse.scms.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.scms.db.DBConnection;
import lk.ijse.scms.dto.CustomerDTO;
import lk.ijse.scms.dto.ServiceDTO;
import lk.ijse.scms.dto.tm.ServiceTM;
import lk.ijse.scms.model.CustomerModel;
import lk.ijse.scms.model.ServiceModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class ServiceFormController implements Initializable {
    private final static String URL = "jdbc:mysql://localhost:3306//scms";
    private final static Properties props = new Properties();

    static {
        props.setProperty("user","root");
        props.setProperty("password","1234");
    }
    @FXML
    private DatePicker Dob;
    
    @FXML
    private JFXTextField txtId;

    @FXML
    private ComboBox<String> cmbType;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private TableView<ServiceTM> tblService;

    @FXML
    private TableColumn<?, ?> colService_job_id;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colCustomer_id;

    @FXML
    private ComboBox<String> cmbCust_id;

    public AnchorPane loadFormContext;

    private String customer_id;
    public static ArrayList<CustomerDTO> customerDTOArrayList= new ArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList("Admi","Cashier");
        cmbType.setItems(list);

        try {
            loadComboBox();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getAll();
        setCellValueFactory();
    }

    void loadComboBox() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        customerDTOArrayList=CustomerModel.View();
        for (CustomerDTO customerDTO:customerDTOArrayList) {
            list.add(customerDTO.getCustId());
        }
        cmbCust_id.setItems(list);
    }

    void setCellValueFactory() {
        colService_job_id.setCellValueFactory(new PropertyValueFactory<>("service_job_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colCustomer_id.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
    }

    void getAll() {
        try {
            ObservableList<ServiceTM> obList = FXCollections.observableArrayList();
            List<ServiceDTO> serviceDTOList = ServiceModel.getAll();

            for (ServiceDTO serviceDTO : serviceDTOList){
                obList.add(new ServiceTM(
                        serviceDTO.getService_job_id(),
                        serviceDTO.getDate(),
                        serviceDTO.getType(),
                        serviceDTO.getCustomer_id()
                ));
            }
            tblService.setItems(obList);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String dob=Dob.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        ServiceDTO serviceDTO = new ServiceDTO(txtId.getText(),dob,(String) cmbType.getValue(),(String) cmbCust_id.getValue());
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Service " + "VALUE (?,?,?,?)");
            pstm.setString(1,serviceDTO.getService_job_id());
            pstm.setString(2,serviceDTO.getDate());
            pstm.setString(3,serviceDTO.getType());
            pstm.setString(4,serviceDTO.getCustomer_id());

            int add = pstm.executeUpdate();

            if (add > 0){
                new Alert(Alert.AlertType.CONFIRMATION,"Saved", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Try agin", ButtonType.OK).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getAll();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String dob=Dob.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        ServiceDTO serviceDTO = new ServiceDTO(txtId.getText(),dob,(String) cmbType.getValue(),(String) cmbCust_id.getValue());
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE Service SET " + "date = ?,type = ?,customer_id = ? WHERE service_job_id = ?");

            pstm.setString(1,serviceDTO.getDate());
            pstm.setString(2,serviceDTO.getType());
            pstm.setString(3,serviceDTO.getCustomer_id());
            pstm.setString(4,serviceDTO.getService_job_id());

            int add = pstm.executeUpdate();

            if (add > 0){
                new Alert(Alert.AlertType.CONFIRMATION,"Updated", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Try agin", ButtonType.OK).show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getAll();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE from Service WHERE service_job_id = ?");

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
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
       /*String dob=Dob.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Service WHERE service_job_id = ? ");

            pstm.setString(1,txtSearch.getText());

            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()){
                txtId.setText(resultSet.getString(1));
                dob.setValue(resultSet.getString(2));
                cmbType.setValue(resultSet.getString(3));
                cmbCust_id.setValue(resultSet.getString(4));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
    }

    public void cmbCustomer_idOnAction(ActionEvent actionEvent) {
        customer_id = (String) cmbCust_id.getValue();
        try {
            CustomerDTO customerDTO = CustomerModel.search(customer_id);
            /*txtName.setText(customerDTO.getCustName());
            txtSupplierCompany.setText(supplier.getSupplier_company());
            txtId.setText(customer_id);*/
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnAddServiceOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/placeItem_form.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        loadFormContext.getScene().getWindow().hide();
    }
}
