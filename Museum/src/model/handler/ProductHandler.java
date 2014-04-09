/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.handler;

import db.DBConnection;
import java.awt.event.ActionListener;
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
    private SaleHandler saleh;
    private ArrayList<Product> specList;
    private Listeners listners;

    public ProductHandler(SaleHandler saleh, Listeners listeners) {
        this.listners = listeners;
        this.saleh = saleh;
        productList = new ArrayList<>();
        productLineList = new ArrayList<>();
        groupList = new ArrayList<>();
        specList = new ArrayList<>();
        getDatabase();
        addLineToSale();
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
                        Product pr = new Product(rse.getInt("product_numberid"), rse.getString("product_name"), group, rse.getString("product_supplier"),
                                rse.getDouble("product_buyprice"), rse.getDouble("product_saleprice_dk"), rse.getDouble("product_saleprice_euro"),
                                rse.getDouble("product_discount"), rse.getInt("product_quantities"));
                        productList.add(pr);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Museum.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ResultSet rse = db.getResult("SELECT * FROM productline");
            while (rse.next()) {
                for (Sale sale : saleh.getSaleList()) {
                    if (sale.getId() == rse.getInt("productline_sale_id")) {

                        for (Product product : productList) {
                            if (product.getProductNumber() == rse.getInt("productline_product_id")) {
                                pl = new ProductLine(rse.getInt("productline_id"), sale, product, rse.getInt("productline_quantities"));

                                productLineList.add(pl);

                            }
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Museum.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addLineToSale() {
        for (Sale sale : saleh.getSaleList()) {
            for (ProductLine productLine : productLineList) {
                if (sale == productLine.getSale()) {

                    sale.setPl(productLine);
                }
            }
        }
    }

    public void opretProductLine(Product product, int quantities) {
        int idnumer = 0;
        boolean erder = false;
        for (int i = 0; i < saleh.getSale().getPl().size(); i++) {
            if (saleh.getSale().getPl().get(i).getProduct().equals(product)) {
                int quantitiess = saleh.getSale().getPl().get(i).getQuantities();
                saleh.getSale().getPl().get(i).setQuantities(quantities + quantitiess);
                erder = true;
                idnumer = saleh.getSale().getPl().get(i).getId();

            }
        }

        if (!erder) {
            for (ProductLine productLine : productLineList) {
                if (productLine.getId() > idnumer) {
                    idnumer = productLine.getId();
                }
            }
            idnumer = idnumer + 1;
            pl = new ProductLine(idnumer, saleh.getSale(), product, quantities);
            saleh.addProductLine(pl);
            productLineList.add(pl);

        } else if (erder) {
            for (int i = 0; i < productLineList.size(); i++) {
                if (productLineList.get(i).getId() == idnumer) {
                    int antal = productLineList.get(i).getQuantities();
                    productLineList.get(i).setQuantities(quantities + antal);
                }

            }
        }

    }

    public void setSpecProductList(ProductGroup group) {
        specList.removeAll(specList);

        for (Product product : productList) {
            if (product.getGroupNumber() == group) {
                specList.add(product);
            }
        }
        listners.notifyListeners("Update view");
    }

    public void clearSpecProductList() {
        specList.removeAll(specList);
    }

    public void searchProduct(int productnumber) {
        specList.removeAll(specList);

        for (Product product : productList) {
            if (product.getProductNumber() == productnumber) {
                specList.add(product);
                listners.notifyListeners("Update view");
            }
        }

    }

    public void addListener(ActionListener listener) {
        listners.addListener(listener);

    }

    public ArrayList<Product> getSpecList() {

        return specList;
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
