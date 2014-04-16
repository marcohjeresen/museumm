/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Date;

/**
 *
 * @author markh_000
 */
public class CashRegister {
    private int id;
    private String date;
    private int amountDk;
    private int amountEuro;
    private Employee employee;

    public CashRegister(int id,String date, int amountDk, int amountEuro, Employee employee) {
        this.id = id;
        this.date = date;
        this.amountDk = amountDk;
        this.amountEuro = amountEuro;
        this.employee = employee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmountDk() {
        return amountDk;
    }

    public void setAmountDk(int amountDk) {
        this.amountDk = amountDk;
    }

    public int getAmountEuro() {
        return amountEuro;
    }

    public void setAmountEuro(int amountEuro) {
        this.amountEuro = amountEuro;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CashRegister{" + "id=" + id + ", date=" + date + ", amountDk=" + amountDk + ", amountEuro=" + amountEuro + ", employee=" + employee + '}';
    }

   
    
}
