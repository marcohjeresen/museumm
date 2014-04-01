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
import model.CashRegister;
import model.Employee;
import museum.Museum;

/**
 *
 * @author markh_000
 */
public class CashHandler {
    private CashRegister cr;
    private EmployeeHandler eh;
    private ArrayList<CashRegister> cashList;

    public CashHandler(EmployeeHandler eh) {
        this.eh = eh;
        cashList = new ArrayList<>();
        getDatabase();
    }
    
    public void getDatabase(){
        DBConnection db = new DBConnection();
        try {
            ResultSet rs = db.getResult("SELECT * FROM cashregistercontent");
            while (rs.next()) {
                    for (Employee employeeList : eh.getEmployeeList()) {
                    if (employeeList.getCpr() == rs.getInt("starting_employee_cpr")) {
                        cr = new CashRegister(rs.getDate("starting_date"), rs.getDouble("starting_amount_dk"), rs.getDouble("starting_amount_euro"), employeeList );
                        cashList.add(cr);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Museum.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<CashRegister> getCashList() {
        return cashList;
    }

    public void setCashList(ArrayList<CashRegister> cashList) {
        this.cashList = cashList;
    }
    
    
    
}
