/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamnt.daos;

import tamnt.db.MyConnection;
import tamnt.dtos.BookDTO;
import tamnt.dtos.OrderDTO;
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
public class OrderDAO {

    private static final Logger LOGGER = Logger.getLogger(OrderDAO.class);
    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public OrderDAO() {
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
            e.printStackTrace();
        }
    }

    public boolean inserOrder(OrderDTO dto) throws Exception {
        boolean result = true;
        try {
            String sql = "Insert Into tblOrders(orderID, total, orderDate, discountCode, userID) Values(?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, dto.getOrderID());
            preStm.setFloat(2, dto.getTotal());
            preStm.setDate(3, dto.getOrderDate());
            preStm.setString(4, dto.getDiscountCode());
            preStm.setString(5, dto.getUserID());
            preStm.executeUpdate();
            for (BookDTO bdto : dto.getBookList()) {
                sql = "Insert Into tblOrderDetails(quantity, price, orderID, bookID) VALUES(?,?,?,?)";
                preStm = conn.prepareStatement(sql);
                preStm.setInt(1, bdto.getQuantityCart());
                preStm.setFloat(2, bdto.getPrice());
                preStm.setInt(3, dto.getOrderID());
                preStm.setString(4, bdto.getBookID());
                preStm.executeUpdate();
                sql = "Update tblBooks Set quantity=quantity-? Where bookID = ?";
                preStm = conn.prepareStatement(sql);
                preStm.setInt(1, bdto.getQuantityCart());
                preStm.setString(2, bdto.getBookID());
                preStm.executeUpdate();
            }
        } catch (Exception e) {
            result = false;
            LOGGER.error(e);
        } finally {
            closeMyConnection();
        }
        return result;
    }

    public OrderDTO checkUsedDiscount(String userID, String discountCode) {
        OrderDTO result = null;
        try {
            String sql = "Select orderID From tblOrders Where userID = ? And discountCode = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userID);
            preStm.setString(2, discountCode);
            rs = preStm.executeQuery();
            if (rs.next()) {
                int orderID = rs.getInt("orderID");
                result = new OrderDTO(orderID);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeMyConnection();
        }
        return result;
    }

    public List<OrderDTO> historyUser(String userID) throws Exception {
        List<OrderDTO> result = null;
        OrderDTO dto;
        try {
            String sql = "select orderID, total, orderDate, discountCode From tblOrders Where userID=? Order By orderDate DESC";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userID);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                float total = rs.getFloat("total");
                Date orderDate = rs.getDate("orderDate");
                String discountCode = rs.getString("discountCode");
                dto = new OrderDTO(orderID, total, orderDate, discountCode);
                result.add(dto);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeMyConnection();
        }
        return result;
    }

    public List<OrderDTO> searchHistoryUser(String searchDate, String userID) throws Exception {
        List<OrderDTO> result = null;
        OrderDTO dto;
        try {
            String sql = "select orderID, total, orderDate, discountCode From tblOrders Where orderDate <= ? And userID=? Order By orderDate DESC";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, searchDate);
            preStm.setString(2, userID);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                float total = rs.getFloat("total");
                Date orderDate = rs.getDate("orderDate");
                String discountCode = rs.getString("discountCode");
                dto = new OrderDTO(orderID, total, orderDate, discountCode);
                result.add(dto);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeMyConnection();
        }
        return result;
    }

    public List<OrderDTO> historyAdmin() throws Exception {
        List<OrderDTO> result = null;
        OrderDTO dto;
        try {
            String sql = "select orderID, total, orderDate, discountCode, userID From tblOrders Order By orderDate DESC";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                float total = rs.getFloat("total");
                Date orderDate = rs.getDate("orderDate");
                String discountCode = rs.getString("discountCode");
                String userID = rs.getString("userID");
                dto = new OrderDTO(orderID, total, orderDate, userID, discountCode);
                result.add(dto);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeMyConnection();
        }
        return result;
    }

    public List<OrderDTO> searchHistoryAdmin(String searchDate) throws Exception {
        List<OrderDTO> result = null;
        OrderDTO dto;
        try {
            String sql = "select orderID, total, orderDate, discountCode, userID From tblOrders Where orderDate <= ? Order By orderDate DESC";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, searchDate);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                float total = rs.getFloat("total");
                Date orderDate = rs.getDate("orderDate");
                String discountCode = rs.getString("discountCode");
                String userID = rs.getString("userID");
                dto = new OrderDTO(orderID, total, orderDate, userID, discountCode);
                result.add(dto);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeMyConnection();
        }
        return result;
    }

    public OrderDTO historyDetail(int orderID) throws Exception {
        OrderDTO result = new OrderDTO();
        List<BookDTO> bookList = null;
        BookDTO dto;
        try {
            String sql = "Select B.title, B.image, D.quantity, D.price From tblOrders O Join tblOrderDetails D On O.orderID=? And O.orderID=D.orderID Join tblBooks B On D.BookID=B.BookID";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, orderID);
            rs = preStm.executeQuery();
            bookList = new ArrayList<>();
            while (rs.next()) {
                String title = rs.getString("title");
                String image = rs.getString("image");
                int quantity = rs.getInt("quantity");
                float price = rs.getFloat("price");
                dto = new BookDTO(title, image, price, quantity);
                bookList.add(dto);
            }
            result.setBookList(bookList);
            result.setOrderID(orderID);
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            closeMyConnection();
        }
        return result;
    }
}
