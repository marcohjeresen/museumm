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
import model.PaymentType;
import model.Product;
import model.ProductGroup;
import museum.Museum;

/**
 *
 * @author markh_000
 */
public class PaymentTypeHandler {
    private PaymentType pt;
    private ArrayList<PaymentType> ptList;

    public PaymentTypeHandler() {
        ptList = new ArrayList<>();
        getDatebase();
    }
    
    public void getDatebase(){
        DBConnection db = new DBConnection();
        try {
            ResultSet rs = db.getResult("SELECT * FROM paymenttype");
            while (rs.next()) {
                pt = new PaymentType(rs.getInt("paymenttype_id"), rs.getString("paymenttype_type"), rs.getDouble("paymenttype_fee"));
                ptList.add(pt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Museum.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<PaymentType> getPtList() {
        return ptList;
    }

    public void setPtList(ArrayList<PaymentType> ptList) {
        this.ptList = ptList;
    }
    
}
