package lk.ijse.scms.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class LoginPageFormController {
    @FXML
    private JFXButton btnlogin;

    @FXML
    private TextField Username;

    @FXML
    private PasswordField Password;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/main_form.fxml"));
        Stage stage = (Stage) btnlogin.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("LoginPage");
        stage.centerOnScreen();

    }
}
