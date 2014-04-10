/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.handler;

import db.DBConnection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import model.handler.EmployeeHandler;
import model.handler.PaymentTypeHandler;
import museum.Museum;
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
    private int id;

    public SaleHandler(EmployeeHandler eh, PaymentTypeHandler pth) {
        this.eh = eh;
        this.pth = pth;
        saleList = new ArrayList<>();
        int id = 0;
        getDatabase();
    }

    public void getDatabase() {
        DBConnection db = new DBConnection();
        try {
            ResultSet rs = db.getResult("SELECT * FROM sale");
            while (rs.next()) {

                for (PaymentType payment : pth.getPtList()) {
                    if (payment.getId() == rs.getInt("sale_paymenttype_id")) {
                        for (Employee employee : eh.getEmployeeList()) {
                            if (employee.getCpr() == rs.getInt("sale_employee_cpr")) {
                                sale = new Sale(rs.getInt("sale_id"), payment, employee, rs.getString("sale_date"));
                                saleList.add(sale);
                            }
                        }
                    }
                }
            }
            db.close();
        } catch (SQLException ex) {
            Logger.getLogger(Museum.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void opretSale(Employee employee) {
        id = 0;
        for (Sale sale : getSaleList()) {
            if (sale.getId() > id) {
                id = sale.getId();
            }
        }
        
        id = id + 1;
        sale = new Sale(id, null, employee, null);
        saleList.add(sale);
        
    }

    public Sale getSale() {
        return sale;
    }

    public void clearSaleKurv() {
        sale.clearTl();
        sale.clearPl();
        sale.clearEl();
    }

    public void clearSale() {
        saleList.remove(sale);
        sale = null;
    }

    public void addEventLine(EventLine eventLine) {
        sale.setEl(eventLine);
    }

    public void addTicketLine(TicketLine ticketLine) {
        sale.setTl(ticketLine);
    }

    public void addProductLine(ProductLine productLine) {
        sale.setPl(productLine);
    }

    public void addPaymentType(PaymentType paymentType) {
        sale.setPaymentType(paymentType);
    }

    public void addDate(String date) {
        sale.setDate(date);
    }

    public void addEndPrice(double dk, double euro) {
        sale.setEndpriceDk(dk);
        sale.setEndpriceEuro(euro);
    }

    public void addInvoice(Invoice invoice) {
        sale.setInvoiceList(null);
    }

    public Sale searchSale(int saleid) {
        for (Sale sale1 : saleList) {
            if (sale1.getId() == saleid) {
                sale = sale1;
            }
        }
        return sale;
    }

    public ArrayList<Sale> getSaleList() {
        return saleList;
    }

    public void setSaleList(ArrayList<Sale> saleList) {
        this.saleList = saleList;
    }
    
    public void addSaleToDatebase(Sale sale, int paymentid){
        DBConnection db = new DBConnection();
        Calendar cal = Calendar.getInstance();
        String dato = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE) + " " + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
        
        
        try {
            int id = sale.getId();
            
            int employee = sale.getEmployee().getCpr();
            String date = dato;
            db.execute("insert into sale values ("+id+","+paymentid+","+employee+",'"+dato+"')");
            
            db.close();
        } catch (SQLException ex) {
            Logger.getLogger(Museum.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
