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
    private TicketType specticket;
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

                                tl = new TicketLine(rse.getInt("ticketline_id"), sale, rse.getInt("ticketline_quantities"), rse.getString("ticketline_date"), ticketType);

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

    public void opretTicketLine(TicketType ticketType, int quantities, String date) {
        int idnumer = 0;
        boolean erder = false;
        for (int i = 0; i < saleh.getSale().getTl().size(); i++) {
            if (saleh.getSale().getTl().get(i).getTicketType().equals(ticketType)) {
                int quantitiess = saleh.getSale().getTl().get(i).getQuantities();
                saleh.getSale().getTl().get(i).setQuantities(quantities + quantitiess);
                erder = true;
            }
        }
        if (!erder) {
            for (TicketLine ticketLine : tlList) {
                if (ticketLine.getId() > idnumer) {
                    idnumer = ticketLine.getId();
                }
            }
            idnumer = idnumer + 1;
            tl = new TicketLine(idnumer, saleh.getSale(), quantities, date, ticketType);
            saleh.addTicketLine(tl);
            tlList.add(tl);

        } else if (erder) {
            for (int i = 0; i < tlList.size(); i++) {
                if (tlList.get(i).getId() == idnumer) {
                    int antal = tlList.get(i).getQuantities();
                    tlList.get(i).setQuantities(quantities + antal);
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

    public void setSpecTicket(TicketType ticketType) {
        specticket = null;

        specticket = ticketType;

    }

    public void clearSpecTicket() {
        specticket = null;
    }

    public TicketType getSpecticket() {
        return specticket;
    }

}
