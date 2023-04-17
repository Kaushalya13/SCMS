package lk.ijse.scms.dto.tm;

public class CustomerTM {
    String custId;
    String custName;
    String nic;
    String address;
    String email;
    String contactno;

    public CustomerTM() {
    }

    public CustomerTM(String custId, String custName, String nic, String address, String email, String contactno) {
        this.custId = custId;
        this.custName = custName;
        this.nic = nic;
        this.address = address;
        this.email = email;
        this.contactno = contactno;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    @Override
    public String toString() {
        return "CustomerTM{" +
                "custId='" + custId + '\'' +
                ", custName='" + custName + '\'' +
                ", nic='" + nic + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", contactno='" + contactno + '\'' +
                '}';
    }
}

