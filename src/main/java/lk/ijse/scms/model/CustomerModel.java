package lk.ijse.scms.model;

import lk.ijse.scms.db.DBConnection;
import lk.ijse.scms.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {
     public static List<CustomerDTO>getAll() throws SQLException {
         Connection con = DBConnection.getInstance().getConnection();
         String sql = "SELECT * FROM Customer";

         List<CustomerDTO> data = new ArrayList<>();

         ResultSet resultSet = con.createStatement().executeQuery(sql);
         while (resultSet.next()){
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

    /* public static List<String> getIds() throws SQLException {
         Connection con = DBConnection.getInstance().getConnection();
         String sql = "SELECT customer_id FROM Customer";

         List<String> ids = new ArrayList<>();

         ResultSet resultSet = con.createStatement().executeQuery(sql);
         while (resultSet.next()){
             ids.add(resultSet.getString(1));
         }
         return ids;
     }

     public static CustomerDTO searchById(String custId) throws SQLException {
         Connection con = DBConnection.getInstance().getConnection();
         String sql = "SELECT * FROM Customer WHERE customer_id = ?";
         PreparedStatement pstm = con.prepareStatement(sql);
         pstm.setString(1,custId);

         ResultSet resultSet = pstm.executeQuery();
         if (resultSet.next()){
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
     }*/
}
