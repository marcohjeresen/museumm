/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package museum2;
import docTools.DocHandler;
import model.handler.*;
import model.controller.*;

import model.*;
import utillity.UtilHandler;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.MainView;
/**
 *
 * @author markh_000
 */
public class Museum2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
       Listeners listeners = Listeners.getList();
       StoreHandler storeHandler = StoreHandler.getStoreHandler();
       StoreController storeController = StoreController.getStoreController();
       MoneyHandler moneyHandler = MoneyHandler.getMoneyHandler();
       SaleHandler saleHandler = SaleHandler.getSaleHandler();
       UtilHandler utilHandler = UtilHandler.getUtilHandler();
       
       MainView mainView = new MainView(moneyHandler, saleHandler, storeHandler, storeController,listeners,utilHandler);
       mainView.setSize(1024, 730);
       mainView.setVisible(true);
      
       
    }
    
}
