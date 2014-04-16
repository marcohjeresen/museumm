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
    private String date;
    private int priceDk;
    private int priceEuro;
    private InvoiceStatus invoiceStatus;

    public Invoice(int id, Sale sale, String date, int priceDk, int priceEuro, InvoiceStatus invoiceStatus) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPriceDk() {
        return priceDk;
    }

    public void setPriceDk(int priceDk) {
        this.priceDk = priceDk;
    }

    public int getPriceEuro() {
        return priceEuro;
    }

    public void setPriceEuro(int priceEuro) {
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
