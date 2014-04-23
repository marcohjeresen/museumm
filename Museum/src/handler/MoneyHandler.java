/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package handler;
import db.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import museum2.Museum2;
/**
 *
 * @author markh_000
 */
public class MoneyHandler {
    private CashRegister cashRegister;
    private ArrayList<CashRegister> cashRegistersList;
    private StoreHandler storeHandler;
    private DifferanceRegistre differanceRegistre;
    private ArrayList<DifferanceRegistre> differanceRegistresList;
    private Listeners listeners;
    
    public MoneyHandler(StoreHandler storeHandler, Listeners listeners1) throws SQLException {
        this.storeHandler = storeHandler;
        this.listeners = listeners1;
        cashRegistersList = new ArrayList<>();
        differanceRegistresList = new ArrayList<>();
        getCashDatabase();
    }
    
    public void getCashDatabase()throws SQLException {
        DBConnection db = new DBConnection();
        try {
            ResultSet rs = db.getResult("SELECT * FROM cashregistercontent");
            while (rs.next()) {
                    for (Employee employeeList : storeHandler.getEmployeesList()) {
                    if (employeeList.getCpr() == rs.getInt("starting_employee_cpr")) {
                        CashRegister cashRegister = new CashRegister(rs.getInt("starting_id"),rs.getString("starting_date"), rs.getInt("starting_amount_dk"), rs.getInt("starting_amount_euro"), employeeList );
                        cashRegistersList.add(cashRegister);
                    }
                }
            }
            db.close();
        } catch (SQLException ex) {
            Logger.getLogger(Museum2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addStartingCashToDataBase(CashRegister register){
        DBConnection db = new DBConnection();
        try {
            db.execute("insert into cashregistercontent values ("+register.getId()+",'"+register.getDate()+"',"+register.getAmountDk()+","+register.getAmountEuro()+","+register.getEmployee().getCpr()+")");
            db.close();
        } catch (SQLException ex) {
            Logger.getLogger(Museum2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addEndCashToDatabase(DifferanceRegistre dif){
        DBConnection db = new DBConnection();
        try {
            db.execute("insert into differance values ('"+dif.getId()+"',"+dif.getEmployee().getCpr()+""
                    + ","+dif.getCurrentCashDk()+","+dif.getCurrentCahsEuro()+","+dif.getExpectedDk()+","+dif.getExpectedEuro()+""
                    + ","+dif.getDifferanceDk()+","+dif.getDifferanceEuro()+","+dif.getDate()+")");
            db.close();
        } catch (SQLException ex) {
            Logger.getLogger(Museum2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CashRegister getCashRegister() {
        return cashRegister;
    }

    public void setCashRegister(int dk, int euro) {
        int id = 0;
        Calendar cal = Calendar.getInstance();
        String dato = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
        for (CashRegister cashRegister1 : cashRegistersList) {
            if (id < cashRegister1.getId()) {
                id = cashRegister1.getId();
            }
        }
        id = id +1;
        cashRegister = new CashRegister(id,dato, dk, euro, storeHandler.getEmployee());
        addStartingCashToDataBase(cashRegister);
        listeners.notifyListeners("Cash Registre");
    }

    public ArrayList<CashRegister> getCashRegistersList() {
        return cashRegistersList;
    }

    public void setCashRegistersList(CashRegister cashRegisters) {
        cashRegistersList.add(cashRegisters);
    }
    public void addCashAmount(String type, String curency ,int amount){
        int amountDk = cashRegister.getAmountDk();
        int amountEuro = cashRegister.getAmountEuro();
        switch (type) {
            case "+":
                if (curency == "DK") {
                    cashRegister.setAmountDk(amountDk + amount);
                }else if (curency == "EURO")
                    cashRegister.setAmountEuro(amountEuro + amount);
                break;
            case "-":
                if (curency == "Dk") {
                    cashRegister.setAmountDk(amountDk - amount);
                }else if (curency == "Euro")
                    cashRegister.setAmountEuro(amountEuro - amount);
                break;
        }
        
      
    }
    
   
   
}
