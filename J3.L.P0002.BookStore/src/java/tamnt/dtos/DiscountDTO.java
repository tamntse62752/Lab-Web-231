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
public class DiscountDTO {
    
    private String discountCode;
    private float discountPercent;
    private Date creatediscountDate;
    private boolean statusDiscount;

    public DiscountDTO(float discountPercent) {
        this.discountPercent = discountPercent;
    }

    public DiscountDTO(String discountCode, float discountPercent, Date creatediscountDate, boolean statusDiscount) {
        this.discountCode = discountCode;
        this.discountPercent = discountPercent;
        this.creatediscountDate = creatediscountDate;
        this.statusDiscount = statusDiscount;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public float getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(float discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Date getCreatediscountDate() {
        return creatediscountDate;
    }

    public void setCreatediscountDate(Date creatediscountDate) {
        this.creatediscountDate = creatediscountDate;
    }

    public boolean isStatusDiscount() {
        return statusDiscount;
    }

    public void setStatusDiscount(boolean statusDiscount) {
        this.statusDiscount = statusDiscount;
    }
    
}
