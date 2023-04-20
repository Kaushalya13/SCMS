package lk.ijse.scms.dto.tm;

public class LoginTM {
    private String login_id;
    private String date;
    private String time;
    private String user_id;
    private String user_name;

    public LoginTM() {
    }

    public LoginTM(String login_id, String date, String time, String user_id, String user_name) {
        this.login_id = login_id;
        this.date = date;
        this.time = time;
        this.user_id = user_id;
        this.user_name = user_name;
    }

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    @Override
    public String toString() {
        return "LoginTM{" +
                "login_id='" + login_id + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                '}';
    }
}
