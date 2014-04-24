/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package museum2;
import handler.*;
import model.*;
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
       MoneyHandler moneyHandler = new MoneyHandler(storeHandler,listeners);
       SaleHandler saleHandler = new SaleHandler(storeHandler, moneyHandler, listeners);
       
       MainView mainView = new MainView(moneyHandler, saleHandler, storeHandler, listeners);
       mainView.setVisible(true);
       
       Calendar cal = Calendar.getInstance();
       Calendar dato1 = Calendar.getInstance();
       dato1.set(2014, 2, 24);
       Calendar dato2 = Calendar.getInstance();
       dato2.set(2014, 3, 24);
       
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date test = formatter.parse("2014-04-25 16:02:00");
            Calendar testCal = Calendar.getInstance();
            testCal.setTime(test);
            System.out.println("Dato: "+testCal.getTime());
        } catch (ParseException ex) {
            System.out.println("Date parse error");
            System.out.println(ex.getLocalizedMessage());
        }
       
    }
    
}
