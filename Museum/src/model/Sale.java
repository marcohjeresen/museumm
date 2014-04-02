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
    private ArrayList<Invoice> invoiceList;
    private double endpriceDk;
    private double endpriceEuro;

    public Sale(int id, PaymentType paymentType, Employee employee, Date date) {
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
        String sale = "Sale id: " + id + "\n" + "paymentType: " + paymentType.getType() + " employee: " + employee.getName() + " date: " + date + "\nProduct: \n";
        if (productLine != null) {
            for (ProductLine productline : productLine) {
                for (Product product : productline.getProductList()) {
                    sale = sale + product.toString() + "\n";
                    endpriceDk = endpriceDk + product.getPriceDk();
                    endpriceEuro = endpriceEuro + product.getPriceEuro();
                }
            }
        }
        if (eventLine != null) {
            sale = sale + "Event: \n";
            for (EventLine eventline : eventLine) {
                for (EventType eventtype : eventline.getEventtypeList()) {
                    sale = sale + eventtype.toString() + " Customer: " + eventline.getCustomer().getName() + "\n";
                    endpriceDk = endpriceDk + eventtype.getPriceDk();
                    endpriceEuro = endpriceEuro + eventtype.getPriceEuro();
                }
            }
        }
        if (ticketLine != null) {
            sale = sale + "Tickets: \n";
            for (TicketLine ticketline : ticketLine) {
                for (TicketType ticketType : ticketline.getTicketList()) {
                    sale = sale + ticketType.toString() + "\n";
                    endpriceDk = endpriceDk + ticketType.getPriceDk();
                    endpriceEuro = endpriceEuro + ticketType.getPriceEuro();
                }
            }
        }
        if (invoiceList != null) {
            for (Invoice invoice : invoiceList) {
                sale = sale + "Invoice: \n" + invoice.toString() + "\n";
            }
            
        }
        return sale + "PriceDk: " + endpriceDk + " PriceEuro: " + endpriceEuro + "\n";
    }

    public double getEndpriceDk() {
        return endpriceDk;
    }

    public void setEndpriceDk(double endpriceDk) {
        this.endpriceDk = endpriceDk;
    }

    public double getEndpriceEuro() {
        return endpriceEuro;
    }

    public void setEndpriceEuro(double endpriceEuro) {
        this.endpriceEuro = endpriceEuro;
    }

}
