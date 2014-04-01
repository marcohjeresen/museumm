/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

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
    private TicketLine tl;
    private EventLine el;
    private ProductLine pl;

    public Sale(int id, PaymentType paymentType, Employee employee, Date date) {
        this.id = id;
        this.paymentType = paymentType;
        this.employee = employee;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TicketLine getTl() {
        return tl;
    }

    public void setTl(TicketLine tl) {
        this.tl = tl;
    }

    public EventLine getEl() {
        return el;
    }

    public void setEl(EventLine el) {
        this.el = el;
    }

    public ProductLine getPl() {
        return pl;
    }

    public void setPl(ProductLine pl) {
        this.pl = pl;
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
        return "Sale" + "id: " + id + " paymentType: " + paymentType.getType() + " employee: " + employee.getName() + " date: " + date;
    }
    
}
