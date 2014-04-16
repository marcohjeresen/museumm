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
public class EventLine {

    private int id;
    private EventType eventtype;
    private Sale sale;
    private int quantities;
    private String date;
    private int customer;
    private String place;

    public EventLine(int id, EventType eventtype, Sale sale, int quantities, String date, int customer, String place) {
        this.id = id;
        this.eventtype = eventtype;
        this.sale = sale;
        this.quantities = quantities;
        this.date = date;
        this.customer = customer;
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EventType getEventtype() {
        return eventtype;
    }

    public void setEventtype(EventType eventtype) {
        this.eventtype = eventtype;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSaleId(Sale sale) {
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

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        String eventLine = "";
        eventLine = eventLine + eventtype.toString() + "\n";
        eventLine = eventLine + "Date: " + date + " Customer: " + customer;
        return eventLine;
    }

    public int getEventlinePriceDk() {
        int dk = 0;
        
            dk = eventtype.getPriceDk() * quantities;
        
        return dk;
    }

    public int getEventlineEuro() {
        int euro = 0;
        euro = eventtype.getPriceEuro() * quantities;
        return euro;
    }

}
