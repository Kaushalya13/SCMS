package lk.ijse.scms.model;

import lk.ijse.scms.db.DBConnection;
import lk.ijse.scms.dto.*;
import lk.ijse.scms.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleModel {
    public static List<VehicleDTO> getAll() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Vehicle";

        List<VehicleDTO> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            data.add(new VehicleDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            ));
        }
        return data;
    }

    public static ArrayList<VehicleDTO> View() throws SQLException {
        ArrayList<VehicleDTO> vehicleDTOArrayList = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Vehicle");

        while (rst.next()) {
            vehicleDTOArrayList.add(
                    new VehicleDTO(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6),rst.getString(7),rst.getString(8)));
        }
        return vehicleDTOArrayList;
    }

    public static VehicleDTO search(String vehicle_id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Vehicle WHERE vehicle_id='" + vehicle_id + "'");
        System.out.println(resultSet);
        if (resultSet.next()) {
            return new VehicleDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            );
        }
        return null;
    }

}
