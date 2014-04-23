/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import db.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import museum2.Museum2;

/**
 *
 * @author markh_000
 */
public class SaleHandler {
    
    private PaymentType paymentType;
    private ArrayList<PaymentType> paymentTypesList;
    
    private InvoiceStatus invoiceStatus;
    private Invoice invoice;
    
    private Sale sale;
    private ArrayList<Sale> salesList;
    private ReturnProduct returnProduct;
    private ArrayList<ReturnProduct> returnProductsList;
    
    private ArrayList<InvoiceStatus> invoiceStatusesList;
    
    private ArrayList<Invoice> invoicesList;
    private Customer customer;
    private ArrayList<Customer> customersList;
    
    private ArrayList<ProductLine> productLinesList;
    private ArrayList<EventLine> eventLinesList;
    private ArrayList<TicketLine> ticketLinesList;
    
    private StoreHandler storeHandler;
    private MoneyHandler moneyHandler;
    private Listeners listeners;
    
    public SaleHandler(StoreHandler storeHandler1, MoneyHandler moneyHandler1, Listeners listeners1) throws SQLException {
        this.storeHandler = storeHandler1;
        this.moneyHandler = moneyHandler1;
        this.listeners = listeners1;
        paymentTypesList = new ArrayList<>();
        salesList = new ArrayList<>();
        returnProductsList = new ArrayList<>();
        invoiceStatusesList = new ArrayList<>();
        invoicesList = new ArrayList<>();
        customersList = new ArrayList<>();
        productLinesList = new ArrayList<>();
        eventLinesList = new ArrayList<>();
        ticketLinesList = new ArrayList<>();
        getPaymentData();
        getSaleData();
        getInvoiceData();
        getProductLineData();
        getEventLineData();
        getTicketLineData();
        
    }
    
