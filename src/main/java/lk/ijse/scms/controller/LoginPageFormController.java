package lk.ijse.scms.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginPageFormController {

    public AnchorPane LogInFormcontext;
    public Label lblerror;

    @FXML
    private JFXButton btnlogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
       /* AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/main_form.fxml"));
        Stage stage = (Stage) btnlogin.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("LoginPage");
        stage.centerOnScreen();*/

        LoginFormManager();
    }

    private void LoginFormManager() throws IOException {
        String user = "SCMS";
        String password = "1234";
        if ( txtUserName.getText().equals(user) &&  txtPassword.getText().equals(password)) {
            Stage window = (Stage) LogInFormcontext.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/main_form.fxml"))));
            window.centerOnScreen();
        } else if ( txtUserName.getText().isEmpty() &&  txtPassword.getText().isEmpty()) {
            lblerror.setText("Your User Name Or Password IS Empty...!");
            txtPassword.clear();
            txtPassword.clear();
        }
        else if (! txtUserName.getText().equals(user)) {
            lblerror.setText("Your User Name is incorrect..!");
            txtUserName.clear();
            txtPassword.clear();
        } else if (! txtPassword.getText().equals(password)) {
            lblerror.setText("Your Password is incorrect..!");
            txtUserName.clear();
            txtPassword.clear();
        }
    }
}
