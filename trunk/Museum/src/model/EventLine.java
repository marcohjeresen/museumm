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
    private ArrayList<EventType> eventtypeList;
    private Sale sale;
    private int quantities;
    private Date date;
    private Customer customer;

    public EventLine(int id, Sale sale, int quantities, Date date, Customer customer) {
        this.id = id;
        this.sale = sale;
        this.quantities = quantities;
        this.date = date;
        this.customer = customer;
        eventtypeList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<EventType> getEventtypeList() {
        return eventtypeList;
    }

    public void setEventtypeList(EventType eventType) {
        eventtypeList.add(eventType);
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        String eventLine = "";
        
        for (EventType eventType : eventtypeList) {
            eventLine = eventLine + eventType.toString()+"\n";
        }
        eventLine = eventLine + "Date: " + date + " Customer: " + customer.getName();
        return eventLine;
       }
    
    public double getEventlinePriceDk(){
        double dk = 0;
        for (EventType eventType : eventtypeList) {
            dk = dk + eventType.getPriceDk();
        }
        return dk;
    }
    
    public double getEventlineEuro(){
        double euro = 0;
        for (EventType eventType : eventtypeList) {
            euro = euro + eventType.getPriceEuro();
        }
        return euro;
    }


    
    
}
