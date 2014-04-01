/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.handler;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
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
public class InvoiceHandler {
    private Invoice in;
    private InvoiceStatus ins;
    private Sale sale;
    private SaleHandler saleH;
    
    private ArrayList<Invoice> inList;
    private ArrayList<InvoiceStatus> insList;

    public InvoiceHandler(SaleHandler saleH) {
        this.saleH = saleH;
        inList = new ArrayList<>();
        insList = new ArrayList<>();
        getDatabase();
    }
    
    public void getDatabase() {
        DBConnection db = new DBConnection();
        try {
            ResultSet rs = db.getResult("SELECT * FROM invoicestatus");
            while (rs.next()) {
                ins = new InvoiceStatus(rs.getInt("invoicestatus_id"), rs.getString("invoicestatus_type"));
                insList.add(ins);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Museum.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ResultSet rse = db.getResult("SELECT * FROM invoice");
            while (rse.next()) {
                for (Sale sale : saleH.getSaleList()) {
                    if (sale.getId() == rse.getInt("invoice_sale_id")) {
                        for (InvoiceStatus invoiceStatus : insList) {
                            if (invoiceStatus.getId() == rse.getInt("invoice_invoicestatus_id")) {
                                in = new Invoice(rse.getInt("invoice_id"), sale, rse.getDate("invoice_date"), rse.getDouble("invoice_pricedk"), 
                                        rse.getDouble("invoice_priceeuro"), ins);
                                inList.add(in);
                            }
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Museum.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Invoice> getInList() {
        return inList;
    }

    public void setInList(ArrayList<Invoice> inList) {
        this.inList = inList;
    }

    public ArrayList<InvoiceStatus> getInsList() {
        return insList;
    }

    public void setInsList(ArrayList<InvoiceStatus> insList) {
        this.insList = insList;
    }
    
}
