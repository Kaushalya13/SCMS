package lk.ijse.scms.model;

import lk.ijse.scms.db.DBConnection;
import lk.ijse.scms.dto.CartDTO;
import lk.ijse.scms.dto.CustomerDTO;
import lk.ijse.scms.dto.EmployeeDTO;
import lk.ijse.scms.dto.ItemDTO;
import lk.ijse.scms.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemModel {
    public static List<ItemDTO> getAll() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Item";

        List<ItemDTO> data = new ArrayList<>();

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            data.add(new ItemDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            ));
        }
        return data;
    }

    public static ItemDTO search(String itemCode) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Item WHERE itemCode='" + itemCode + "'");
        System.out.println(resultSet);
        if (resultSet.next()) {
            return new ItemDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            );
        }
        return null;
    }

    public static ArrayList<ItemDTO> View() throws SQLException {
        ArrayList<ItemDTO> itemDTOArrayList = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Item");

        while (rst.next()) {
            itemDTOArrayList.add(
                    new ItemDTO(rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getInt(4)));
        }
        return itemDTOArrayList;
    }

    public static boolean updateQty(List<CartDTO> cartDTOList) throws SQLException {
        for (CartDTO dto : cartDTOList) {
            if(!updateQty(dto)) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(CartDTO dto) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Item SET qtyOnStock = (qtyOnStock - ?) WHERE itemCode = ?";

        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1, dto.getQty());
        pstm.setString(2, dto.getItemCode());

        return pstm.executeUpdate() > 0;
    }
}
