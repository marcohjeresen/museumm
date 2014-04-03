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
import veiw.*;

/**
 *
 * @author markh_000
 */
public class Museum {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Listeners listeners = new Listeners();
        CustomerHandler customerHandler = new CustomerHandler();
        PaymentTypeHandler paymentTypeHandler = new PaymentTypeHandler();

        EmployeeHandler employeeHandler = new EmployeeHandler();
        CashHandler cashHandler = new CashHandler(employeeHandler);

        SaleHandler saleHandler = new SaleHandler(employeeHandler, paymentTypeHandler);
        ProductHandler productHandler = new ProductHandler(saleHandler,listeners);
        EventHandler eventHandler = new EventHandler(saleHandler, customerHandler);
        TicketHandler ticketHandler = new TicketHandler(saleHandler);
        InvoiceHandler invoiceHandler = new InvoiceHandler(saleHandler);
        KurvHandler kurvHandler = new KurvHandler(productHandler, customerHandler, paymentTypeHandler, ticketHandler, employeeHandler, eventHandler, saleHandler, invoiceHandler);

        
        Guie2 gu = new Guie2(productHandler, listeners, kurvHandler);
        gu.setVisible(true);
    }

}
