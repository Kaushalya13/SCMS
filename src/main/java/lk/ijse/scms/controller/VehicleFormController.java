package lk.ijse.scms.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.scms.db.DBConnection;
import lk.ijse.scms.dto.CompanyDTO;
import lk.ijse.scms.dto.CustomerDTO;
import lk.ijse.scms.dto.VehicleDTO;
import lk.ijse.scms.dto.tm.VehicleTM;
import lk.ijse.scms.model.CompanyModel;
import lk.ijse.scms.model.CustomerModel;
import lk.ijse.scms.model.VehicleModel;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private ComboBox<String> cmbVehicle_name;

    @FXML
    private ComboBox<String> cmbType;

    @FXML
    private ComboBox<String> cmbCompany_id;

    @FXML
    private ComboBox<String> cmbCustomer_id;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private DatePicker Date;

    @FXML
    private DatePicker ReturnDate;

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
    private TableColumn<?, ?> colReceive_date;

    @FXML
    private TableColumn<?, ?> colReturn_date;

    @FXML
    private TableColumn<?, ?> colCompany_id;

    public AnchorPane loadFormContext;

    private String customer_id;
    private String company_id;

    public static ArrayList<CustomerDTO> customerDTOArrayList= new ArrayList();
    public static ArrayList<CompanyDTO> companyDTOArrayList = new ArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList("Honda","Suzuki","Yamaha Ray ZR","TVS","BAJAJ","KTM","Hero","CT 100","Dio","Discover","Hornet","Other");
        cmbVehicle_name.setItems(list);

        ObservableList<String> list1 = FXCollections.observableArrayList("Moped","Scooter","Cruiser","Trial Bikes","Dual Purpose","Sport Bikes","Other");
        cmbType.setItems(list1);

        getAll();
        setCellValueFactory();
        clearAll();

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

    void clearAll() {
        cmbCustomer_id.setValue(null);
        txtId.setText(null);
        cmbVehicle_name.setValue(null);
        cmbType.setValue(null);
        Date.setValue(null);
        ReturnDate.setValue(null);
        cmbCompany_id.setValue(null);
    }

    void setCellValueFactory() {
        colCustomer_id.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        colVehicle_id.setCellValueFactory(new PropertyValueFactory<>("vehicle_id"));
        colVehicle_name.setCellValueFactory(new PropertyValueFactory<>("vehicle_name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("vehicle_type"));
        colReceive_date.setCellValueFactory(new PropertyValueFactory<>("receive_date"));
        colReturn_date.setCellValueFactory(new PropertyValueFactory<>("return_date"));
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
                        vehicleDTO.getCompany_id(),
                        vehicleDTO.getReceive_date(),
                        vehicleDTO.getReturn_date()
                ));
            }
            tblVehicle.setItems(obList);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String date=Date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String returndate=ReturnDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        VehicleDTO vehicleDTO = new VehicleDTO(txtId.getText(), (String) cmbVehicle_name.getValue(),(String) cmbType.getValue(),(String) cmbCustomer_id.getValue(),(String) cmbCompany_id.getValue(),date,returndate);
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Vehicle " + " VALUE (?,?,?,?,?,?,?)");
            pstm.setString(1,vehicleDTO.getVehicle_id());
            pstm.setString(2,vehicleDTO.getVehicle_name());
            pstm.setString(3,vehicleDTO.getVehicle_type());
            pstm.setString(4,vehicleDTO.getCustomer_id());
            pstm.setString(5,vehicleDTO.getCompany_id());
            pstm.setString(6,vehicleDTO.getReceive_date());
            pstm.setString(7,vehicleDTO.getReturn_date());

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
        clearAll();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String date=Date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String returndate=ReturnDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        VehicleDTO vehicleDTO = new VehicleDTO(txtId.getText(), (String) cmbVehicle_name.getValue(),(String) cmbType.getValue(),(String) cmbCustomer_id.getValue(),(String) cmbCompany_id.getValue(),date,returndate);
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE Vehicle SET " + "vehicle_name = ?,vehicle_type = ?,customer_id = ?,company_id = ?,receive_date = ?,return_date = ? WHERE vehicle_id = ?");

            pstm.setString(1,vehicleDTO.getVehicle_name());
            pstm.setString(2,vehicleDTO.getVehicle_type());
            pstm.setString(3,vehicleDTO.getCustomer_id());
            pstm.setString(4,vehicleDTO.getCompany_id());
            pstm.setString(5,vehicleDTO.getReceive_date());
            pstm.setString(6,vehicleDTO.getReturn_date());
            pstm.setString(7,vehicleDTO.getVehicle_id());

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
        clearAll();
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
        clearAll();
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Vehicle WHERE vehicle_id = ? ");

            pstm.setString(1,txtSearch.getText());

            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()){
                txtId.setText(resultSet.getString(1));
                cmbVehicle_name.setValue(resultSet.getString(2));
                cmbType.setValue(resultSet.getString(3));
                cmbCustomer_id.setValue(resultSet.getString(4));
                cmbCompany_id.setValue(resultSet.getString(5));
                Date.setValue(LocalDate.parse(resultSet.getString(6)));
                ReturnDate.setValue(LocalDate.parse(resultSet.getString(7)));
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
}
