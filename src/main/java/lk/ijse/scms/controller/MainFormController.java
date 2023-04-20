package lk.ijse.scms.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
    public AnchorPane loadFormContext;
    public Label lblDate;
    public Label lblTime;


    public void btnDashboradOnAction() throws IOException {
        URL resource = getClass().getResource("/view/dashborad_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        loadFormContext.getChildren().clear();
        loadFormContext.getChildren().add(load);
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/employee_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        loadFormContext.getChildren().clear();
        loadFormContext.getChildren().add(load);
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/customer_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        loadFormContext.getChildren().clear();
        loadFormContext.getChildren().add(load);

    }

    public void btnItemOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/item_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        loadFormContext.getChildren().clear();
        loadFormContext.getChildren().add(load);

    }

    public void btnSupplierOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/supplier_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        loadFormContext.getChildren().clear();
        loadFormContext.getChildren().add(load);

    }

    public void btnVehicleOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/vehicle_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        loadFormContext.getChildren().clear();
        loadFormContext.getChildren().add(load);

    }


    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/loginPage_form.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        loadFormContext.getScene().getWindow().hide();
        
    }

    public void btnUserOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/user_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        loadFormContext.getChildren().clear();
        loadFormContext.getChildren().add(load);
    }

    public void btnLoginrecodeOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/loginrecode_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        loadFormContext.getChildren().clear();
        loadFormContext.getChildren().add(load);

    }

    public void btnCompanyOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/company_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        loadFormContext.getChildren().clear();
        loadFormContext.getChildren().add(load);
    }

    public void btnServiceOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/service_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        loadFormContext.getChildren().clear();
        loadFormContext.getChildren().add(load);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            btnDashboradOnAction();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initClock();
    }

    private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            lblTime.setText(LocalDateTime.now().format(formatter));

            SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            lblDate.setText(formatter2.format(date));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

}
