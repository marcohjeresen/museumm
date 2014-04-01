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
public class Sale {
    private int id;
    private PaymentType paymentType;
    private Employee employee;
    private Date date;
    private ArrayList<TicketLine> ticketLine;
    private ArrayList<EventLine> eventLine;
    private ArrayList<ProductLine> productLine;

    public Sale(int id, PaymentType paymentType, Employee employee, Date date) {
        this.id = id;
        this.paymentType = paymentType;
        this.employee = employee;
        this.date = date;
        ticketLine = new ArrayList<>();
        eventLine = new ArrayList<>();
        productLine = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<TicketLine> getTl() {
        return ticketLine;
    }

    public void setTl(TicketLine tl) {
        ticketLine.add(tl);
    }

    public ArrayList<EventLine> getEl() {
        return eventLine;
    }

    public void setEl(EventLine el) {
        eventLine.add(el);
    }

    public ArrayList<ProductLine> getPl() {
        return productLine;
    }

    public void setPl(ProductLine pl) {
        productLine.add(pl);
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        String sale = "Sale id: " + id + " paymentType: " + paymentType.getType() + " employee: " + employee.getName() + " date: " + date;
        
            
        
        return sale;
    }
    
}
