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
        
      
        ArrayList<Employee> employeeList = new ArrayList<>();
        try {
            DBConnection db = new DBConnection();

            ResultSet rs = db.getResult("SELECT * FROM employee");
            while (rs.next()) {
                Employee em = new Employee(rs.getInt("employee_cpr"), rs.getString("employee_name"), rs.getString("employee_adresse"),
                        rs.getInt("employee_postzip"), rs.getString("employee_city"), rs.getString("employee_username"), rs.getInt("employee_password"));
                employeeList.add(em);

            }

        } catch (SQLException ex) {
            Logger.getLogger(Museum.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            DBConnection db = new DBConnection();

            ResultSet rse = db.getResult("SELECT * FROM employeephone");
            while (rse.next()) {
                for (Employee employee : employeeList) {
                    if (employee.getCpr() == rse.getInt("employeephone_cpr")) {
                        employee.setPhoneList(rse.getInt("employeephone_phone"));
                    }
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(Museum.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        

        for (Employee employee : employeeList) {
            System.out.println(employee.getCpr());
        }
        Gui gu = new Gui(employeeList);
        gu.setVisible(true);
    }

}