    public void getPaymentData() throws SQLException {
        DBConnection db = new DBConnection();
        try {
            ResultSet rs = db.getResult("SELECT * FROM paymenttype");
            while (rs.next()) {
                PaymentType paymentType = new PaymentType(rs.getInt("paymenttype_id"), rs.getString("paymenttype_type"), rs.getInt("paymenttype_fee"));
                paymentTypesList.add(paymentType);
            }
            db.close();
        } catch (SQLException ex) {
            Logger.getLogger(Museum2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getSaleData() throws SQLException {
        DBConnection db = new DBConnection();
        try {
            ResultSet rs = db.getResult("SELECT * FROM sale");
            while (rs.next()) {
                
                for (PaymentType payment : paymentTypesList) {
                    if (payment.getId() == rs.getInt("sale_paymenttype_id")) {
                        for (Employee employee : storeHandler.getEmployeesList()) {
                            if (employee.getCpr() == rs.getInt("sale_employee_cpr")) {
                                Sale sale = new Sale(rs.getInt("sale_id"), payment, employee, rs.getString("sale_date"));
                                salesList.add(sale);
                            }
                        }
                    }
                }
            }
            db.close();
        } catch (SQLException ex) {
            Logger.getLogger(Museum2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getInvoiceData() throws SQLException {
        DBConnection db = new DBConnection();
        try {
            ResultSet rs = db.getResult("SELECT * FROM invoicestatus");
            while (rs.next()) {
                InvoiceStatus invoiceStatus = new InvoiceStatus(rs.getInt("invoicestatus_id"), rs.getString("invoicestatus_type"));
                invoiceStatusesList.add(invoiceStatus);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Museum2.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ResultSet rse = db.getResult("SELECT * FROM invoice");
            while (rse.next()) {
                for (Sale sale : salesList) {
                    if (sale.getId() == rse.getInt("invoice_sale_id")) {
                        for (InvoiceStatus invoiceStatus : invoiceStatusesList) {
                            if (invoiceStatus.getId() == rse.getInt("invoice_invoicestatus_id")) {
                                Invoice invoice = new Invoice(rse.getInt("invoice_id"), sale, rse.getString("invoice_date"), rse.getInt("invoice_pricedk"),
                                        rse.getInt("invoice_priceeuro"), invoiceStatus);
                                invoicesList.add(invoice);
                            }
                        }
                    }
                }
            }
            db.close();
        } catch (SQLException ex) {
            Logger.getLogger(Museum2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getProductLineData() throws SQLException {
        DBConnection db = new DBConnection();
        try {
            ResultSet rse = db.getResult("SELECT * FROM productline");
            while (rse.next()) {
                for (Sale sale : salesList) {
                    if (sale.getId() == rse.getInt("productline_sale_id")) {
                        
                        for (Product product : storeHandler.getProductsList()) {
                            if (product.getProductNumber() == rse.getInt("productline_product_id")) {
                                ProductLine productLine = new ProductLine(rse.getInt("productline_id"), sale, product, rse.getInt("productline_quantities"));
                                
                                productLinesList.add(productLine);
                                
                            }
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Museum2.class.getName()).log(Level.SEVERE, null, ex);
        }
        addProductLineFromDataToSale();
    }
    
    public void getTicketLineData() throws SQLException {
        
        DBConnection db = new DBConnection();
        try {
            ResultSet rse = db.getResult("SELECT * FROM ticketline");
            while (rse.next()) {
                for (Sale sale : salesList) {
                    if (sale.getId() == rse.getInt("ticketline_sale_id")) {
                        
                        for (TicketType ticketType : storeHandler.getTicketTypesList()) {
                            if (ticketType.getId() == rse.getInt("ticketline_tickettype_id")) {
                                
                                TicketLine ticketLine = new TicketLine(rse.getInt("ticketline_id"), sale, rse.getInt("ticketline_quantities"), rse.getString("ticketline_date"), ticketType);
                                
                                ticketLinesList.add(ticketLine);
                            }
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Museum2.class.getName()).log(Level.SEVERE, null, ex);
        }
        addTicketLineFromDataToSale();
    }
    
    public void getEventLineData() throws SQLException {
        DBConnection db = new DBConnection();
        
        try {
            ResultSet rse = db.getResult("SELECT * FROM eventline");
            while (rse.next()) {
                for (EventType eventtype : storeHandler.getEventTypesList()) {
                    if (eventtype.getId() == rse.getInt("eventline_eventtype_id")) {
                        for (Sale sale : salesList) {
                            if (sale.getId() == rse.getInt("eventline_sale_id")) {
                                
                                EventLine eventLine = new EventLine(rse.getInt("eventline_id"), eventtype, sale, rse.getInt("eventline_quantities"), rse.getString("eventline_date"), rse.getInt("eventline_customer_phone"), rse.getString("eventLine_place"));
                                
                                eventLinesList.add(eventLine);
                                
                            }
                        }
                        
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Museum2.class.getName()).log(Level.SEVERE, null, ex);
        }
        addEventLineFromDataToSale();
    }
    
    public void addTicketLineFromDataToSale() {
        for (Sale sale : salesList) {
            for (TicketLine ticketLine : ticketLinesList) {
                if (sale == ticketLine.getSale()) {
                    sale.setTicketLine(ticketLine);
                }
            }
        }
    }
    
    public void addEventLineFromDataToSale() {
        for (Sale sale : salesList) {
            for (EventLine eventLine : eventLinesList) {
                if (sale == eventLine.getSale()) {
                    sale.setEventLine(eventLine);
                }
            }
        }
    }
    
    public void addProductLineFromDataToSale() {
        for (Sale sale : salesList) {
            for (ProductLine productLine : productLinesList) {
                if (sale == productLine.getSale()) {
                    
                    sale.setProductLine(productLine);
                }
            }
        }
    }
    
    public void endSale(Sale sale1, boolean discount) {
        int id = 0;
        for (Invoice invoice : invoicesList) {
            if (id < invoice.getId()) {
                id = invoice.getId();
            }
        }
        id = id + 1;
        Calendar cal = Calendar.getInstance();
        String dato = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
        for (InvoiceStatus invoiceStatus : invoiceStatusesList) {
            if (invoiceStatus.getType().equals("Betalt")) {
                invoice = new Invoice(id, sale1, dato, sale1.getEndpriceDk(discount), sale1.getEndpriceEuro(discount), invoiceStatus);
            }
            invoicesList.add(invoice);
        }
        sale1.setInvoiceList(invoice);
        salesList.add(sale1);
        sale1.setDate(dato);
        try {
            DBConnection db = new DBConnection();
            db.execute("insert into sale values(" + sale1.getId() + "," + "1" + "," + sale1.getEmployee().getCpr() + ",'" + sale1.getDate() + "')");
            if (!sale1.getProductLine().isEmpty()) {
                for (ProductLine productLine : sale1.getProductLine()) {
                    db.execute("insert into productline values(" + productLine.getId() + "," + productLine.getProduct().getProductNumber() + "," + productLine.getSale().getId() + "," + productLine.getQuantities() + ")");
                    productLinesList.add(productLine);
                }
            }
            if (!sale1.getEventLine().isEmpty()) {
                for (EventLine eventLine : sale1.getEventLine()) {
                    db.execute("insert into eventline values (" + eventLine.getId() + "," + eventLine.getEventtype().getId() + "," + eventLine.getSale().getId() + "," + eventLine.getQuantities() + ",'" + eventLine.getDate() + "'," + eventLine.getCustomer() + ",'" + eventLine.getPlace() + "')");
                    eventLinesList.add(eventLine);
                }
            }
            if (!sale1.getTicketLine().isEmpty()) {
                for (TicketLine ticketLine : sale1.getTicketLine()) {
                    db.execute("insert into ticketline values(" + ticketLine.getId() + "," + ticketLine.getTicketType().getId() + "," + ticketLine.getSale().getId() + "," + ticketLine.getQuantities() + ",'" + ticketLine.getDate() + "')");
                    ticketLinesList.add(ticketLine);
                }
            }
            if (!sale1.getInvoiceList().isEmpty()) {
                for (Invoice invoice : sale1.getInvoiceList()) {
                    System.out.println(invoice.toString());
                    db.execute("insert into invoice values (" + invoice.getId() + "," + invoice.getSale().getId() + ",'" + invoice.getDate() + "'," + invoice.getPriceDk() + "," + invoice.getPriceEuro() + "," + invoice.getInvoiceStatus().getId() + ")");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }
    
    public ArrayList<PaymentType> getPaymentTypesList() {
        return paymentTypesList;
    }
    
    public void addToPaymentTypesList(PaymentType paymentTypes) {
        paymentTypesList.add(paymentTypes);
    }
    
    public ArrayList<Sale> getSalesList() {
        return salesList;
    }
    
    public void addToSalesList(Sale sale) {
        salesList.add(sale);
    }
    
    public ArrayList<ReturnProduct> getReturnProductsList() {
        return returnProductsList;
    }
    
    public void addToReturnProductsList(ReturnProduct returnProduct) {
        returnProductsList.add(returnProduct);
    }
    
    public ArrayList<InvoiceStatus> getInvoiceStatusesList() {
        return invoiceStatusesList;
    }
    
    public void addToInvoiceStatusesList(InvoiceStatus invoiceStatuses) {
        invoiceStatusesList.add(invoiceStatuses);
    }
    
    public ArrayList<Invoice> getInvoicesList() {
        return invoicesList;
    }
    
    public void addToInvoicesList(Invoice invoice) {
        invoicesList.add(invoice);
    }
    
    public ArrayList<Customer> getCustomersList() {
        return customersList;
    }
    
    public void addToCustomersList(Customer customers) {
        customersList.add(customers);
    }
    
    public void createNewSale() {
        int id = 0;
        for (Sale sale : salesList) {
            if (id < sale.getId()) {
                id = sale.getId();
            }
        }
        id = id + 1;
        sale = new Sale(id, null, null, null);
        salesList.add(sale);
        System.out.println("ny salg");
    }
    
    public void addEmployeeToSale() {
        sale.setEmployee(storeHandler.getEmployee());
    }
    
    public void clearSale() {
        sale.clearInvoiceList();
        sale.setPaymentType(null);
        sale.clearTicketLine();
        sale.clearProductLine();
        sale.clearEventLine();
        sale.clearEmployee();
        sale.setEndprice(false);
        listeners.notifyListeners("Update Basket");
    }
    
    public void clearSaleinventori() {
        sale.clearTicketLine();
        sale.clearProductLine();
        sale.clearEventLine();
        sale.setEndprice(false);
        listeners.notifyListeners("Update Basket");
    }
    
    public Sale getSale() {
        return sale;
    }
    
    public void addProductLineToSale(Product product, int productAmount) {
        boolean erder = false;
        boolean forlidt = false;
        int id = 0;
        if (!sale.getProductLine().isEmpty()) {
            for (int i = 0; i < sale.getProductLine().size(); i++) {
                if (sale.getProductLine().get(i).getProduct().equals(product)) {
                    if (sale.getProductLine().get(i).getQuantities() < product.getQuantities()) {
                        erder = true;
                        int quantities = sale.getProductLine().get(i).getQuantities();
                        sale.getProductLine().get(i).setQuantities(quantities + productAmount);
                    } else {
                        erder = true;
                        forlidt = true;
                    }
                }
            }
        }
        if (product.getQuantities() > 0) {
            if (!erder && !forlidt) {
                System.out.println("heeeej");
                for (ProductLine productLine : productLinesList) {
                    if (id < productLine.getId()) {
                        id = productLine.getId();
                    }
                }
                for (ProductLine productLine : sale.getProductLine()) {
                    if (id < productLine.getId()) {
                        id = productLine.getId();
                    }
                }
                id = id + 1;
                ProductLine pLine = new ProductLine(id, sale, product, productAmount);
                sale.setProductLine(pLine);
            }
        }
        listeners.notifyListeners("Update Basket");
    }
    
    public void removeProductLineFromSale(int id) {
        int plads = 0;
        for (int i = 0; i < sale.getProductLine().size(); i++) {
            if (sale.getProductLine().get(i).getId() == id) {
                plads = i;
                
            }
        }
        sale.getProductLine().remove(plads);
        listeners.notifyListeners("Update Basket");
    }
    
    public void addTicketLineToSale(TicketType ticketType, int quantities, String date) {
        boolean erder = false;
        int id = 0;
        if (!sale.getTicketLine().isEmpty()) {
            for (int i = 0; i < sale.getTicketLine().size(); i++) {
                if (sale.getTicketLine().get(i).getTicketType().equals(ticketType)) {
                    erder = true;
                    int ticketQuantities = sale.getTicketLine().get(i).getQuantities();
                    sale.getTicketLine().get(i).setQuantities(ticketQuantities + quantities);
                }
            }
        }
        if (!erder) {
            for (TicketLine ticketLine : ticketLinesList) {
                if (id < ticketLine.getId()) {
                    id = ticketLine.getId();
                }
            }
            for (TicketLine ticketLine : sale.getTicketLine()) {
                if (ticketLine != null) {
                    if (id < ticketLine.getId()) {
                        id = ticketLine.getId();
                    }
                }
            }
            id = id + 1;
            if (ticketType.getId() == 4) {
                if (quantities < 10) {
                    quantities = 10;
                }
            }
            TicketLine ticketLine = new TicketLine(id, sale, quantities, date, ticketType);
            sale.setTicketLine(ticketLine);
        }
        listeners.notifyListeners("Update Basket");
    }
    
    public void removeTicketLineFromSale(int id) {
        int plads = 0;
        for (int i = 0; i < sale.getTicketLine().size(); i++) {
            if (sale.getTicketLine().get(i).getId() == id) {
                plads = i;
                
            }
        }
        sale.getTicketLine().remove(plads);
        listeners.notifyListeners("Update Basket");
    }
    
    public void addEventLineToSale(EventType eventType, int quantities, String date, int customer, String place) {
        boolean erder = false;
        int id = 0;
        if (!sale.getEventLine().isEmpty()) {
            for (int i = 0; i < sale.getEventLine().size(); i++) {
                if (sale.getEventLine().get(i).getEventtype().equals(eventType)) {
                    erder = true;
                    int eventQuantities = sale.getEventLine().get(i).getQuantities();
                    sale.getEventLine().get(i).setQuantities(eventQuantities + quantities);
                }
                
            }
        }
        if (!erder) {
            for (EventLine eventLine : eventLinesList) {
                if (id < eventLine.getId()) {
                    id = eventLine.getId();
                }
            }
            for (EventLine eventLine : sale.getEventLine()) {
                if (eventLine != null) {
                    if (id < eventLine.getId()) {
                        id = eventLine.getId();
                    }
                }
            }
            id = id + 1;
            EventLine eventLine = new EventLine(id, eventType, sale, quantities, date, customer, place);
            sale.setEventLine(eventLine);
        }
        listeners.notifyListeners("Update Basket");
    }
    
    public void removeEventLineFromSale(int id) {
        int plads = 0;
        for (int i = 0; i < sale.getEventLine().size(); i++) {
            if (sale.getEventLine().get(i).getId() == id) {
                plads = i;
                
            }
        }
        sale.getEventLine().remove(plads);
        listeners.notifyListeners("Update Basket");
    }
}
