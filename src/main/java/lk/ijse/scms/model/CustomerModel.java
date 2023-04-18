package lk.ijse.scms.model;

import lk.ijse.scms.crudUtil.CrudUtil;
import lk.ijse.scms.db.DBConnection;
import lk.ijse.scms.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {
    public static List<CustomerDTO> getAll() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Customer";

        List<CustomerDTO> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            data.add(new CustomerDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return data;
    }

    public static CustomerDTO search(String customer_id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Customer WHERE customer_id='" + customer_id + "'");
        System.out.println(resultSet);
        if (resultSet.next()) {
            return new CustomerDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
        }
    return null;
    }

    public static ArrayList<CustomerDTO> View() throws SQLException {
        ArrayList<CustomerDTO> customerDTOArrayList = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Customer");

        while (rst.next()) {
            customerDTOArrayList.add(
                    new CustomerDTO(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6)));
        }
        return customerDTOArrayList;
    }
}
