/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamnt.daos;

import tamnt.db.MyConnection;
import tamnt.dtos.CategoryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author SIMON
 */
public class CategoryDAO {
    
    private static final Logger LOGGER = Logger.getLogger(CategoryDAO.class);

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public CategoryDAO() {
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
    
    public List<CategoryDTO> categoryList() throws Exception {
        List<CategoryDTO> result = null;
        CategoryDTO dto;
        try {
            String sql = "Select categoryID, categoryName From tblCategories";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String categoryID = rs.getString("categoryID");
                String categoryName = rs.getString("categoryName");
                dto = new CategoryDTO(categoryID, categoryName);
                result.add(dto);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeMyConnection();
        }
        return result;
    }
    
}
