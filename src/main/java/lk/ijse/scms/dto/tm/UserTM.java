package lk.ijse.scms.dto.tm;

public class UserTM {
    private String user_id;
    private String user_name;
    private String password;
    private String ranks;
    private String email;
    private String nic;
    private String contactno;

    public UserTM() {
    }

    public UserTM(String user_id, String user_name, String password, String ranks, String email, String nic, String contactno) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.ranks = ranks;
        this.email = email;
        this.nic = nic;
        this.contactno = contactno;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRanks() {
        return ranks;
    }

    public void setRanks(String ranks) {
        this.ranks = ranks;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    @Override
    public String toString() {
        return "UserTM{" +
                "user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", ranks='" + ranks + '\'' +
                ", email='" + email + '\'' +
                ", nic='" + nic + '\'' +
                ", contactno='" + contactno + '\'' +
                '}';
    }
}
