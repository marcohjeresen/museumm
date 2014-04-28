/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utillity;

import db.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import model.handler.*;
import museum2.Museum2;

/**
 *
 * @author markh_000
 */
public class StatistikHandler {

    private StoreHandler storeHandler;
    private SaleHandler saleHandler;
    private Listeners listeners;
    private DBConnection dBConnection;
    private ArrayList valgte;

    private ArrayList<Line> stringList;
    private ArrayList<Line> typeList;

    public StatistikHandler(StoreHandler storeHandler1, SaleHandler saleHandler1, Listeners listeners1) {
        this.storeHandler = storeHandler1;
        this.saleHandler = saleHandler1;
        this.listeners = listeners1;
        stringList = new ArrayList<>();
        typeList = new ArrayList<>();
        valgte = new ArrayList();
        stringList.removeAll(stringList);
    }

    public void setvalgte(String type, String typeNavn) {

    }

    public void setList(String type, String typeNavn) {
        Line line;
        switch (type) {
            case "Product":
                DBConnection db = new DBConnection();

                for (Product product : storeHandler.getProductsList()) {
                    line = new Line(product.getProductNumber() + "  " + product.getName(), 0, 0);
                    try {
                        ResultSet rse = db.getResult("select product.product_numberid, productline_quantities\n"
                                + "from product, productline\n"
                                + "where productline_product_id = " + product.getProductNumber() + " and product_numberid = " + product.getProductNumber() + "\n"
                                + "order by product_numberid;");
                        while (rse.next()) {
                            line.setPriceDk(line.getPriceDk() + rse.getInt("productline_quantities"));

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Museum2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    typeList.add(line);
                }

                switch (typeNavn) {

                    case "Mest Solgte":

                        break;
                    case "Størst Indtjening":

                        break;
                    case "Mindst Antal":

                        break;
                }

                break;

        }
    }

    public void clearList() {
        stringList.removeAll(stringList);
    }

    public String getString() {
        stringList.removeAll(stringList);
        int plads = 0;
        for (int i = 0; i < typeList.size(); i++) {
            boolean erder = false;
            for (int j = 0; j < stringList.size(); j++) {
                if  (typeList.get(i).getPriceDk() > stringList.get(j).getPriceDk() && typeList.get(i).getPriceDk() != 0) {
                    System.out.println("t"+typeList.get(i).getPriceDk()+" " +i);
                    System.out.println("s"+stringList.get(j).getPriceDk()+" "+j);
                    System.out.println("");
                    plads = 0;
                    erder = true;
                    if (j <= plads) {
                        System.out.println(plads);
                        System.out.println(j);
                        plads = j;
                        
                    }

                    
                } 

            }
            if (!erder) {
                stringList.add(typeList.get(i));
                
            } else {
                stringList.add(plads, typeList.get(i));
                
            }

        }

        String text = "";

        for (Line line : stringList) {
            text = text + line.getText() + "\t\t\tAntal: " + line.getPriceDk() + "\n";
        }
        return text;
    }

}
