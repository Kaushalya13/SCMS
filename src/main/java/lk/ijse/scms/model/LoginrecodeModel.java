package lk.ijse.scms.model;

import lk.ijse.scms.dto.LoginrecodeDTO;
import lk.ijse.scms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginrecodeModel {
    public static List<LoginrecodeDTO> getAll() throws SQLException {
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

}
