/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.handler;

import utillity.Listeners;
import db.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import model.controller.*;
import museum2.Museum2;

/**
 *
 * @author markh_000
 */
public class StoreHandler {
    private static StoreHandler storeHandler;
    private ProductGroup productGroup;
    private Product product;
    private Product searchProduct;
    private ArrayList<ProductGroup> productGroupList;
    private ArrayList<Product> productsList;
    
    private TicketType ticketType;
    private ArrayList<TicketType> ticketTypesList;
    
    private EventType eventType;
    private ArrayList<EventType> eventTypesList;
    
    private Employee employee;
    private ArrayList<Employee> employeesList;
    
    private String showTypePanel;
    
    private Listeners listeners;
    private Calendar calendar;
    private StoreController storeController;
    
    private StoreHandler() throws SQLException {
        listeners = Listeners.getList();
        productGroupList = new ArrayList<>();
        productsList = new ArrayList<>();
        ticketTypesList = new ArrayList<>();
        eventTypesList = new ArrayList<>();
        employeesList = new ArrayList<>();
        
    }
    public static StoreHandler getStoreHandler() throws SQLException{
        if (storeHandler == null) {
            storeHandler = new StoreHandler();
        }
        return storeHandler;
    }
    
    public ProductGroup getProductGroup() {
        return productGroup;
    }
    
    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }
    
    public Product getProduct() {
        return product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
    
    public ArrayList<ProductGroup> getProductGroupList() {
        return productGroupList;
    }
    
    public void setProductGroupList(ProductGroup productGroup1) {
        productGroupList.add(productGroup1);
    }
    
    public ArrayList<Product> getProductsList() {
        return productsList;
    }
    
    public void setProductsList(Product products1) {
        productsList.add(products1);
    }
    
    public TicketType getTicketType() {
        return ticketType;
    }
    
    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }
    
    public ArrayList<TicketType> getTicketTypesList() {
        return ticketTypesList;
    }
    
    public void setTicketTypesList(TicketType ticketType1) {
        ticketTypesList.add(ticketType1);
    }
    
    public EventType getEventType() {
        return eventType;
    }
    
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
    
    public ArrayList<EventType> getEventTypesList() {
        return eventTypesList;
    }
    
    public void setEventTypesList(EventType eventTypes1) {
        eventTypesList.add(eventTypes1);
    }
    
    public Employee getEmployee() {
        return employee;
    }
    
    public void logoutEmployee() {
        employee = null;
        listeners.notifyListeners("Employee Login");
    }
    
    public void setEmployee(int kode) {
        for (Employee employee1 : employeesList) {
            if (employee1.getPassword() == kode) {
                employee = employee1;
                
            }
        }
        listeners.notifyListeners("Employee Login");
    }
    
    public ArrayList<Employee> getEmployeesList() {
        return employeesList;
    }
    
    public void setEmployeesList(Employee employees1) {
        employeesList.add(employees1);
    }
    
    public void setShowType(String typeNavn) {
        showTypePanel = typeNavn;
        listeners.notifyListeners("ShowType Change");
    }
    
    public String getShowType() {
        return showTypePanel;
    }
    
    public void searchProduct(int productNumber) {
        for (Product product : productsList) {
            if (product.getProductNumber() == productNumber) {
                searchProduct = product;
                setShowType("Product");
                listeners.notifyListeners("Search Product");
                
            }
        }
    }
    
    public Product getSearchProduct() {
        return searchProduct;
    }
    
    
    
    public void SetDateToCalender(Calendar cal){
        calendar = cal;
    }
    public Calendar getDateToCalendar(){
        return calendar;
    }
}
