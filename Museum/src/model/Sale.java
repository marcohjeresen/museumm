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
    private String date;
    private ArrayList<TicketLine> ticketLine;
    private ArrayList<EventLine> eventLine;
    private ArrayList<ProductLine> productLine;
    private ArrayList<Invoice> invoiceList;
    private double endpriceDk;
    private double endpriceEuro;

    public Sale(int id, PaymentType paymentType, Employee employee, String date) {
        this.id = id;
        this.paymentType = paymentType;
        this.employee = employee;
        this.date = date;
        ticketLine = new ArrayList<>();
        eventLine = new ArrayList<>();
        productLine = new ArrayList<>();
        invoiceList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<TicketLine> getTicketLine() {
        return ticketLine;
    }

    public void setTicketLine(TicketLine tl) {
        ticketLine.add(tl);
    }
    public void clearTicketLine(){
        ticketLine.removeAll(ticketLine);
    }

    public ArrayList<EventLine> getEventLine() {
        return eventLine;
    }
    public void clearEventLine(){
        eventLine.removeAll(eventLine);
    }

    public void setEventLine(EventLine el) {
        eventLine.add(el);
    }

    public ArrayList<ProductLine> getProductLine() {
        return productLine;
    }

    public void setProductLine(ProductLine pl) {
        productLine.add(pl);
    }
    public void clearProductLine(){
        productLine.removeAll(productLine);
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
    public void clearEmployee(){
        employee = null;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(Invoice invoice) {
        invoiceList.add(invoice);
    }

    @Override
    public String toString() {
        endpriceDk = 0;
        endpriceEuro = 0;
        String sale = "\nSale id: " + id + "\n" + "paymentType: " + paymentType.getType() + " employee: " + employee.getName() + " date: " + date + "\n\nProduct: \n";
        if (!productLine.isEmpty()) {
            for (ProductLine productline : productLine) {
                    sale = sale + productline.getProduct().toString() + "\n";
                    endpriceDk = endpriceDk + productline.getProduct().getPriceDk();
                    endpriceEuro = endpriceEuro + productline.getProduct().getPriceEuro();
                
            }
        }
        
//        if (!eventLine.isEmpty()) {
//            sale = sale + "\nEvent: \n";
//            for (EventLine eventline : eventLine) {
//                for (EventLine eventLine : eventLine) {
//                    sale = sale + eventLine.getEventtype().toString() + " Customer: " + eventline.getCustomer() + "\n";
//                    endpriceDk = endpriceDk + eventLine.getEventtype().getPriceDk();
//                    endpriceEuro = endpriceEuro + eventLine.getEventtype().getPriceEuro();
//                }
//            }
//        }
//        if (!ticketLine.isEmpty()) {
//            sale = sale + "\nTickets: \n";
//            for (TicketLine ticketline : ticketLine) {
//                for (TicketLine ticketLine : ticketLine) {
//                    sale = sale + ticketLine.toString() + "\n";
//                    endpriceDk = endpriceDk + ticketLine.getTicketType().getPriceDk();
//                    endpriceEuro = endpriceEuro + ticketLine.getTicketType().getPriceEuro();
//                }
//            }
//        }
        if (!invoiceList.isEmpty()) {
            for (Invoice invoice : invoiceList) {
                sale = sale + "\nInvoice: \n" + invoice.toString() + "\n";
            }

        }
        endpriceEuro = endpriceEuro * 100;
        endpriceEuro = Math.round(endpriceEuro);
        endpriceEuro = (endpriceEuro / 100);
        return sale + "PriceDk: " + endpriceDk + " PriceEuro: " + endpriceEuro + "\n";
    }

    public double getEndpriceDk() {
        return endpriceDk;
    }

    public void setEndpriceDk(int endpriceDk) {
        this.endpriceDk = endpriceDk;
    }

    public double getEndpriceEuro() {
        return endpriceEuro;
    }

    public void setEndpriceEuro(int endpriceEuro) {
        this.endpriceEuro = endpriceEuro;
    }
    public void clearSale(){
        
    }

}
