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
    private ArrayList<EventType> specList;
    private Sale sale;
    private SaleHandler sh;
    private CustomerHandler cth;
    private EventType eventType;

    public EventHandler(SaleHandler sh, CustomerHandler cth) {
        this.sh = sh;
        this.cth = cth;
        
        eventLineList = new ArrayList<>();
        eventTypeList = new ArrayList<>();
        specList = new ArrayList<>();
        getDatabase();
        addLineToSale();
    }
    
     public void getDatabase() {
        DBConnection db = new DBConnection();
        try {
            ResultSet rs = db.getResult("SELECT * FROM eventtype");
            while (rs.next()) {
                et = new EventType(rs.getInt("eventtype_id"), rs.getString("eventtype_type"), rs.getInt("eventtype_pricedk"), 
                        rs.getInt("eventtype_priceeuro"), rs.getTime("eventtype_time"));
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
                                        el = new EventLine(rse.getInt("eventline_id"),eventtype, sale, rse.getInt("eventline_quantities"), rse.getString("eventline_date"), rse.getInt("eventline_customer_phone"));
                                        
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
     
     public void opretEventLine(EventType eventType1, int quantities, int customer, String date) {
        int idnumer = 0;
        boolean erder = false;
        for (int i = 0; i < sh.getSale().getEl().size(); i++) {
            if (sh.getSale().getEl().get(i).getEventtype().equals(eventType1)) {
                int quantitiess = sh.getSale().getEl().get(i).getQuantities();
                sh.getSale().getEl().get(i).setQuantities(quantities + quantitiess);
                erder = true;
            }
        }
        if (!erder) {
            for (EventLine eventLine : eventLineList) {
                if (eventLine.getId() > idnumer) {
                    idnumer = eventLine.getId();
                }
            }
            
            idnumer = idnumer + 1;
            el = new EventLine(idnumer, eventType1, sh.getSale(), quantities, date, customer);
            sh.addEventLine(el);
            eventLineList.add(el);
        }else if (erder) {
             for (int i = 0; i < eventLineList.size(); i++) {
                 if (eventLineList.get(i).getId() == idnumer) {
                     int antal = eventLineList.get(i).getQuantities();
                     eventLineList.get(i).setQuantities(quantities+ antal);
                 }
                
            }
         }

    }

     
      public void addLineToSale() {
        for (Sale sale : sh.getSaleList()) {
            for (EventLine eventLine : eventLineList) {
                if (sale == eventLine.getSale()) {
                    sale.setEl(eventLine);
                }
            }
        }
    }

    public ArrayList<EventLine> getEventLineList() {
        return eventLineList;
    }

    public void setEventLineList(ArrayList<EventLine> eventLineList) {
        this.eventLineList = eventLineList;
    }

    public ArrayList<EventType> getEventTypeList() {
        return eventTypeList;
    }
    
    public void setSpecEventType(EventType eventType1){
        eventType = eventType1;
    }
    
    public EventType getEventType(){
        return eventType;
    }
    
//    public void setSpecielList(){
//        specList.removeAll(specList);
//
//        for (EventType eventType : eventTypeList) {
//            if (eventType.getId() == group) {
//                specList.add(product);
//            }
//        }
//        listners.notifyListeners();
//    }
    
}
