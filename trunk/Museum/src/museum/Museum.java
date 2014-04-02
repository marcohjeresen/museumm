/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museum;

import model.handler.SaleHandler;
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

        EmployeeHandler employeeHandler = new EmployeeHandler();
        CashHandler cashHandler = new CashHandler(employeeHandler);

        SaleHandler saleHandler = new SaleHandler(employeeHandler, paymentTypeHandler);
        ProductHandler productHandler = new ProductHandler(saleHandler);
        EventHandler eventHandler = new EventHandler(saleHandler, customerHandler);
        TicketHandler ticketHandler = new TicketHandler(saleHandler);
        InvoiceHandler invoiceHandler = new InvoiceHandler(saleHandler);

        for (Sale sale : saleHandler.getSaleList()) {
            System.out.println(sale.toString());
            
        }
//        System.out.println(saleHandler.searchSale(2).toString());
//        Gui gu = new Gui(employeeHandler.getEmployeeList());
//        gu.setVisible(true);
    }

}
