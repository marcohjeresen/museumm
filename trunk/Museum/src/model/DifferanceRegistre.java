/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.*;

/**
 *
 * @author MarcoPc
 */
public class DifferanceRegistre {

    private int id;
    private Employee employee;
    private int currentCashDk;
    private int currentCahsEuro;
    private int expectedDk;
    private int expectedEuro;
    private int differanceDk;
    private int differanceEuro;
    private String date;

    public DifferanceRegistre(int id, Employee employee, int currentCashDk, int currentCahsEuro, int expectedDk, int expectedEuro, int differanceDk, int differanceEuro, String date) {
        this.id = id;
        this.employee = employee;
        this.currentCashDk = currentCashDk;
        this.currentCahsEuro = currentCahsEuro;
        this.expectedDk = expectedDk;
        this.expectedEuro = expectedEuro;
        this.differanceDk = differanceDk;
        this.differanceEuro = differanceEuro;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getCurrentCashDk() {
        return currentCashDk;
    }

    public void setCurrentCashDk(int currentCashDk) {
        this.currentCashDk = currentCashDk;
    }

    public int getCurrentCahsEuro() {
        return currentCahsEuro;
    }

    public void setCurrentCahsEuro(int currentCahsEuro) {
        this.currentCahsEuro = currentCahsEuro;
    }

    public int getExpectedDk() {
        return expectedDk;
    }

    public void setExpectedDk(int expectedDk) {
        this.expectedDk = expectedDk;
    }

    public int getExpectedEuro() {
        return expectedEuro;
    }

    public void setExpectedEuro(int expectedEuro) {
        this.expectedEuro = expectedEuro;
    }

    public int getDifferanceDk() {
        return differanceDk;
    }

    public void setDifferanceDk(int differanceDk) {
        this.differanceDk = differanceDk;
    }

    public int getDifferanceEuro() {
        return differanceEuro;
    }

    public void setDifferanceEuro(int differanceEuro) {
        this.differanceEuro = differanceEuro;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DifferanceRegistre{" + "id=" + id + ", employee=" + employee + ", currentCashDk=" + currentCashDk + ", currentCahsEuro=" + currentCahsEuro + ", expectedDk=" + expectedDk + ", expectedEuro=" + expectedEuro + ", differanceDk=" + differanceDk + ", differanceEuro=" + differanceEuro + ", date=" + date + '}';
    }
    

    
    
}
