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
    
    public void setProductList(Product product) {
        
        productList.add(product);
        listeners.notifyListeners("Update kurv");
        
    }
    
    public void cancelLast() {
        if (!productList.isEmpty()) {
            productList.remove(productList.size() - 1);
            listeners.notifyListeners("Update kurv");
        }
        
    }
    
    public void clearKurv() {
        productList.removeAll(productList);
        ticketTypesList.removeAll(ticketTypesList);
        eventTypesList.removeAll(eventTypesList);
        Prisdk = 0;
        Priseuro = 0;
        listeners.notifyListeners("Update kurv");
    }
    
    public ArrayList<Product> getProductList() {
        return productList;
    }
    
    @Override
    public String toString() {
        Prisdk = 0;
        Priseuro = 0;
        String kurv = "Nummer:\tVareTitle:\t\tPrisDk:\tPrisEuro:\n";
        if (!productList.isEmpty()) {
            for (Product product : productList) {
                if (product.getName().length() < 15) {
                    kurv = kurv + product.getProductNumber() + "\t" + product.getName() + "\t\t" + product.getPriceDk() + "\t" + product.getPriceEuro() + "\n";
                    Prisdk = Prisdk + product.getPriceDk();
                    Priseuro = Priseuro + product.getPriceEuro();
                } else {
                    kurv = kurv + product.getProductNumber() + "\t" + product.getName() + "\t" + product.getPriceDk() + "\t" + product.getPriceEuro() + "\n";
                    Prisdk = Prisdk + product.getPriceDk();
                    Priseuro = Priseuro + product.getPriceEuro();
                }
            }            
        }
        if (!ticketTypesList.isEmpty()) {
            for (TicketType ticketType : ticketTypesList) {
                if (ticketType.getType().length() < 15) {
                    kurv = kurv + ticketType.getId() + "\t"+ ticketType.getType() + "\t\t" + ticketType.getPriceDk()+ "\t" + ticketType.getPriceEuro() + "\n";
                    Prisdk = Prisdk + ticketType.getPriceDk();
                    Priseuro = Priseuro + ticketType.getPriceEuro();
                }else {
                    kurv = kurv + ticketType.getId() + "\t"+ ticketType.getType() + "\t" + ticketType.getPriceDk()+ "\t" + ticketType.getPriceEuro() + "\n";
                    Prisdk = Prisdk + ticketType.getPriceDk();
                    Priseuro = Priseuro + ticketType.getPriceEuro();
                }
            }
        }
        if (!eventTypesList.isEmpty()) {
            for (EventType eventType : eventTypesList) {
                if (eventType.getType().length() < 15) {
                    kurv = kurv + eventType.getId() + "\t" + eventType.getType() + "\t\t" + eventType.getPriceDk() + "\t" + eventType.getPriceEuro() + "\n";
                    Prisdk = Prisdk + eventType.getPriceDk();
                    Priseuro = Priseuro + eventType.getPriceEuro();
                }else{
                    kurv = kurv + eventType.getId() + "\t" + eventType.getType() + "\t" + eventType.getPriceDk() + "\t" + eventType.getPriceEuro() + "\n";
                    Prisdk = Prisdk + eventType.getPriceDk();
                    Priseuro = Priseuro + eventType.getPriceEuro();
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
        for (Employee employ : employeeHandler.getEmployeeList()) {
            if (employ.getPassword() == kode) {
                this.employee = employ;
                listeners.notifyListeners("Employee log");
            }
        }
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
    
    public void setTicketTypesList(TicketType ticketTypet) {
        ticketTypesList.add(ticketTypet);
        listeners.notifyListeners("Update kurv");
    }
    
    public ArrayList<EventType> getEventTypesList() {
        return eventTypesList;
    }
    
    public void setEventTypesList(EventType eventType) {
        eventTypesList.add(eventType);
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
