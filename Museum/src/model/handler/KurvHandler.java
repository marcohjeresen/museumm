/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.handler;

import java.util.ArrayList;
import model.*;
import java.math.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author markh_000
 */
public class KurvHandler {
    
    private ProductHandler productHandler;
    private CustomerHandler customerHandler;
    private PaymentTypeHandler paymentTypeHandler;
    private TicketHandler ticketHandler;
    private EmployeeHandler employeeHandler;
    private EventHandler eventHandler;
    private SaleHandler saleHandler;
    private InvoiceHandler invoiceHandler;
    private ProductLine productLine;
    private ArrayList<Product> productList;
    private ArrayList<Product> specProductList;
    private ArrayList<TicketType> ticketTypesList;
    private ArrayList<EventType> eventTypesList;
    private Listeners listeners;
    private Employee employee;
    private CashRegister cashRegister;
    private double Prisdk;
    private double Priseuro;
    private String typeslags;
    
    public KurvHandler(ProductHandler productHandler, CustomerHandler customerHandler, PaymentTypeHandler paymentTypeHandler, TicketHandler ticketHandler, EmployeeHandler employeeHandler, EventHandler eventHandler, SaleHandler saleHandler, InvoiceHandler invoiceHandler, Listeners listeners1) {
        this.productHandler = productHandler;
        this.customerHandler = customerHandler;
        this.paymentTypeHandler = paymentTypeHandler;
        this.ticketHandler = ticketHandler;
        this.employeeHandler = employeeHandler;
        this.eventHandler = eventHandler;
        this.saleHandler = saleHandler;
        this.invoiceHandler = invoiceHandler;
        this.listeners = listeners1;
        productList = new ArrayList<>();
        specProductList = new ArrayList<>();
        ticketTypesList = new ArrayList<>();
        eventTypesList = new ArrayList<>();
        
    }
    
    public void setProductLine(Product product, int quantities) {
        productHandler.opretProductLine(product, quantities);
        listeners.notifyListeners("Update kurv");
    }
    
    public void cancelLast() {
        if (!productList.isEmpty()) {
            productList.remove(productList.size() - 1);
            listeners.notifyListeners("Update kurv");
        }
        
    }
    
    public void clearKurv() {
        saleHandler.clearSaleKurv();
        listeners.notifyListeners("Update kurv");
    }
    
    public ArrayList<Product> getProductList() {
        return productList;
    }
    
