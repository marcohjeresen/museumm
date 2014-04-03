/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.handler;
import java.util.ArrayList;
import model.*;
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

    public KurvHandler(ProductHandler productHandler, CustomerHandler customerHandler, PaymentTypeHandler paymentTypeHandler, TicketHandler ticketHandler, EmployeeHandler employeeHandler, EventHandler eventHandler, SaleHandler saleHandler, InvoiceHandler invoiceHandler) {
        this.productHandler = productHandler;
        this.customerHandler = customerHandler;
        this.paymentTypeHandler = paymentTypeHandler;
        this.ticketHandler = ticketHandler;
        this.employeeHandler = employeeHandler;
        this.eventHandler = eventHandler;
        this.saleHandler = saleHandler;
        this.invoiceHandler = invoiceHandler;
        productList = new ArrayList<>();
        ticketTypesList = new ArrayList<>();
        eventTypesList = new ArrayList<>();
        
    }
    
    public void setProductList(Product product){
        productList.add(product);
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    @Override
    public String toString() {
        String kurv = "";
        for (Product product : productList) {
            kurv = kurv + product.toString()+"\n";
        }
        return kurv;
    }

   
    
    
    
    
}
