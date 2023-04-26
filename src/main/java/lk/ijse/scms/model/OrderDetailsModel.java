package lk.ijse.scms.model;

import lk.ijse.scms.db.DBConnection;
import lk.ijse.scms.dto.CartDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailsModel {

    public static boolean save(String oId, List<CartDTO> cartDTOList) throws SQLException {
        for (CartDTO cartDTO : cartDTOList) {
            if (!save(oId, cartDTO)) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(String oId, CartDTO dto) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        double total = dto.getQty() * dto.getUnitPrice();
        String sql = "INSERT INTO orderDetails(order_id, itemCode, unitPrice, quantity,total) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, oId);
        pstm.setString(2, dto.getItemCode());
        pstm.setDouble(3, dto.getUnitPrice());
        pstm.setInt(4, dto.getQty());
        pstm.setDouble(5, total);

        return pstm.executeUpdate() > 0;

    }
}
