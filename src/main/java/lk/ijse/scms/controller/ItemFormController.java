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
import lk.ijse.scms.dto.ItemDTO;
import lk.ijse.scms.dto.tm.ItemTM;
import lk.ijse.scms.model.ItemModel;

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
    private JFXTextField txtDescription;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList("Manager","Repairer","Cashier","Mechanic supporters");
        cmbItem_type.setItems(list);

        getAll();
        setCellValueFactory();
    }

    void setCellValueFactory() {
        colItem_code.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItem_type.setCellValueFactory(new PropertyValueFactory<>("itemType"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
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
                        itemDTO.getDescription(),
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
        ItemDTO itemDTO = new ItemDTO(txtItem_code.getText(),(String) cmbItem_type.getValue(), txtDescription.getText(),Double.parseDouble(txtUnitPrice.getText()),Integer.parseInt(txtQtyOnStock.getText()));
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item " + "VALUE (?,?,?,?,?)");
            pstm.setString(1,itemDTO.getItemCode());
            pstm.setString(2,itemDTO.getItemType());
            pstm.setString(3,itemDTO.getDescription());
            pstm.setDouble(4,itemDTO.getUnitPrice());
            pstm.setInt(5,itemDTO.getQtyOnStock());


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
       ItemDTO itemDTO = new ItemDTO(txtItem_code.getText(),(String) cmbItem_type.getValue(), txtDescription.getText(),Double.parseDouble(txtUnitPrice.getText()),Integer.parseInt(txtQtyOnStock.getText()));
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET " + "itemType = ?,description = ?,unitPrice = ?,qtyOnStock = ? WHERE itemCode = ?");

            pstm.setString(1,itemDTO.getItemType());
            pstm.setString(2,itemDTO.getDescription());
            pstm.setDouble(3,itemDTO.getUnitPrice());
            pstm.setInt(4,itemDTO.getQtyOnStock());
            pstm.setString(5,itemDTO.getItemCode());

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
                txtDescription.setText(resultSet.getString(3));
                txtUnitPrice.setText(resultSet.getString(4));
                txtQtyOnStock.setText(resultSet.getString(5));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
