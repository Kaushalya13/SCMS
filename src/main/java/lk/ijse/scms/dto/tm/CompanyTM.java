package lk.ijse.scms.dto.tm;

public class CompanyTM {
    private String company_id;
    private String company_name;
    private String company_type;

    public CompanyTM() {
    }

    public CompanyTM(String company_id, String company_name, String company_type) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.company_type = company_type;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_type() {
        return company_type;
    }

    public void setCompany_type(String company_type) {
        this.company_type = company_type;
    }

    @Override
    public String toString() {
        return "CompanyTM{" +
                "company_id='" + company_id + '\'' +
                ", company_name='" + company_name + '\'' +
                ", company_type='" + company_type + '\'' +
                '}';
    }
}
