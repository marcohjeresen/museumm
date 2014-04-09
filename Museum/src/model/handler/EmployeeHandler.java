/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.handler;

import db.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import museum.Museum;

/**
 *
 * @author MarcoPc
 */
public class EmployeeHandler {

    private Employee employee;
    private ArrayList<Employee> employeeList;
    private Listeners listeners;

    public EmployeeHandler(Listeners listeners1) {
        this.listeners = listeners1;
        employeeList = new ArrayList<>();
        getdatabase();
    }

    public void getdatabase() {
        DBConnection db = new DBConnection();
        try {
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
    }

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(ArrayList<Employee> employeeList) {
        this.employeeList = employeeList;
    }
    
    public void setLoginEmployee(int kode){
        for (Employee employee1 : employeeList) {
            if (employee1.getPassword() == kode) {
                this.employee = employee1;
                listeners.notifyListeners("Employee log");
                
                
            }
        }
    }
    public void logEmployeeUd(){
        employee = null;
        listeners.notifyListeners("Employee log");
    }
    
    public Employee getLogIndEmployee(){
        return employee;
    }
    
    
}
