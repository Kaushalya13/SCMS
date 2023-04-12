package lk.ijse.scms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginPage_form.fxml"));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
