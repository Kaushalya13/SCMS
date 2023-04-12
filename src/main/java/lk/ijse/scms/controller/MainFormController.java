package lk.ijse.scms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
    public AnchorPane loadFormContext;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            btnDashboradOnAction();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
