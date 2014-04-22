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
    private int endpriceDk;
    private int endpriceEuro;

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

    public void clearTicketLine() {
        ticketLine.removeAll(ticketLine);
    }

    public ArrayList<EventLine> getEventLine() {
        return eventLine;
    }

    public void clearEventLine() {
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

    public void clearProductLine() {
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

    public void clearEmployee() {
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
    public void clearInvoiceList(){
        invoiceList.removeAll(invoiceList);
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

    public int getEndpriceDk(boolean discount) {
        setEndprice(discount);
        return endpriceDk;
    }

    public void setEndprice(boolean discount) {
        double price = 0;
        endpriceDk = 0;
        endpriceEuro = 0;
        if (!productLine.isEmpty()) {
            for (ProductLine productline : productLine) {
                if (discount) {
                    price = (productline.getProduct().getPriceDk() / 100) * productline.getProduct().getDiscount();
                    price = price * productline.getQuantities();
                    endpriceDk = (int) (endpriceDk + price);
                    
                    price = (productline.getProduct().getPriceEuro() / 100) * productline.getProduct().getDiscount();
                    price = price * productline.getQuantities();
                    endpriceEuro = (int) (endpriceEuro + price);
                }else{
                    price = productline.getProduct().getPriceDk() * productline.getQuantities();
                endpriceDk = (int) (endpriceDk + price);
                price = productline.getProduct().getPriceEuro() * productline.getQuantities();
                endpriceEuro = (int) (endpriceEuro + price);

                }
            }
        }
        if (!eventLine.isEmpty() ) {
            for (EventLine eventLine1 : eventLine) {
                
                price = eventLine1.getEventlinePriceDk();
                endpriceDk = (int) (endpriceDk + price);
                price = eventLine1.getEventlineEuro();
                endpriceEuro = (int) (endpriceEuro + price);
            }
        }
        if (!ticketLine.isEmpty()) {
            for (TicketLine ticketLine1 : ticketLine) {
                price = ticketLine1.getTicketType().getPriceDk() * ticketLine1.getQuantities();
                endpriceDk = (int) (endpriceDk + price);
                price = ticketLine1.getTicketType().getPriceEuro() * ticketLine1.getQuantities();
                endpriceEuro = (int) (endpriceEuro + price);
            }
        }

    }

    public int getEndpriceEuro(boolean discount) {
        setEndprice(discount);
        return endpriceEuro;
    }

    public void clearSale() {

    }

}
