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
import model.Customer;
import model.PaymentType;
import museum.Museum;

/**
 *
 * @author markh_000
 */
public class CustomerHandler {
    private Customer ct;
    private ArrayList<Customer> ctList;

    public CustomerHandler() {
        ctList = new ArrayList<>();
        getDatebase();
    }
    
    public void getDatebase(){
        DBConnection db = new DBConnection();
        try {
            ResultSet rs = db.getResult("SELECT * FROM customer");
            while (rs.next()) {
                ct = new Customer(rs.getString("customer_name"), rs.getInt("customer_phone"));
                ctList.add(ct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Museum.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Customer> getCtList() {
        return ctList;
    }

    public void setCtList(ArrayList<Customer> ctList) {
        this.ctList = ctList;
    }
    
}
