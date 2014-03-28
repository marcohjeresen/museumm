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
public class Product extends ProductGroup{
    private int productNumber;
    private String name;
    private ProductGroup productGroup;
    private String supplier;
    private double buyPrice;
    private double priceDk;
    private double priceEuro;
    private double discount;
    private int quantities;

    public Product(int productNumber, String name, ProductGroup groupNumber, String supplier, double buyPrice, double priceDk, double priceEuro, double discount, int quantities, int groupId, String groupType) {
        super(groupId, groupType);
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

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getPriceDk() {
        return priceDk;
    }

    public void setPriceDk(double priceDk) {
        this.priceDk = priceDk;
    }

    public double getPriceEuro() {
        return priceEuro;
    }

    public void setPriceEuro(double priceEuro) {
        this.priceEuro = priceEuro;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getQuantities() {
        return quantities;
    }

    public void setQuantities(int quantities) {
        this.quantities = quantities;
    }
    
}



