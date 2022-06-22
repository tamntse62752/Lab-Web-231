/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamnt.daos;

import tamnt.db.MyConnection;
import tamnt.dtos.DiscountDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

/**
 *
 * @author SIMON
 */
public class DiscountDAO {

    private static final Logger LOGGER = Logger.getLogger(DiscountDAO.class);

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public DiscountDAO() {
    }

    private void closeMyConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public DiscountDTO findByDiscountCode(String discountCode) {
        DiscountDTO result = null;
        try {
            String sql = "Select discountPercent From tblDiscounts Where discountCode = ? And statusDiscount = 1";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, discountCode);
            rs = preStm.executeQuery();
            if (rs.next()) {
                float discountPercent = rs.getFloat("discountPercent");
                result = new DiscountDTO(discountPercent);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeMyConnection();
        }
        return result;
    }
    
    public boolean updateStatusDiscount(String discountCode) throws Exception {
        boolean check = false;
        try {
            String sql = "Update tblDiscounts set statusDiscount = 0 Where discountCode = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, discountCode);
            check = preStm.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeMyConnection();
        }
        return check;
    }

    public boolean createDiscount(DiscountDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert Into tblDiscounts(discountCode, discountPercent, creatediscountDate, statusDiscount) Values(?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getDiscountCode());
            preStm.setFloat(2, dto.getDiscountPercent());
            preStm.setDate(3, dto.getCreatediscountDate());
            preStm.setBoolean(4, dto.isStatusDiscount());
            check = preStm.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeMyConnection();
        }
        return check;
    }

}
