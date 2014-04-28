/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package museum2;
import model.handler.*;
import model.controller.*;

import model.*;
import utillity.StatistikHandler;
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
       Listeners listeners = new Listeners();
       StoreHandler storeHandler = new StoreHandler(listeners);
       StoreController storeController = new StoreController(storeHandler);
       MoneyHandler moneyHandler = new MoneyHandler(storeHandler,listeners);
       SaleHandler saleHandler = new SaleHandler(storeHandler, moneyHandler, listeners);
       StatistikHandler statistikHandler = new StatistikHandler(storeHandler, saleHandler, listeners);
       MainView mainView = new MainView(moneyHandler, saleHandler, storeHandler, storeController,listeners,statistikHandler);
       mainView.setVisible(true);
      
       
    }
    
}
