/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamnt.daos;

import tamnt.db.MyConnection;
import tamnt.dtos.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

/**
 *
 * @author SIMON
 */
public class UserDAO {

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class);

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public UserDAO() {
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

    public String checkLogin(String userID, String password) {
        String role = "failed";
        try {
            String sql = "Select roleID, statusUserID From tblUsers Where userID = ? and password = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userID);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String status = rs.getString("statusUserID");
                if (status.equals("Active")) {
                    role = rs.getString("roleID");
                } else if (status.equals("Inactive")) {
                    role = "inactive";
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeMyConnection();
        }
        return role;
    }

    public UserDTO findByUserID(String userID) {
        UserDTO result = null;
        try {
            String sql = "Select fullName, roleID From tblUsers Where userID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String fullName = rs.getString("fullName");
                String roleID = rs.getString("roleID");
                result = new UserDTO(fullName, roleID);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeMyConnection();
        }
        return result;
    }

}
