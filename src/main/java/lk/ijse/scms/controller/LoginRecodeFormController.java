package lk.ijse.scms.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.scms.dto.LoginrecodeDTO;
import lk.ijse.scms.dto.UserDTO;
import lk.ijse.scms.dto.tm.LoginTM;
import lk.ijse.scms.model.LoginrecodeModel;
import lk.ijse.scms.model.OrderModel;
import lk.ijse.scms.model.UserModel;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Properties;
import java.util.ResourceBundle;

public class LoginRecodeFormController implements Initializable {
    private final static String URL = "jdbc:mysql://localhost:3306//scms";
    private final static Properties props = new Properties();

    static {
        props.setProperty("user","root");
        props.setProperty("password","1234");
    }

    @FXML
    private TableView<LoginTM> tblLoginrecode;

    @FXML
    private TableColumn<?, ?> colUser_name;


    @FXML
    private TableColumn<?, ?> colLogin_id;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private TableColumn<?, ?> colUser_id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateNextLoginId();
    }

    private void generateNextLoginId() {
        try {
            String nextId = LoginrecodeModel.generateNextLoginId();
            colLogin_id.setText(nextId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
