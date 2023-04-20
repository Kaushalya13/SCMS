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
import lk.ijse.scms.dto.EmployeeDTO;
import lk.ijse.scms.dto.tm.EmployeeTM;
import lk.ijse.scms.model.EmployeeModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {
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
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtNic;

    @FXML
    private JFXTextField txtContact_no;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private ComboBox<String> cmbRanks;

    @FXML
    private TableView<EmployeeTM> tblEmployee;

    @FXML
    private TableColumn<?, ?> colEmployee_id;

    @FXML
    private TableColumn<?, ?> colEmployee_name;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colRanks;

    @FXML
    private TableColumn<?, ?> colContact_no;

    public AnchorPane loadFormContext;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList("Manager","Repairer","Cashier","Mechanic supporters");
        cmbRanks.setItems(list);

        getAll();
        setCellValueFactory();
    }

    void setCellValueFactory() {
        colEmployee_id.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        colEmployee_name.setCellValueFactory(new PropertyValueFactory<>("employee_name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colRanks.setCellValueFactory(new PropertyValueFactory<>("ranks"));
        colContact_no.setCellValueFactory(new PropertyValueFactory<>("contactno"));
    }

    void getAll() {
        try {
            ObservableList<EmployeeTM> obList = FXCollections.observableArrayList();
            List<EmployeeDTO> employeeDTOList = EmployeeModel.getAll();

            for (EmployeeDTO employeeDTO : employeeDTOList){
                obList.add(new EmployeeTM(
                        employeeDTO.getEmployee_id(),
                        employeeDTO.getEmployee_name(),
                        employeeDTO.getNic(),
                        employeeDTO.getAddress(),
                        employeeDTO.getRanks(),
                        employeeDTO.getContactno()
                ));
            }
            tblEmployee.setItems(obList);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        EmployeeDTO employeeDTO = new EmployeeDTO(txtId.getText(),txtName.getText(),txtNic.getText(),txtAddress.getText(),(String) cmbRanks.getValue(), txtContact_no.getText());
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Employee " + "VALUE (?,?,?,?,?,?)");
            pstm.setString(1,employeeDTO.getEmployee_id());
            pstm.setString(2,employeeDTO.getEmployee_name());
            pstm.setString(3,employeeDTO.getNic());
            pstm.setString(4,employeeDTO.getAddress());
            pstm.setString(5,employeeDTO.getRanks());
            pstm.setString(6,employeeDTO.getContactno());

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
        EmployeeDTO employeeDTO = new EmployeeDTO(txtId.getText(),txtName.getText(),txtNic.getText(),txtAddress.getText(),(String) cmbRanks.getValue(), txtContact_no.getText());
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE Employee SET " + "employee_name = ?,nic = ?,address = ?,ranks = ?,contact_no = ? WHERE employee_id = ?");

            pstm.setString(1,employeeDTO.getEmployee_name());
            pstm.setString(2,employeeDTO.getNic());
            pstm.setString(3,employeeDTO.getAddress());
            pstm.setString(4,employeeDTO.getRanks());
            pstm.setString(5,employeeDTO.getContactno());
            pstm.setString(6,employeeDTO.getEmployee_id());

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
            PreparedStatement pstm = connection.prepareStatement("DELETE from Employee WHERE employee_id = ?");

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
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Employee WHERE employee_id = ? ");

            pstm.setString(1,txtSearch.getText());

            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()){
                txtId.setText(resultSet.getString(1));
                txtName.setText(resultSet.getString(2));
                txtNic.setText(resultSet.getString(3));
                txtAddress.setText(resultSet.getString(4));
                cmbRanks.setValue(resultSet.getString(5));
                txtContact_no.setText(resultSet.getString(6));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
