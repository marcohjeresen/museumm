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
import model.handler.EmployeeHandler;
import model.handler.ProductHandler;
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
        ProductHandler ph = new ProductHandler();
        EmployeeHandler eh = new EmployeeHandler();
        
        for (Product product : ph.getProductList()) {
            System.out.println(product.getGroupNumber().getGroupType());
        }

        for (Employee employee : eh.getEmployeeList()) {
            System.out.println(employee.getName());
        }
     
    }

}
