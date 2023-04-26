package lk.ijse.scms.dto;

public class ReturnVehicleDTO {
    private String vehicle_id;
    private String service_id;
    private String receive_date;
    private Double service_price;
    private String return_date;

    public ReturnVehicleDTO() {
    }

    public ReturnVehicleDTO(String vehicle_id, String service_id, String receive_date, Double service_price, String return_date) {
        this.vehicle_id = vehicle_id;
        this.service_id = service_id;
        this.receive_date = receive_date;
        this.service_price = service_price;
        this.return_date = return_date;
    }

    public String getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getReceive_date() {
        return receive_date;
    }

    public void setReceive_date(String receive_date) {
        this.receive_date = receive_date;
    }

    public Double getService_price() {
        return service_price;
    }

    public void setService_price(Double service_price) {
        this.service_price = service_price;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    @Override
    public String toString() {
        return "ServiceDTO{" +
                " vehicle_id='" + vehicle_id + '\'' +
                ", service_id='" + service_id + '\'' +
                ", receive_date='" + receive_date + '\'' +
                ", service_price=" + service_price +
                ", return_date='" + return_date + '\'' +
                '}';
    }
}
