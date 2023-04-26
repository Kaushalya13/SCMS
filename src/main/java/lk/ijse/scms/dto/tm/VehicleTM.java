package lk.ijse.scms.dto.tm;

public class VehicleTM {
    private String vehicle_id;
    private String vehicle_name;
    private String vehicle_type;
    private String customer_id;
    private String company_id;
    private String receive_date;
    private String return_date;

    public VehicleTM() {
    }

    public VehicleTM(String vehicle_id, String vehicle_name, String vehicle_type, String customer_id, String company_id, String receive_date, String return_date) {
        this.vehicle_id = vehicle_id;
        this.vehicle_name = vehicle_name;
        this.vehicle_type = vehicle_type;
        this.customer_id = customer_id;
        this.company_id = company_id;
        this.receive_date = receive_date;
        this.return_date = return_date;
    }

    public String getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getVehicle_name() {
        return vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
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

    @Override
    public String toString() {
        return "VehicleTM{" +
                "vehicle_id='" + vehicle_id + '\'' +
                ", vehicle_name='" + vehicle_name + '\'' +
                ", vehicle_type='" + vehicle_type + '\'' +
                ", customer_id='" + customer_id + '\'' +
                ", company_id='" + company_id + '\'' +
                ", receive_date='" + receive_date + '\'' +
                ", return_date='" + return_date + '\'' +
                '}';
    }
}

