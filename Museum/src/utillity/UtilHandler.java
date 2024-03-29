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
public class UtilHandler {
private static UtilHandler utilHandler;
    private StoreHandler storeHandler;
    private SaleHandler saleHandler;
    private Listeners listeners;
    private DBConnection dBConnection;
    private ArrayList valgte;

    private ArrayList<Line> stringList;
    private ArrayList<Line> typeList;
    private ArrayList<StockLine> productsList;

    private UtilHandler() throws SQLException {
        storeHandler = StoreHandler.getStoreHandler();
        saleHandler = SaleHandler.getSaleHandler();
        listeners = Listeners.getList();
        stringList = new ArrayList<>();
        typeList = new ArrayList<>();
        valgte = new ArrayList();
        productsList = new ArrayList<>();
        stringList.removeAll(stringList);
    }
    
    public static UtilHandler getUtilHandler() throws SQLException{
        if (utilHandler == null) {
            utilHandler = new UtilHandler();
        }
        return utilHandler;
    }

    public void setvalgte(String type, String typeNavn) {

    }

    public ArrayList<StockLine> getStockList() {
        productsList.removeAll(productsList);
        StockLine st= new StockLine("Nummer", "Navn", "Suplier", "KøbsPris", "Antal");
        productsList.add(st);
        DBConnection db = new DBConnection();
            try {
                ResultSet rse = db.getResult("SELECT * FROM product order by product_quantities");
                while (rse.next()) {
                    for (ProductGroup group : storeHandler.getProductGroupList()) {
                        if (group.getGroupId() == rse.getInt("product_groupid")) {
                            StockLine l = new StockLine(""+rse.getInt("product_numberid"), rse.getString("product_name"), rse.getString("product_supplier"), ""+rse.getInt("product_buyprice"), ""+rse.getInt("product_quantities"));

                            productsList.add(l);
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Museum2.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return productsList;
    }
    
    public void print(){
        
    }
}
