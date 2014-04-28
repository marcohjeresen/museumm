/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author markh_000
 */
public class Product{
    private int productNumber;
    private String name;
    private ProductGroup productGroup;
    private String supplier;
    private int buyPrice;
    private int priceDk;
    private int priceEuro;
    private int discount;
    private int quantities;

    public Product(int productNumber, String name, ProductGroup groupNumber, String supplier, int buyPrice, int priceDk, int priceEuro, int discount, int quantities) {
        
        this.productNumber = productNumber;
        this.name = name;
        this.productGroup = groupNumber;
        this.supplier = supplier;
        this.buyPrice = buyPrice;
        this.priceDk = priceDk;
        this.priceEuro = priceEuro;
        this.discount = discount;
        this.quantities = quantities;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductGroup getGroupNumber() {
        return productGroup;
    }

    public void setGroupNumber(ProductGroup groupNumber) {
        this.productGroup = groupNumber;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getQuantities() {
        return quantities;
    }

    public void setQuantities(int quantities) {
        this.quantities = quantities;
    }

    @Override
    public String toString() {
        double buyPriceDk = (buyPrice / 100);
        String product = "";
        if (name.length() > 7 && name.length() > 15) {
            product = "pNumber: " + productNumber + "   name: " + name + "\t\t supplier: " + supplier + "\t buyPrice: " + buyPriceDk + ", quantities: " + quantities;
        }else if (name.length() > 15) {
            product = "pNumber: " + productNumber + "   name: " + name + "\t supplier: " + supplier + "\t buyPrice: " + buyPriceDk + ", quantities: " + quantities;
        } else{
            product = "pNumber: " + productNumber + "   name: " + name + "\t\t\t supplier: " + supplier + "\t buyPrice: " + buyPriceDk + ", quantities: " + quantities;
        }
        return product;
    }

    

       

    
    
}



