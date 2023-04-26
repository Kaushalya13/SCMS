package lk.ijse.scms.model;

import lk.ijse.scms.db.DBConnection;
import lk.ijse.scms.dto.LoginrecodeDTO;
import lk.ijse.scms.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginrecodeModel {
    public static List<LoginrecodeDTO> getAll() throws SQLException ,ClassNotFoundException{
        ResultSet resultSet= CrudUtil.execute("SELECT * FROM Loginrecord");
        List<LoginrecodeDTO> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(new LoginrecodeDTO(
                    resultSet.getString(1),
                    resultSet.getDate(2).toLocalDate(),
                    resultSet.getTime(3).toLocalTime(),
                    resultSet.getString(4)
            ));
        }
        return data;
    }

    public static String generateNextLoginId() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();

        String sql = "SELECT login_id FROM loginrecode LOGINRECODE BY login_id DESC LIMIT 1";

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        if(resultSet.next()) {
            return splitLoginId(resultSet.getString(1));
        }
        return splitLoginId(null);
    }

    private static String splitLoginId(String currentLoginId) {
        if(currentLoginId != null) {
            String[] strings = currentLoginId.split("L");
            int id = Integer.parseInt(strings[1]);
            id++;
            String generated = String.format("L%03d", id);
            return generated;
        }
        return "L001";
    }
}
