/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utillity;

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

    public Line(String text, int priceDk, int priceEuro) {
        this.text = text;
        this.priceDk = priceDk;
        this.priceEuro = priceEuro;
    }

    public String getText() {
        return text;
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
