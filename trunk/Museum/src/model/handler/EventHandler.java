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
public class EventHandler {
    private EventType et;
    private EventLine el;
    private ArrayList<EventType> eventTypeList;
    private ArrayList<EventLine> eventLineList;
    private Sale sale;
    private SaleHandler sh;
    private CustomerHandler cth;

    public EventHandler(SaleHandler sh, CustomerHandler cth) {
        this.sh = sh;
        this.cth = cth;
        
        eventLineList = new ArrayList<>();
        eventTypeList = new ArrayList<>();
        getDatabase();
    }
    
     public void getDatabase() {
        DBConnection db = new DBConnection();
        try {
            ResultSet rs = db.getResult("SELECT * FROM eventtype");
            while (rs.next()) {
                et = new EventType(rs.getInt("eventtype_id"), rs.getString("eventtype_type"), rs.getDouble("eventtype_pricedk"), 
                        rs.getDouble("eventtype_priceeuro"), rs.getTime("eventtype_time"));
                eventTypeList.add(et);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Museum.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ResultSet rse = db.getResult("SELECT * FROM eventline");
            while (rse.next()) {
                for (EventType eventtype : eventTypeList) {
                    if (eventtype.getId() == rse.getInt("eventline_eventtype_id")) {
                        for (Sale sale : sh.getSaleList()) {
                            if (sale.getId() == rse.getInt("eventline_sale_id")) {
                                for (Customer customer : cth.getCtList()) {
                                    if (customer.getPhone() == rse.getInt("eventline_customer_phone")) {
                                        el = new EventLine(rse.getInt("eventline_id"), sale, rse.getInt("eventline_quantities"), rse.getDate("eventline_date"), customer);
                                        el.setEventtypeList(eventtype);
                                        eventLineList.add(el);
                                    }
                                }
                                
                            }
                        }
                        
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Museum.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<EventLine> getEventLineList() {
        return eventLineList;
    }

    public void setEventLineList(ArrayList<EventLine> eventLineList) {
        this.eventLineList = eventLineList;
    }
    
}
