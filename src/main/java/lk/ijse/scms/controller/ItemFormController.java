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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.scms.db.DBConnection;
import lk.ijse.scms.dto.ItemDTO;
import lk.ijse.scms.dto.tm.ItemTM;
import lk.ijse.scms.model.ItemModel;
import lk.ijse.scms.util.Regex;
import lk.ijse.scms.util.TextFields;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {
    private final static String URL = "jdbc:mysql://localhost:3306//scms";
    private final static Properties props = new Properties();

    static {
        props.setProperty("user","root");
        props.setProperty("password","1234");
    }

    @FXML
    private JFXTextField txtItem_code;

    @FXML
    private JFXTextField txtQtyOnStock;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    private ComboBox<String> cmbItem_type;

    @FXML
    private JFXTextField txtSerach;

    @FXML
    private TableView<ItemTM> tblItem;

    @FXML
    private TableColumn<?, ?> colItem_code;

    @FXML
    private TableColumn<?, ?> colItem_type;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> colQtyOnStock;

    public AnchorPane loadFormContext;

    @FXML
    private TextField txtItem_place;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList("Oil","Spare parts");
        cmbItem_type.setItems(list);

        getAll();
        setCellValueFactory();
        clearAll();
    }
    void clearAll() {
        txtItem_code.setText(null);
        cmbItem_type.setValue(null);
        txtUnitPrice.setText(null);
        txtQtyOnStock.setText(null);
    }
    void setCellValueFactory() {
        colItem_code.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItem_type.setCellValueFactory(new PropertyValueFactory<>("itemType"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnStock.setCellValueFactory(new PropertyValueFactory<>("qtyOnStock"));
    }

    void getAll() {
        try {
            ObservableList<ItemTM> obList = FXCollections.observableArrayList();
            List<ItemDTO> itemDTOListDTOList = ItemModel.getAll();

            for (ItemDTO itemDTO : itemDTOListDTOList){
                obList.add(new ItemTM(
                        itemDTO.getItemCode(),
                        itemDTO.getItemType(),
                        itemDTO.getUnitPrice(),
                        itemDTO.getQtyOnStock()
                ));
            }
            tblItem.setItems(obList);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Query error!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if(!isValidated()){
            new Alert(Alert.AlertType.ERROR,"Check Fields").show();
            return;
        }
        ItemDTO itemDTO = new ItemDTO(txtItem_code.getText(),(String) cmbItem_type.getValue(),Double.parseDouble(txtUnitPrice.getText()),Integer.parseInt(txtQtyOnStock.getText()));
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item " + "VALUE (?,?,?,?)");
            pstm.setString(1,itemDTO.getItemCode());
            pstm.setString(2,itemDTO.getItemType());
            pstm.setDouble(3,itemDTO.getUnitPrice());
            pstm.setInt(4,itemDTO.getQtyOnStock());


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
        if(!isValidated()){
            new Alert(Alert.AlertType.ERROR,"Check Fields").show();
            return;
        }
       ItemDTO itemDTO = new ItemDTO(txtItem_code.getText(),(String) cmbItem_type.getValue(),Double.parseDouble(txtUnitPrice.getText()),Integer.parseInt(txtQtyOnStock.getText()));
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET " + "itemType = ?,unitPrice = ?,qtyOnStock = ? WHERE itemCode = ?");

            pstm.setString(1,itemDTO.getItemType());
            pstm.setDouble(2,itemDTO.getUnitPrice());
            pstm.setInt(3,itemDTO.getQtyOnStock());
            pstm.setString(4,itemDTO.getItemCode());

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
            PreparedStatement pstm = connection.prepareStatement("DELETE from Item WHERE itemCode = ?");

            pstm.setString(1,txtItem_code.getText());

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
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE itemCode = ? ");

            pstm.setString(1,txtSerach.getText());

            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()){
                txtItem_code.setText(resultSet.getString(1));
                cmbItem_type.setValue(resultSet.getString(2));
                txtUnitPrice.setText(resultSet.getString(3));
                txtQtyOnStock.setText(resultSet.getString(4));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void txtItemCodeOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.INVOICE,txtItem_code);
    }

    public void txtUnitPriceOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DOUBLE,txtUnitPrice);
    }

    public void txtQtyOnStockOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.INTEGER,txtQtyOnStock);
    }

    public boolean isValidated(){
        if(!Regex.setTextColor(TextFields.INVOICE,txtItem_code))return false;
        if(!Regex.setTextColor(TextFields.DOUBLE,txtUnitPrice))return false;
        if(!Regex.setTextColor(TextFields.INTEGER,txtQtyOnStock))return false;
        return true;
    }

    public void CreateBillOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasDesign = JRXmlLoader.load("src\\main\\resources\\reports\\Item.jrxml");
        JasperReport jasReport = JasperCompileManager.compileReport(jasDesign);
        JasperPrint jasPrint = JasperFillManager.fillReport(jasReport, null, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasPrint,false);
    }
}
