package lk.ijse.scms.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.scms.db.DBConnection;
import lk.ijse.scms.dto.*;
import lk.ijse.scms.dto.tm.PlaceOrderTM;
import lk.ijse.scms.model.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PlaceOrderFormController implements Initializable {

    public TextField txtOrderID_Place;
    @FXML
    private JFXComboBox<String> cmbItemCode;

    @FXML
    private TextField txtItemType;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderTime;

    @FXML
    private TextField txtQty;

    @FXML
    private JFXComboBox<String> cmbCustomer_id;

    @FXML
    private TableColumn<PlaceOrderTM, String> colItemCode;

    @FXML
    private TableColumn<PlaceOrderTM, String> colItemType;

    @FXML
    private TableColumn<PlaceOrderTM, String> colUnitPrice;

    @FXML
    private TableColumn<PlaceOrderTM, String> colOrder_id;

    @FXML
    private TableColumn<PlaceOrderTM,Integer> colQty;

    @FXML
    private TableColumn<PlaceOrderTM, Double> colTotal;

    @FXML
    private TableColumn<PlaceOrderTM, String> colAction;

    @FXML
    private TextField txtQtyOnStock;

    @FXML
    private Label lblOrder_id;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblOrder;

    @FXML
    private TableView<PlaceOrderTM> tblPlaceOrder;

    @FXML
    private AnchorPane loadFormContext;

    private String customer_id;
    private String itemCode;
    private String order_id;
    public static ArrayList<CustomerDTO> customerDTOArrayList= new ArrayList();
    public static ArrayList<ItemDTO> itemDTOArrayList= new ArrayList();

    private ObservableList<PlaceOrderTM> obList = FXCollections.observableArrayList();
    private EventHandler<ActionEvent> e;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setOrderDate();
        setOrderTime();
        try {
            loadCustomerId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            loadItemCode();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setCellValueFactory();
        generateNextOrderId();

    }

    private void generateNextOrderId() {
        try {
            String nextId = OrderModel.generateNextOrderId();
            lblOrder_id.setText(nextId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void setCellValueFactory() {
        colOrder_id.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemType.setCellValueFactory(new PropertyValueFactory<>("itemType"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    void loadItemCode() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        itemDTOArrayList = ItemModel.View();
        for (ItemDTO itemDTO : itemDTOArrayList){
            list.add(itemDTO.getItemCode());
        }
        cmbItemCode.setItems(list);
    }

    void loadCustomerId() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        customerDTOArrayList = CustomerModel.View();
        for (CustomerDTO customerDTO : customerDTOArrayList){
            list.add(customerDTO.getCustId());
        }
        cmbCustomer_id.setItems(list);
    }

    private void setOrderTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        lblOrderTime.setText(LocalDateTime.now().format(formatter));
    }

    private void setOrderDate(){
        lblOrderDate.setText((String.valueOf(LocalDate.now())));
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String order_id = lblOrder_id.getText();
        String itemCode = cmbItemCode.getValue();
        String itemType = txtItemType.getText();
        Double unitPrice = Double.valueOf(txtUnitPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        double total = qty * unitPrice;
        Button btnRemove = new Button("Remove");
        btnRemove.setCursor(Cursor.CLOSED_HAND);

        setRemoveBtnOnAction(btnRemove);

        if (!obList.isEmpty()){
            for (int i =0; i< tblPlaceOrder.getItems().size(); i++){
                if (colItemCode.getCellData(i).equals(itemCode)){
                    qty += (int) colQty.getCellData(i);
                    total = qty * unitPrice;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTotal(total);

                    tblPlaceOrder.refresh();
                    calculateNetTotal();
                    return;
                }
            }
        }
        PlaceOrderTM placeOrderTM = new PlaceOrderTM(order_id,itemCode, itemType, unitPrice, qty, total, btnRemove);
        obList.add(placeOrderTM);
        tblPlaceOrder.setItems(obList);
        calculateNetTotal();

        txtQty.setText("");
    }

    private void calculateNetTotal() {
        double netTotal = 0.0;
        for (int i = 0; i < tblPlaceOrder.getItems().size(); i++){
            double total = (double) colTotal.getCellData(i);
            netTotal += total;
        }
        lblNetTotal.setText(String.valueOf((netTotal)));
    }

    private void setRemoveBtnOnAction(Button btnRemove) {
        btnRemove.setOnAction((e) ->{
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove ?", yes, no).showAndWait();

            if (result.orElse(no) == yes){
                int index = tblPlaceOrder.getSelectionModel().getSelectedIndex();
                obList.remove(index);

                tblPlaceOrder.refresh();
                calculateNetTotal();
            }
        });
    }

    @FXML
    void btnNewCustomerOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/view/customer_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        loadFormContext.getChildren().clear();
        loadFormContext.getChildren().add(load);
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws SQLException, JRException {
        String oId = lblOrder_id.getText();
        String custId = cmbCustomer_id.getValue();

        List<CartDTO> cartDTOList = new ArrayList<>();

        for (int i=0; i < tblPlaceOrder.getItems().size(); i++){
            PlaceOrderTM placeOrderTM = obList.get(i);

            CartDTO cartDTO = new CartDTO(placeOrderTM.getItemCode(), placeOrderTM.getUnitPrice(), placeOrderTM.getQty());
            cartDTOList.add(cartDTO);
        }

        try {
            boolean isPlaced = PlaceOrderModel.placeOrder(oId, custId, cartDTOList);
            if (isPlaced){
                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Order Not Placed!").show();
            }
        }catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    @FXML
    void cmbCustomer_idOnAction(ActionEvent event) {
        customer_id = (String) cmbCustomer_id.getValue();
        try {
            CustomerDTO customerDTO = CustomerModel.search(customer_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void cmbItemCodeOnAction(ActionEvent event) {
        itemCode = (String) cmbItemCode.getValue();
        try {
            ItemDTO itemDTO = ItemModel.search(itemCode);
            txtItemType.setText(itemDTO.getItemType());
            txtUnitPrice.setText(String.valueOf(itemDTO.getUnitPrice()));
            txtQtyOnStock.setText(String.valueOf(itemDTO.getQtyOnStock()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {
        btnAddToCartOnAction(event);
    }

    @FXML
    void rowOnMouseClicked(MouseEvent mouseEvent) {
        Integer index = tblPlaceOrder.getSelectionModel().getSelectedIndex();

        if (index <= -1){
            return;
        }

        lblOrder.setText(colOrder_id.getCellData(index).toString());
    }

    public void btnNewItemOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/view/item_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        loadFormContext.getChildren().clear();
        loadFormContext.getChildren().add(load);
    }

    public void CreateBillOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        String OID = txtOrderID_Place.getText();

        JasperDesign jasperDesign = JRXmlLoader.load("src\\main\\resources\\reports\\PlaceOrder.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String, Object> data = new HashMap<>();
        data.put("OrderID",OID);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);

    }
}
