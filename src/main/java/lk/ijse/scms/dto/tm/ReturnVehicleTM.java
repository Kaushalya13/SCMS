package lk.ijse.scms.dto.tm;

import javafx.scene.control.Button;

public class ReturnVehicleTM {
    private String vehicle_id;
    private String customer_id;
    private String receive_date;
    private String return_date;
    private Double unitPrice;
    private Button btn;

    public ReturnVehicleTM() {
    }

    public ReturnVehicleTM(String vehicle_id, String customer_id, String receive_date, String return_date, Double unitPrice, Button btn) {
        this.vehicle_id = vehicle_id;
        this.customer_id = customer_id;
        this.receive_date = receive_date;
        this.return_date = return_date;
        this.unitPrice = unitPrice;
        this.btn = btn;
    }

    public String getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getReceive_date() {
        return receive_date;
    }

    public void setReceive_date(String receive_date) {
        this.receive_date = receive_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "ServiceTM{" +
                "vehicle_id='" + vehicle_id + '\'' +
                ", customer_id='" + customer_id + '\'' +
                ", receive_date='" + receive_date + '\'' +
                ", return_date='" + return_date + '\'' +
                ", unitPrice=" + unitPrice +
                ", btn=" + btn +
                '}';
    }
}
