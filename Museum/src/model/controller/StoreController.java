/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.controller;

import db.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;
import model.EventType;
import model.Product;
import model.ProductGroup;
import model.ProductLine;
import model.TicketType;
import model.handler.StoreHandler;
import museum2.Museum2;

/**
 *
 * @author MarcoPc
 */
public class StoreController {
private static StoreController storeController;

    private StoreController() throws SQLException {
        StoreHandler storeHandler = StoreHandler.getStoreHandler();
        getProductData(storeHandler.getProductGroupList(), storeHandler.getProductsList());
        getEventData(storeHandler.getEventTypesList());
        getTicketData(storeHandler.getTicketTypesList());
        getEmployeeData(storeHandler.getEmployeesList());
    }
    
    public static StoreController getStoreController() throws SQLException{
        if (storeController == null) {
            storeController = new StoreController();
        }
        return storeController;
    }
    private void getProductData(ArrayList<ProductGroup> productGroupList, ArrayList<Product> productsList) throws SQLException {
        DBConnection db = new DBConnection();
        try {
            ResultSet rs = db.getResult("SELECT * FROM productgroup");
            while (rs.next()) {
                ProductGroup productGroup = new ProductGroup(rs.getInt("productgroup_id"), rs.getString("productgroup_type"));
                productGroupList.add(productGroup);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Museum2.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ResultSet rse = db.getResult("SELECT * FROM product");
            while (rse.next()) {
                for (ProductGroup group : productGroupList) {
                    if (group.getGroupId() == rse.getInt("product_groupid")) {
                        Product pr = new Product(rse.getInt("product_numberid"), rse.getString("product_name"), group, rse.getString("product_supplier"),
                                rse.getInt("product_buyprice"), rse.getInt("product_saleprice_dk"), rse.getInt("product_saleprice_euro"),
                                rse.getInt("product_discount"), rse.getInt("product_quantities"));
                        productsList.add(pr);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Museum2.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.close();
    }
    
    public void getTicketData(ArrayList<TicketType> ticketTypesList ) throws SQLException {
        
        DBConnection db = new DBConnection();
        try {
            ResultSet rs = db.getResult("SELECT * FROM tickettype");
            while (rs.next()) {
                TicketType ticketType = new TicketType(rs.getInt("tickettype_id"), rs.getString("tickettype_type"), rs.getInt("tickettype_pricedk"), rs.getInt("tickettype_priceeuro"));
                ticketTypesList.add(ticketType);
            }
            db.close();
        } catch (SQLException ex) {
            Logger.getLogger(Museum2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getEventData(ArrayList<EventType> eventTypesList) throws SQLException {
        DBConnection db = new DBConnection();
        try {
            ResultSet rs = db.getResult("SELECT * FROM eventtype");
            while (rs.next()) {
                EventType eventType = new EventType(rs.getInt("eventtype_id"), rs.getString("eventtype_type"), rs.getInt("eventtype_pricedk"),
                        rs.getInt("eventtype_priceeuro"));
                eventTypesList.add(eventType);
            }
            db.close();
        } catch (SQLException ex) {
            Logger.getLogger(Museum2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void getEmployeeData(ArrayList<Employee> employeesList) throws SQLException {
        DBConnection db = new DBConnection();
        try {
            ResultSet rs = db.getResult("SELECT * FROM employee");
            while (rs.next()) {
                Employee employee = new Employee(rs.getInt("employee_cpr"), rs.getString("employee_name"), rs.getString("employee_adresse"),
                        rs.getInt("employee_postzip"), rs.getString("employee_city"), rs.getInt("employee_password"));
                employeesList.add(employee);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Museum2.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ResultSet rse = db.getResult("SELECT * FROM employeephone");
            while (rse.next()) {
                for (Employee employee : employeesList) {
                    if (employee.getCpr() == rse.getInt("employeephone_cpr")) {
                        employee.setPhoneList(rse.getInt("employeephone_phone"));
                    }
                }
            }
            db.close();
        } catch (SQLException ex) {
            Logger.getLogger(Museum2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void alterProductQuantities(ArrayList<ProductLine> productLines, ArrayList<Product> productsList) throws SQLException {
        int stock = 0;
        DBConnection db = new DBConnection();
 
        for (int i = 0; i < productsList.size(); i++) {
            for (ProductLine productLine : productLines) {
                if (productsList.get(i).equals(productLine.getProduct())) {
                    
                    stock = productsList.get(i).getQuantities() - productLine.getQuantities();
                    productsList.get(i).setQuantities(stock);
                    db.execute("update product set product_quantities = " + stock + " where product_numberid = " + productsList.get(i).getProductNumber() + ";");
                }
            }
            
        }
    }
}
