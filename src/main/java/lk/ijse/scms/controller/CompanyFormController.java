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
import lk.ijse.scms.dto.tm.CompanyTM;
import lk.ijse.scms.model.CompanyModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class CompanyFormController implements Initializable {
    private final static String URL = "jdbc:mysql://localhost:3306//scms";
    private final static Properties props = new Properties();

    static {
        props.setProperty("user","root");
        props.setProperty("password","1234");
    }

    @FXML
    private JFXTextField txtId;


    @FXML
    private ComboBox<String> cmbCompany_name;

    @FXML
    private ComboBox<String> cmbType;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private TableView<CompanyTM> tblCompany;

    @FXML
    private TableColumn<?, ?> colCompany_id;

    @FXML
    private TableColumn<?, ?> colCompany_name;

    @FXML
    private TableColumn<?, ?> colType;

    public AnchorPane loadFormContext;

    @Override
    public void initialize(java.net.URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList("Ranathunga Motors","Mihiri Motors","Other Company");
        cmbCompany_name.setItems(list);

        ObservableList<String> list1 = FXCollections.observableArrayList("First Service","Second Service","Other Service");
        cmbType.setItems(list1);

        clearAll();
        getAll();
        setCellValueFactory();
    }
    void clearAll() {
        txtId.setText(null);
        cmbCompany_name.setValue(null);
        cmbType.setValue(null);
    }
    void setCellValueFactory() {
        colCompany_id.setCellValueFactory(new PropertyValueFactory<>("company_id"));
        colCompany_name.setCellValueFactory(new PropertyValueFactory<>("company_name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("company_type"));
    }

    void getAll() {
        try {
            ObservableList<CompanyTM> obList = FXCollections.observableArrayList();
            List<CompanyDTO> companyDTOList = CompanyModel.getAll();

            for (CompanyDTO companyDTO : companyDTOList){
                obList.add(new CompanyTM(
                        companyDTO.getCompany_id(),
                        companyDTO.getCompany_name(),
                        companyDTO.getCompany_type()
                ));
            }
            tblCompany.setItems(obList);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        CompanyDTO companyDTO = new CompanyDTO(txtId.getText(),(String) cmbCompany_name.getValue(), (String) cmbType.getValue());
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Company " + "VALUE (?,?,?)");
            pstm.setString(1,companyDTO.getCompany_id());
            pstm.setString(2,companyDTO.getCompany_name());
            pstm.setString(3,companyDTO.getCompany_type());

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
        CompanyDTO companyDTO = new CompanyDTO(txtId.getText(), (String) cmbCompany_name.getValue(), (String) cmbType.getValue());
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE Company SET " + "company_name = ?,company_type = ? WHERE company_id = ?");

            pstm.setString(1,companyDTO.getCompany_name());
            pstm.setString(2,companyDTO.getCompany_type());
            pstm.setString(3,companyDTO.getCompany_id());

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
            PreparedStatement pstm = connection.prepareStatement("DELETE from Company WHERE company_id = ?");

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
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Company WHERE company_id = ? ");

            pstm.setString(1,txtSearch.getText());

            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()){
                txtId.setText(resultSet.getString(1));
                cmbCompany_name.setValue(resultSet.getString(2));
                cmbType.setValue(resultSet.getString(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
