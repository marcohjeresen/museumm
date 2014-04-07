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
    private ArrayList<TicketType> ticketTypesList;
    private ArrayList<EventType> eventTypesList;
    private Listeners listeners;
    private Employee employee;
    private CashRegister cashRegister;
    private double Prisdk;
    private double Priseuro;
    
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
        ticketTypesList = new ArrayList<>();
        eventTypesList = new ArrayList<>();
        
    }
    
    public void setProductList(Product product) {
        
        productList.add(product);
        listeners.notifyListeners();
        
    }

    public void cancelLast() {
        if (!productList.isEmpty()) {
            productList.remove(productList.size() - 1);
            listeners.notifyListeners();
        }
        
    }
    
    public void clearKurv() {
        productList.removeAll(productList);
        ticketTypesList.removeAll(ticketTypesList);
        eventTypesList.removeAll(eventTypesList);
        Prisdk = 0;
        Priseuro = 0;
        listeners.notifyListeners();
    }
    
    public ArrayList<Product> getProductList() {
        return productList;
    }
    
    @Override
    public String toString() {
        Prisdk = 0;
        Priseuro = 0;
        String kurv = "Nummer:\tVareTitle:\t\tPrisDk:\tPrisEuro:\n";
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
        Priseuro = Priseuro * 100;
        Priseuro = Math.round(Priseuro);
        Priseuro = (Priseuro / 100);
        return kurv;
    }
    
    public String priceToString() {
        return "\nTotal: \tPrisdDk: " + Prisdk + "           PrisEuro: " + Priseuro;
    }
    
    public void setBruger(int kode) {
        for (Employee employ : employeeHandler.getEmployeeList()) {
            if (employ.getPassword() == kode) {
                this.employee = employ;
                listeners.notifyListeners();
            }
        }
    }
    public void setCashRegister(double beløbDk, double beløbEuro){
        Calendar cal = Calendar.getInstance();
        String str = cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE)+" "+cal.get(Calendar.HOUR)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND);
        
        cashRegister = new CashRegister(str, beløbDk, beløbEuro, employee);
        
    }

    public CashRegister getCashRegister() {
        return cashRegister;
    }
    
    
    public Employee getEmployee() {
        listeners.notifyListeners();
        return employee;
    }
    
    public void logUd() {
        listeners.notifyListeners();
        employee = null;
    }
    
}
