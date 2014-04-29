/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utillity;

/**
 *
 * @author MarcoPc
 */
public class StockLine {
private String producNumber;
private String producName;
private String producSuppl;
private String producBuyPrice;
private String producQuantitis;

    public StockLine(String producNumber, String producName, String producSuppl, String producBuyPrice, String producQuantitis) {
        this.producNumber = producNumber;
        this.producName = producName;
        this.producSuppl = producSuppl;
        this.producBuyPrice = producBuyPrice;
        this.producQuantitis = producQuantitis;
    }

    public String getProducNumber() {
        return producNumber;
    }

    public void setProducNumber(String producNumber) {
        this.producNumber = producNumber;
    }

    public String getProducName() {
        return producName;
    }

    public void setProducName(String producName) {
        this.producName = producName;
    }

    public String getProducSuppl() {
        return producSuppl;
    }

    public void setProducSuppl(String producSuppl) {
        this.producSuppl = producSuppl;
    }

    public String getProducBuyPrice() {
        return producBuyPrice;
    }

    public void setProducBuyPrice(String producBuyPrice) {
        this.producBuyPrice = producBuyPrice;
    }

    public String getProducQuantitis() {
        return producQuantitis;
    }

    public void setProducQuantitis(String producQuantitis) {
        this.producQuantitis = producQuantitis;
    }

    @Override
    public String toString() {
        String prod = "";
        if (producSuppl.matches("antikvitet.net")) {
            System.out.println(producSuppl.length());
        }
        if (producName.length() <= 10) {
            if (producSuppl.length() >14) {
                prod = producNumber +" \t"+producName+" \t\t\t"+producSuppl+" \t"+producBuyPrice+" \t\t"+producQuantitis;
            }else{
                prod = producNumber +" \t"+producName+" \t\t\t"+producSuppl+" \t\t"+producBuyPrice+" \t\t"+producQuantitis;
            }
        }else if (producName.length() > 10 && producName.length() <= 13) {
            if (producSuppl.length() >14) {
                prod = producNumber +" \t"+producName+" \t\t\t"+producSuppl+" \t"+producBuyPrice+" \t\t"+producQuantitis;
            }else{
                prod = producNumber +" \t"+producName+" \t\t\t"+producSuppl+" \t\t"+producBuyPrice+" \t\t"+producQuantitis;
            }
            
        }else if ((producName.length() > 13 && producName.length() <= 15)) {
            if (producSuppl.length() >14) {
                prod = producNumber +" \t"+producName+" \t\t"+producSuppl+" \t"+producBuyPrice+" \t\t"+producQuantitis;
            }else{
                prod = producNumber +" \t"+producName+" \t\t"+producSuppl+" \t\t"+producBuyPrice+" \t\t"+producQuantitis;
            }
        } else if (producName.length() > 15 && producName.length() <= 20) {
            if (producSuppl.length() >14) {
                prod = producNumber +" \t"+producName+" \t\t"+producSuppl+" \t"+producBuyPrice+" \t\t"+producQuantitis;
            }else{
                prod = producNumber +" \t"+producName+" \t\t"+producSuppl+" \t\t"+producBuyPrice+" \t\t"+producQuantitis;
            }
        }else if (producName.length() > 20 && producName.length() <= 25){
            if (producSuppl.length() >14) {
                prod = producNumber +" \t"+producName+" \t\t"+producSuppl+" \t"+producBuyPrice+" \t\t"+producQuantitis;
            }else{
                prod = producNumber +" \t"+producName+" \t\t"+producSuppl+" \t\t"+producBuyPrice+" \t\t"+producQuantitis;
            }
        }else if (producName.length() > 25 && producName.length() <= 30) {
                   if (producSuppl.length() >14) {
                prod = producNumber +" \t"+producName+"\t\t"+producSuppl+" \t"+producBuyPrice+" \t\t"+producQuantitis;
            }else{
                prod = producNumber +" \t"+producName+"\t\t"+producSuppl+" \t\t"+producBuyPrice+" \t\t"+producQuantitis;
            }   


        }
        return prod;
    }


}
