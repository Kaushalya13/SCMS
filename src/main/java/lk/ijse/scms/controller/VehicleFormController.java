package lk.ijse.scms.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.scms.db.DBConnection;
import lk.ijse.scms.dto.CompanyDTO;
import lk.ijse.scms.dto.CustomerDTO;
import lk.ijse.scms.dto.VehicleDTO;
import lk.ijse.scms.dto.tm.VehicleTM;
import lk.ijse.scms.model.CompanyModel;
import lk.ijse.scms.model.CustomerModel;
import lk.ijse.scms.model.VehicleModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;


public class VehicleFormController implements Initializable {
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
    private ComboBox<String> cmbType;

    @FXML
    private ComboBox<String> cmbCompany_id;

    @FXML
    private ComboBox<String> cmbCustomer_id;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private TableView<VehicleTM> tblVehicle;

    @FXML
    private TableColumn<?, ?> colCustomer_id;

    @FXML
    private TableColumn<?, ?> colVehicle_id;

    @FXML
    private TableColumn<?, ?> colVehicle_name;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colCompany_id;

    public AnchorPane loadFormContext;

    private String customer_id;
    private String company_id;

    public static ArrayList<CustomerDTO> customerDTOArrayList= new ArrayList();
    public static ArrayList<CompanyDTO> companyDTOArrayList = new ArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList("Honda","Suzuki","Yamaha","TVS","BAJAJ","Other");
        cmbType.setItems(list);
        getAll();
        setCellValueFactory();
        try {
            loadComboBox();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            loadCompanyId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void loadCompanyId() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        companyDTOArrayList= CompanyModel.View();
        for (CompanyDTO companyDTO :companyDTOArrayList){
            list.add(companyDTO.getCompany_id());
        }
        cmbCompany_id.setItems(list);
    }

    void loadComboBox() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        customerDTOArrayList=CustomerModel.View();
        for (CustomerDTO customerDTO:customerDTOArrayList) {
            list.add(customerDTO.getCustId());
        }
        cmbCustomer_id.setItems(list);
    }

    void setCellValueFactory() {
        colVehicle_id.setCellValueFactory(new PropertyValueFactory<>("vehicle_id"));
        colVehicle_name.setCellValueFactory(new PropertyValueFactory<>("vehicle_name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("vehicle_type"));
        colCustomer_id.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        colCompany_id.setCellValueFactory(new PropertyValueFactory<>("company_id"));
    }

    void getAll() {
        try {
            ObservableList<VehicleTM> obList = FXCollections.observableArrayList();
            List<VehicleDTO> vehicleDTOList = VehicleModel.getAll();

            for (VehicleDTO vehicleDTO : vehicleDTOList){
                obList.add(new VehicleTM(
                        vehicleDTO.getVehicle_id(),
                        vehicleDTO.getVehicle_name(),
                        vehicleDTO.getVehicle_type(),
                        vehicleDTO.getCustomer_id(),
                        vehicleDTO.getCompany_id()
                ));
            }
            tblVehicle.setItems(obList);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        VehicleDTO vehicleDTO = new VehicleDTO(txtId.getId(), txtName.getText(),(String) cmbType.getValue(),(String) cmbCustomer_id.getValue(),(String) cmbCompany_id.getValue());
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Vehicle " + "VALUE (?,?,?,?,?)");
            pstm.setString(1,vehicleDTO.getVehicle_id());
            pstm.setString(2,vehicleDTO.getVehicle_name());
            pstm.setString(3,vehicleDTO.getVehicle_type());
            pstm.setString(4,vehicleDTO.getCustomer_id());
            pstm.setString(5,vehicleDTO.getCompany_id());

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
        VehicleDTO vehicleDTO = new VehicleDTO(txtId.getId(), txtName.getText(),(String) cmbType.getValue(),(String) cmbCustomer_id.getValue(),(String) cmbCompany_id.getValue());
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE Vehicle SET " + "vehicle_name = ?,vehicle_type = ?,customer_id = ?,company_id = ? WHERE vehicle_id = ?");

            pstm.setString(1,vehicleDTO.getVehicle_name());
            pstm.setString(2,vehicleDTO.getVehicle_type());
            pstm.setString(3,vehicleDTO.getCompany_id());
            pstm.setString(4,vehicleDTO.getCustomer_id());
            pstm.setString(5,vehicleDTO.getVehicle_id());

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
            PreparedStatement pstm = connection.prepareStatement("DELETE from Vehicle WHERE vehicle_id = ?");

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
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Vehicle WHERE vehicle_id = ? ");

            pstm.setString(1,txtSearch.getText());

            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()){
                txtId.setText(resultSet.getString(1));
                txtName.setText(resultSet.getString(2));
                cmbType.setValue(resultSet.getString(3));
                cmbCustomer_id.setValue(resultSet.getString(4));
                cmbCompany_id.setValue(resultSet.getString(5));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void cmbCustomer_idOnAction(ActionEvent actionEvent) {
        customer_id = (String) cmbCustomer_id.getValue();
        try {
            CustomerDTO customerDTO = CustomerModel.search(customer_id);
            /*txtName.setText(customerDTO.getCustName());
            txtSupplierCompany.setText(supplier.getSupplier_company());
            txtId.setText(customer_id);*/
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void cmbCompany_idOnAction(ActionEvent actionEvent) {
        company_id = (String) cmbCompany_id.getValue();
        try {
            CompanyDTO companyDTO = CompanyModel.search(company_id);
            /*txtName.setText(customerDTO.getCustName());
            txtSupplierCompany.setText(supplier.getSupplier_company());
            txtId.setText(company_id);*/
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        /*FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/main_form.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        loadFormContext.getScene().getWindow().hide();*/
    }
}
