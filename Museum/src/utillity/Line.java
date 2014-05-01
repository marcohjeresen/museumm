/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utillity;

import java.util.Calendar;
import model.Sale;
import model.TicketType;

/**
 *
 * @author davs
 */
public class Line {
    private String text;
    private int priceDk;
    private int priceEuro;
    private String productNumber;
    private String productTitle;
    private String productSupplier;
    private String productBuyPrice;
    private String productQuantities;
    private int day;
    private int sum;
    private int id;
    
    
    private int tkAdultQu;
    private int tkKidsQu;
    private int tkFreeQu;
    private int tkAGroupQu;
    
    private String ticketDate;
    

    public Line(String text, int priceDk, int priceEuro) {
        this.text = text;
        this.priceDk = priceDk;
        this.priceEuro = priceEuro;
    }

    public int getTkAdultQu() {
        return tkAdultQu;
    }

    public void setTkAdultQu(int tkAdultQu) {
        this.tkAdultQu = tkAdultQu;
    }

    public int getTkKidsQu() {
        return tkKidsQu;
    }

    public void setTkKidsQu(int tkKidsQu) {
        this.tkKidsQu = tkKidsQu;
    }

    public int getTkFreeQu() {
        return tkFreeQu;
    }

    public void setTkFreeQu(int tkFreeQu) {
        this.tkFreeQu = tkFreeQu;
    }

    public int getTkAGroupQu() {
        return tkAGroupQu;
    }

    public void setTkAGroupQu(int tkAGroupQu) {
        this.tkAGroupQu = tkAGroupQu;
    }

    public String getText() {
        return text;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTicketQuantities() {
        return tkAdultQu;
    }

    public void setTicketQuantities(int ticketQuantities) {
        this.tkAdultQu = ticketQuantities;
    }

    public String getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(String ticketDate) {
        this.ticketDate = ticketDate;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPriceDk() {
        return priceDk;
    }

    public void setPriceDk(int priceDk) {
        this.priceDk = priceDk;
    }

    public int getPriceEuro() {
        return priceEuro;
    }

    public void setPriceEuro(int priceEuro) {
        this.priceEuro = priceEuro;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductSupplier() {
        return productSupplier;
    }

    public void setProductSupplier(String productSupplier) {
        this.productSupplier = productSupplier;
    }

    public String getProductBuyPrice() {
        return productBuyPrice;
    }

    public void setProductBuyPrice(String productBuyPrice) {
        this.productBuyPrice = productBuyPrice;
    }

    public String getProductQuantities() {
        return productQuantities;
    }

    public void setProductQuantities(String productQuantities) {
        this.productQuantities = productQuantities;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        String text = "";
        if (priceDk == 0) {
           text = "text: " + text + " priceDk: " + priceDk + " priceEuro: " + priceEuro;
        }
        return text;
    }

    
}
