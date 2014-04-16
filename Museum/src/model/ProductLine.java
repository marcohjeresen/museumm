/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;

/**
 *
 * @author markh_000
 */
public class ProductLine {
    private int id;
    private Product product;
    private Sale sale;
    private int quantities;

    public ProductLine(int id, Sale saleId, Product product1, int quantities) {
        this.id = id;
        this.sale = saleId;
        this.product = product1; 
        this.quantities = quantities;
    }

    public int getQuantities() {
        return quantities;
    }

    public void setQuantities(int quantities) {
        this.quantities = quantities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale saleId) {
        this.sale = saleId;
    }

    @Override
    public String toString() {
        String productLine = "Sale Number: "+sale.getId()+"\n";
            productLine = productLine + product.toString()+ "\n";
        return productLine;
    }
    
    
   
    
}
