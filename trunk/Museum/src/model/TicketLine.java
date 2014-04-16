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
    private TicketType ticketType;
    private Sale sale;
    private int quantities;
    private String date;

    public TicketLine(int id, Sale sale, int quantities, String date, TicketType ticketType) {
        this.id = id;
        this.sale = sale;
        this.quantities = quantities;
        this.date = date;
        this.ticketType = ticketType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        String ticketLine = "";

            ticketLine = ticketLine + ticketType.toString();
        
        
        return ticketLine + "Quantities: " + quantities + " Date: " + date;
    }
    
    
    
}
