/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamnt.dtos;

import java.sql.Date;

/**
 *
 * @author SIMON
 */
public class BookDTO {
    
    private String bookID, title, image, description, author, statusBookID, categoryID;
    private float price;
    private int quantity, quantityCart = 1;
    private Date createBookDate;

    public BookDTO(String title) {
        this.title = title;
    }
    
    public BookDTO(String title, String image, float price, int quantityCart) {
        this.title = title;
        this.image = image;
        this.price = price;
        this.quantityCart = quantityCart;
    }
    
    public BookDTO(String bookID, String title, String image, float price, int quantity) {
        this.bookID = bookID;
        this.title = title;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }

    public BookDTO(String bookID, String title, String image, String description, String author, String categoryID, float price, int quantity, Date createBookDate) {
        this.bookID = bookID;
        this.title = title;
        this.image = image;
        this.description = description;
        this.author = author;
        this.categoryID = categoryID;
        this.price = price;
        this.quantity = quantity;
        this.createBookDate = createBookDate;
    }

    public BookDTO(String bookID, String title, String image, String description, String author, String statusBookID, String categoryID, float price, int quantity, Date createBookDate) {
        this.bookID = bookID;
        this.title = title;
        this.image = image;
        this.description = description;
        this.author = author;
        this.statusBookID = statusBookID;
        this.categoryID = categoryID;
        this.price = price;
        this.quantity = quantity;
        this.createBookDate = createBookDate;
    }
    
    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatusBookID() {
        return statusBookID;
    }

    public void setStatusBookID(String statusBookID) {
        this.statusBookID = statusBookID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantityCart() {
        return quantityCart;
    }

    public void setQuantityCart(int quantityCart) {
        this.quantityCart = quantityCart;
    }

    public Date getCreateBookDate() {
        return createBookDate;
    }

    public void setCreateBookDate(Date createBookDate) {
        this.createBookDate = createBookDate;
    }
    
}
