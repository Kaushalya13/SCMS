package lk.ijse.scms.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class LoginRecodeFormController {

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtTime;

    @FXML
    private JFXTextField txtDate;

    @FXML
    private TableView<?> tblLoginrecode;

    @FXML
    private TableColumn<?, ?> colLogin_id;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colTime;

    public void btnSerachOnAction(ActionEvent actionEvent) {

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

    }
}
