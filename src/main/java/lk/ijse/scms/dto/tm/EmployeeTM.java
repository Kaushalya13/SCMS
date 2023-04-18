package lk.ijse.scms.dto.tm;

public class EmployeeTM {
    String employee_id;
    String employee_name;
    String nic;
    String address;
    String ranks;
    String contactno;

    public EmployeeTM() {
    }

    public EmployeeTM(String employee_id, String employee_name, String nic, String address, String ranks, String contactno) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.nic = nic;
        this.address = address;
        this.ranks = ranks;
        this.contactno = contactno;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRanks() {
        return ranks;
    }

    public void setRanks(String ranks) {
        this.ranks = ranks;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    @Override
    public String toString() {
        return "EmployeeTM{" +
                "employee_id='" + employee_id + '\'' +
                ", employee_name='" + employee_name + '\'' +
                ", nic='" + nic + '\'' +
                ", address='" + address + '\'' +
                ", ranks='" + ranks + '\'' +
                ", contactno='" + contactno + '\'' +
                '}';
    }
}