    @Override
    public String toString() {
        Prisdk = 0;
        Priseuro = 0;
        String kurv = "Id:          Antal:\tVareTitle:\t\tPrisDk:\tPrisEuro:\n";
        if (!saleHandler.getSale().getPl().isEmpty()) {
            for (ProductLine productLine : saleHandler.getSale().getPl()) {
                if (productLine.getProduct().getName().length() < 15) {
                    kurv = kurv + productLine.getProduct().getProductNumber() + "    "+productLine.getQuantities() +"\t" + productLine.getProduct().getName() + "\t\t" + productLine.getProduct().getPriceDk() + "\t" + productLine.getProduct().getPriceEuro() + "\n";
                    Prisdk = Prisdk + (productLine.getProduct().getPriceDk() * productLine.getQuantities());
                    Priseuro = Priseuro + (productLine.getProduct().getPriceEuro() * productLine.getQuantities());
                } else {
                    kurv = kurv + productLine.getProduct().getProductNumber() + "    "+productLine.getQuantities() +"\t" + productLine.getProduct().getName() + "\t" + productLine.getProduct().getPriceDk() + "\t" + productLine.getProduct().getPriceEuro() + "\n";
                    Prisdk = Prisdk + (productLine.getProduct().getPriceDk() * productLine.getQuantities());
                    Priseuro = Priseuro + (productLine.getProduct().getPriceEuro() * productLine.getQuantities());
                }
            }            
        }
        if (!saleHandler.getSale().getTl().isEmpty()) {
            for (TicketLine ticketLine : saleHandler.getSale().getTl()) {
                if (ticketLine.getTicketType().getType().length() < 15) {
                    kurv = kurv + ticketLine.getTicketType().getId() + "    "+ticketLine.getQuantities()+"\t"+ ticketLine.getTicketType().getType() + "\t\t" + ticketLine.getTicketType().getPriceDk()+ "\t" + ticketLine.getTicketType().getPriceEuro() + "\n";
                    Prisdk = Prisdk + (ticketLine.getTicketType().getPriceDk() * ticketLine.getQuantities());
                    Priseuro = Priseuro + (ticketLine.getTicketType().getPriceEuro()* ticketLine.getQuantities());
                }else {
                    kurv = kurv + ticketLine.getTicketType().getId() + "    "+ticketLine.getQuantities()+"\t"+ ticketLine.getTicketType().getType() + "\t" + ticketLine.getTicketType().getPriceDk()+ "\t" + ticketLine.getTicketType().getPriceEuro() + "\n";
                    Prisdk = Prisdk + (ticketLine.getTicketType().getPriceDk() * ticketLine.getQuantities());
                    Priseuro = Priseuro + (ticketLine.getTicketType().getPriceEuro()* ticketLine.getQuantities());
                }
            }
        }
        if (!saleHandler.getSale().getEl().isEmpty()) {
            for (EventLine eventLine : saleHandler.getSale().getEl()) {
                if (eventLine.getEventtype().getType().length() < 15) {
                    kurv = kurv + eventLine.getEventtype().getId() +"    "+eventLine.getQuantities()+ "\t" + eventLine.getEventtype().getType() + "\t\t" + eventLine.getEventtype().getPriceDk() + "\t" + eventLine.getEventtype().getPriceEuro() + "\n";
                    Prisdk = Prisdk + eventLine.getEventtype().getPriceDk();
                    Priseuro = Priseuro + eventLine.getEventtype().getPriceEuro();
                }else{
                    kurv = kurv + eventLine.getEventtype().getId() +"    "+eventLine.getQuantities()+ "\t" + eventLine.getEventtype().getType() + "\t" + eventLine.getEventtype().getPriceDk() + "\t" + eventLine.getEventtype().getPriceEuro() + "\n";
                    Prisdk = Prisdk + eventLine.getEventtype().getPriceDk();
                    Priseuro = Priseuro + eventLine.getEventtype().getPriceEuro();
                }
            }
        }
        Priseuro = Priseuro * 100;
        Priseuro = Math.round(Priseuro);
        Priseuro = (Priseuro / 100);
        
        Prisdk = Prisdk * 100;
        Prisdk = Math.round(Prisdk);
        Prisdk = (Prisdk / 100);
        return kurv;
    }
    
    public String priceToString() {
        return "\nTotal: \tPrisdDk: " + Prisdk + "           PrisEuro: " + Priseuro;
    }
    
    public void setBruger(int kode) {
        employeeHandler.setLoginEmployee(kode);
        
        saleHandler.opretSale(employeeHandler.getLogIndEmployee());
        listeners.notifyListeners("Update kurv");
    }

    public void setCashRegister(double beløbDk, double beløbEuro) {
        Calendar cal = Calendar.getInstance();
        String str = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE) + " " + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
        
        cashRegister = new CashRegister(str, beløbDk, beløbEuro, employee);
        listeners.notifyListeners("CashRegister");
        
    }
    
    public CashRegister getCashRegister() {
        return cashRegister;
    }
    
    public Employee getEmployee() {
        
        return employee;
    }
    
    
    public void logUd() {
        listeners.notifyListeners("Employee log");
        employee = null;
    }
    
    public ArrayList<TicketType> getTicketTypesList() {
        return ticketTypesList;
    }
    
    public void setTicketTypesLine(TicketType ticketType, int quantities, String date) {
        ticketHandler.opretTicketLine(ticketType, quantities, date);
        listeners.notifyListeners("Update kurv");
    }
    
    public ArrayList<EventType> getEventTypesList() {
        return eventTypesList;
    }
    
    public void setEventTypesLine(EventType eventType, int quant, int customer, String date) {
        eventHandler.opretEventLine(eventType, quant, customer, date);
        listeners.notifyListeners("Update kurv");
    }
    
    public String getTypeView(){
         
        return typeslags;
    }
    
    public void setTypeView(String type){
        typeslags = "Product";
        switch (type) {
            case "Product":
                ticketHandler.clearSpecTicket();
                typeslags = "Product";
                break;
            case "Ticket":
                productHandler.clearSpecProductList();
                typeslags = "Ticket";
                break;
            case "Event":
                productHandler.clearSpecProductList();
                ticketHandler.clearSpecTicket();
                typeslags = "Event";
                break;
               
        }
        listeners.notifyListeners("Update view");
    }
    
}
