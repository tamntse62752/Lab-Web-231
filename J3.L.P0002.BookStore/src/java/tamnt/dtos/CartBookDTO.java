/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tamnt.dtos;

import java.util.HashMap;

/**
 *
 * @author SIMON
 */
public class CartBookDTO {

    private String customerName;
    private HashMap<String, BookDTO> cart;

    public void addToCart(BookDTO dto) throws Exception {
        if (this.cart.containsKey(dto.getBookID())) {
            int quantity = this.cart.get(dto.getBookID()).getQuantityCart() + 1;
            dto.setQuantityCart(quantity);
        }
        this.cart.put(dto.getBookID(), dto);
    }

    public CartBookDTO() {
        this.customerName = "GUEST";
        this.cart = new HashMap<>();
    }

    public CartBookDTO(String customerName) {
        this.customerName = customerName;
        this.cart = new HashMap<>();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public HashMap<String, BookDTO> getCart() {
        return cart;
    }

    public float getTotal() {
        float result = 0;
        for (BookDTO dto : this.cart.values()) {
            result += dto.getPrice() * dto.getQuantityCart();
        }
        return result;
    }

    public void remove(String id) throws Exception {
        if (this.cart.containsKey(id)) {
            this.cart.remove(id);
        }
    }

    public void updateCart(String id, int newQuantity) throws Exception {
        if (this.cart.containsKey(id)) {
            this.cart.get(id).setQuantityCart(newQuantity);
        }
    }

}
