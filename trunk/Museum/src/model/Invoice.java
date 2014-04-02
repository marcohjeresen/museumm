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
public class Invoice {
    private int id;
    private Sale sale;
    private Date date;
    private double priceDk;
    private double priceEuro;
    private InvoiceStatus invoiceStatus;

    public Invoice(int id, Sale sale, Date date, double priceDk, double priceEuro, InvoiceStatus invoiceStatus) {
        this.id = id;
        this.sale = sale;
        this.date = date;
        this.priceDk = priceDk;
        this.priceEuro = priceEuro;
        this.invoiceStatus = invoiceStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPriceDk() {
        return priceDk;
    }

    public void setPriceDk(double priceDk) {
        this.priceDk = priceDk;
    }

    public double getPriceEuro() {
        return priceEuro;
    }

    public void setPriceEuro(double priceEuro) {
        this.priceEuro = priceEuro;
    }

    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    @Override
    public String toString() {
        return "id: " + id + " date: " + date + " priceDk: " + priceDk + " priceEuro: " + priceEuro + " invoiceStatus: " + invoiceStatus.getType();
    }
    
}
