/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museum;

import db.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import model.handler.*;
import veiw.Gui;

/**
 *
 * @author markh_000
 */
public class Museum {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CustomerHandler customerHandler = new CustomerHandler(); 
        PaymentTypeHandler paymentTypeHandler = new PaymentTypeHandler();
        ProductHandler productHandler = new ProductHandler();
        EmployeeHandler employeeHandler = new EmployeeHandler();
        CashHandler cashHandler = new CashHandler(employeeHandler);
        SaleHandler saleHandler = new SaleHandler(employeeHandler, paymentTypeHandler);
        EventHandler eventHandler = new EventHandler(saleHandler, customerHandler);
        TicketHandler ticketHandler = new TicketHandler(saleHandler);
        InvoiceHandler invoiceHandler = new InvoiceHandler(saleHandler);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//        for (Product product : ph.getProductList()) {
//            System.out.println(product.getGroupNumber().getGroupType());
//        }
//
//        for (Employee employee : eh.getEmployeeList()) {
//            System.out.println(employee.getName());
//        }
//        
//        for (CashRegister cashreg : ch.getCashList()) {
//            System.out.println(cashreg.getDate()+" "+cashreg.getEmployee().getName());
//        }
//        
//        for (Sale sale : sh.getSaleList()) {
//            System.out.println(sale.toString());
//        }
//        
//        for (EventLine event : evt.getEventLineList()) {
//            System.out.println(event.toString());
//        }
     
    }

}
