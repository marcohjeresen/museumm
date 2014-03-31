/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;


import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author markh_000
 */
public class TicketLine {
    private int id;
    private ArrayList<TicketType> ticketList;
    private Sale sale;
    private int quantities;
    private Date date;

    public TicketLine(int id, Sale sale, int quantities, Date date) {
        this.id = id;
        this.sale = sale;
        this.quantities = quantities;
        this.date = date;
        ticketList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<TicketType> getTicketList() {
        return ticketList;
    }

    public void setTicketList(TicketType ticket) {
        ticketList.add(ticket);
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public int getQuantities() {
        return quantities;
    }

    public void setQuantities(int quantities) {
        this.quantities = quantities;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        String ticketLine = "";
        
        for (TicketType ticketType : ticketList) {
            ticketLine = ticketLine + ticketType.toString();
        }
        
        return ticketLine + "Quantities: " + quantities + " Date: " + date;
    }
    
    
    
}
