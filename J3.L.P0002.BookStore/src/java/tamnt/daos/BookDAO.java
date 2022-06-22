/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamnt.daos;

import tamnt.db.MyConnection;
import tamnt.dtos.BookDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author SIMON
 */
public class BookDAO {

    private static final Logger LOGGER = Logger.getLogger(BookDAO.class);

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public BookDAO() {
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

    public List<BookDTO> bookList() throws Exception {
        List<BookDTO> result = null;
        BookDTO dto;
        try {
            String sql = "Select bookID, title, image, description, price, author, quantity, createBookDate, categoryID From tblBooks Where quantity > 0 And statusBookID = 'Active'";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String bookID = rs.getString("bookID");
                String title = rs.getString("title");
                String image = rs.getString("image");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                String author = rs.getString("author");
                int quantity = rs.getInt("quantity");
                Date createBookDate = rs.getDate("createBookDate");
                String categoryID = rs.getString("categoryID");
                dto = new BookDTO(bookID, title, image, description, author, categoryID, price, quantity, createBookDate);
                result.add(dto);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeMyConnection();
        }
        return result;
    }

    public List<BookDTO> searchBook(String searchCategory, float searchPrice, String searchName) throws Exception {
        List<BookDTO> result = null;
        BookDTO dto;
        try {
            String sql = "Select bookID, title, image, description, price, author, quantity, createBookDate, categoryID From tblBooks Where categoryID = ? And price <= ? And title Like ? And quantity > 0 And statusBookID = 'Active'";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, searchCategory);
            preStm.setFloat(2, searchPrice);
            preStm.setString(3, "%" + searchName + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String bookID = rs.getString("bookID");
                String title = rs.getString("title");
                String image = rs.getString("image");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                String author = rs.getString("author");
                int quantity = rs.getInt("quantity");
                Date createBookDate = rs.getDate("createBookDate");
                String categoryID = rs.getString("categoryID");
                dto = new BookDTO(bookID, title, image, description, author, categoryID, price, quantity, createBookDate);
                result.add(dto);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeMyConnection();
        }
        return result;
    }

    public boolean deleteBook(String bookID) throws Exception {
        boolean check = false;
        try {
            String sql = "Update tblBooks set statusBookID = 'Inactive' Where bookID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, bookID);
            check = preStm.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeMyConnection();
        }
        return check;
    }

    public boolean updateBook(BookDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Update tblBooks set title = ?, image = ?, description = ?, price = ?, author = ?, quantity = ?, createBookDate = ?, categoryID = ? Where bookID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getTitle());
            preStm.setString(2, dto.getImage());
            preStm.setString(3, dto.getDescription());
            preStm.setFloat(4, dto.getPrice());
            preStm.setString(5, dto.getAuthor());
            preStm.setInt(6, dto.getQuantity());
            preStm.setDate(7, dto.getCreateBookDate());
            preStm.setString(8, dto.getCategoryID());
            preStm.setString(9, dto.getBookID());
            check = preStm.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeMyConnection();
        }
        return check;
    }
    
    public boolean createBook(BookDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert Into tblBooks(bookID, title, image, description, price, author, quantity, createBookDate, statusBookID, categoryID) Values(?,?,?,?,?,?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getBookID());
            preStm.setString(2, dto.getTitle());
            preStm.setString(3, dto.getImage());
            preStm.setString(4, dto.getDescription());
            preStm.setFloat(5, dto.getPrice());
            preStm.setString(6, dto.getAuthor());
            preStm.setInt(7, dto.getQuantity());
            preStm.setDate(8, dto.getCreateBookDate());
            preStm.setString(9, dto.getStatusBookID());
            preStm.setString(10, dto.getCategoryID());
            check = preStm.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeMyConnection();
        }
        return check;
    }
    
    public BookDTO checkBookID(String bookID) {
        BookDTO result = null;
        try {
            String sql = "Select title From tblBooks Where bookID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, bookID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String title = rs.getString("title");
                result = new BookDTO(title);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeMyConnection();
        }
        return result;
    }

}
