package lk.ijse.scms.model;

import lk.ijse.scms.crudUtil.CrudUtil;
import lk.ijse.scms.db.DBConnection;
import lk.ijse.scms.dto.CompanyDTO;
import lk.ijse.scms.dto.CustomerDTO;
import lk.ijse.scms.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyModel {

    public static List<CompanyDTO> getAll() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Company";

        List<CompanyDTO> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            data.add(new CompanyDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            ));
        }
        return data;
    }

    public static CompanyDTO search(String company_id) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Company WHERE company_id='" + company_id + "'");
        System.out.println(resultSet);
        if (resultSet.next()) {
            return new CompanyDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
        }
        return null;
    }
    public static ArrayList<CompanyDTO> View() throws SQLException {
        ArrayList<CompanyDTO> companyDTOArrayList = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Company");

        while (rst.next()) {
            companyDTOArrayList.add(
                    new CompanyDTO(rst.getString(1), rst.getString(2), rst.getString(3)));
        }
        return companyDTOArrayList;
    }
}
