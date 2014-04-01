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
public class TicketHandler {
    
    private TicketType tp;
    private TicketLine tl;
    private Sale sale;
    private SaleHandler saleh;
    
    private ArrayList<TicketType> tpList;
    private ArrayList<TicketLine> tlList;
    
    public TicketHandler(SaleHandler saleh) {
        this.saleh = saleh;
        tpList = new ArrayList<>();
        tlList = new ArrayList<>();
        getDatabase();
        addLineToSale();
    }
    
    public void getDatabase() {
        
        DBConnection db = new DBConnection();
        try {
            ResultSet rs = db.getResult("SELECT * FROM tickettype");
            while (rs.next()) {
                tp = new TicketType(rs.getInt("tickettype_id"), rs.getString("tickettype_type"), rs.getDouble("tickettype_pricedk"), rs.getDouble("tickettype_priceeuro"));
                tpList.add(tp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Museum.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ResultSet rse = db.getResult("SELECT * FROM ticketline");
            while (rse.next()) {
                for (Sale sale : saleh.getSaleList()) {
                    if (sale.getId() == rse.getInt("ticketline_sale_id")) {
                        
                        for (TicketType ticketType : tpList) {
                            if (ticketType.getId() == rse.getInt("ticketline_tickettype_id")) {
                                
                                tl = new TicketLine(rse.getInt("ticketline_id"), sale, rse.getInt("ticketline_quantities"), rse.getDate("ticketline_date"));
                                tl.setTicketList(ticketType);
                                tlList.add(tl);
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
            for (TicketLine ticketLine : tlList) {
                if (sale == ticketLine.getSale()) {
                    sale.setTl(ticketLine);
                }
            }
        }
    }
    
    public ArrayList<TicketType> getTpList() {
        return tpList;
    }
    
    public void setTpList(ArrayList<TicketType> tpList) {
        this.tpList = tpList;
    }
    
    public ArrayList<TicketLine> getTlList() {
        return tlList;
    }
    
    public void setTlList(ArrayList<TicketLine> tlList) {
        this.tlList = tlList;
    }
    
}
