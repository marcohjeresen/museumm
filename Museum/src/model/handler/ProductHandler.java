/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.handler;

import db.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import museum.Museum;

/**
 *
 * @author MarcoPc
 */
public class ProductHandler {

    private Product pr;
    private ProductLine pl;
    private ArrayList<Product> productList;
    private ArrayList<ProductLine> productLineList;
    private ArrayList<ProductGroup> groupList;

    public ProductHandler() {
        productList = new ArrayList<>();
        productLineList = new ArrayList<>();
        groupList = new ArrayList<>();
        getDatabase();
    }

    public void getDatabase() {
        DBConnection db = new DBConnection();
        try {
            ResultSet rs = db.getResult("SELECT * FROM productgroup");
            while (rs.next()) {
                ProductGroup pg = new ProductGroup(rs.getInt("productgroup_id"), rs.getString("productgroup_type"));
                groupList.add(pg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Museum.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ResultSet rse = db.getResult("SELECT * FROM product");
            while (rse.next()) {
                for (ProductGroup group : groupList) {
                    if (group.getGroupId() == rse.getInt("product_groupid")) {
                        Product pr = new Product(rse.getInt("product_numberid"), rse.getString("product_name"), group, rse.getString("product_supplier"), rse.getDouble("product_buyprice"), rse.getDouble("product_saleprice_dk"), rse.getDouble("product_saleprice_euro"), rse.getDouble("product_discount"), rse.getInt("product_quantities"));
                        productList.add(pr);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Museum.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public ArrayList<ProductLine> getProductLineList() {
        return productLineList;
    }

    public void setProductLineList(ArrayList<ProductLine> productLineList) {
        this.productLineList = productLineList;
    }

    public ArrayList<ProductGroup> getGroupList() {
        return groupList;
    }

    public void setGroupList(ArrayList<ProductGroup> groupList) {
        this.groupList = groupList;
    }
    

}
