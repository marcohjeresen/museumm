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
    private Listeners listeners;

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
    
    public void setProductList(Product product){
        productList.add(product);
        listeners.notifyListeners();
        
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    @Override
    public String toString() {
        double Prisdk = 0;
        double Priseuro = 0;
        String kurv = "Nummer:\tVareTitle:\t\tPrisDk:\tPrisEuro:\n";
        for (Product product : productList) {
            if (product.getName().length() < 15) {
                kurv = kurv + product.getProductNumber()+ "\t"+product.getName()+"\t\t"+product.getPriceDk()+"\t"+product.getPriceEuro()+"\n";
            }else{
                 kurv = kurv + product.getProductNumber()+ "\t"+product.getName()+"\t"+product.getPriceDk()+"\t"+product.getPriceEuro()+"\n";
            }
           
            Prisdk = Prisdk + product.getPriceDk();
            Priseuro = Priseuro + product.getPriceEuro();
            
        }
        
        kurv = kurv + "\nTotal: \tPrisdDk: "+Prisdk+"           PrisEuro: "+Priseuro;
        return kurv;
    }

   
    
    
    
    
}
