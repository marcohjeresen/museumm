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
    private ArrayList<Product> productList;
    private Sale sale;

    public ProductLine(int id, Sale saleId) {
        this.id = id;
        this.sale = saleId;
        productList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(Product product) {
        productList.add(product);
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale saleId) {
        this.sale = saleId;
    }
   
    
}
