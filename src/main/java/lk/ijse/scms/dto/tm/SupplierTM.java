package lk.ijse.scms.dto.tm;

public class SupplierTM {
    private String supplier_id;
    private String supplier_name;
    private String address;
    private String email;
    private String contactno;

    public SupplierTM() {
    }

    public SupplierTM(String supplier_id, String supplier_name, String address, String email, String contactno) {
        this.supplier_id = supplier_id;
        this.supplier_name = supplier_name;
        this.address = address;
        this.email = email;
        this.contactno = contactno;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
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
        return "SupplierTM{" +
                "supplier_id='" + supplier_id + '\'' +
                ", supplier_name='" + supplier_name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", contactno='" + contactno + '\'' +
                '}';
    }
}

