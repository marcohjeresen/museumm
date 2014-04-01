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
 * @author markh_000
 */
public class SaleHandler {
    private Sale sale;
    private EmployeeHandler eh;
    private PaymentTypeHandler pth;
    private ArrayList<Sale> saleList;

    public SaleHandler(EmployeeHandler eh, PaymentTypeHandler pth) {
        this.eh = eh;
        this.pth = pth;
        saleList = new ArrayList<>();
        getDatabase();
    }
    
    public void getDatabase(){
        DBConnection db = new DBConnection();
        try {
            ResultSet rs = db.getResult("SELECT * FROM sale");
            while (rs.next()) {
               
                for (PaymentType payment : pth.getPtList()) {
                    if (payment.getId() == rs.getInt("sale_paymenttype_id")) {
                        for (Employee employee : eh.getEmployeeList()) {
                            if (employee.getCpr() == rs.getInt("sale_employee_cpr")) {
                                sale = new Sale(rs.getInt("sale_id"), payment, employee, rs.getDate("sale_date"));
                                saleList.add(sale);
                            }
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Museum.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Sale> getSaleList() {
        return saleList;
    }

    public void setSaleList(ArrayList<Sale> saleList) {
        this.saleList = saleList;
    }
    
}
