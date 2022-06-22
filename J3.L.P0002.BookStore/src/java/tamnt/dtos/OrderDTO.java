/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamnt.dtos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SIMON
 */
public class OrderDTO {

    private int orderID;
    private float total;
    private Date orderDate;
    private String userID, discountCode;

    List<BookDTO> bookList;

    public OrderDTO() {
        this.bookList = new ArrayList<>();
    }
    
    public OrderDTO(int orderID) {
        this.orderID = orderID;
    }

    public OrderDTO(int orderID, float total, Date orderDate, String discountCode) {
        this.orderID = orderID;
        this.total = total;
        this.orderDate = orderDate;
        this.discountCode = discountCode;
    }
        
    public OrderDTO(int orderID, float total, Date orderDate, String userID, String discountCode) {
        this.orderID = orderID;
        this.total = total;
        this.orderDate = orderDate;
        this.userID = userID;
        this.discountCode = discountCode;
        this.bookList = new ArrayList<>();
    }
    
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public List<BookDTO> getBookList() {
        return bookList;
    }

    public void setBookList(List<BookDTO> bookList) {
        this.bookList = bookList;
    }

}
