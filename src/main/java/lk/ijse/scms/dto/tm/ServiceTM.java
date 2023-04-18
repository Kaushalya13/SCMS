package lk.ijse.scms.dto.tm;

public class ServiceTM {
    String service_job_id;
    String date;
    String type;
    String customer_id;

    public ServiceTM() {
    }

    public ServiceTM(String service_job_id, String date, String type, String customer_id) {
        this.service_job_id = service_job_id;
        this.date = date;
        this.type = type;
        this.customer_id = customer_id;
    }

    public String getService_job_id() {
        return service_job_id;
    }

    public void setService_job_id(String service_job_id) {
        this.service_job_id = service_job_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    @Override
    public String toString() {
        return "ServiceTM{" +
                "service_job_id='" + service_job_id + '\'' +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", customer_id='" + customer_id + '\'' +
                '}';
    }
}
