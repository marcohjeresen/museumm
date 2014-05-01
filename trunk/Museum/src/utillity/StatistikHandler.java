/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utillity;

import db.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ProductGroup;
import museum2.Museum2;
import utillity.view.CalenderPanel;

/**
 *
 * @author MarcoPc
 */
public class StatistikHandler {

    private ArrayList<Line> stateList;

    public StatistikHandler() {
    }

    public void getStat(String fromDate) throws SQLException, ParseException {
        stateList = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar dateFrom = Calendar.getInstance();

        try {
            Date d = formatter.parse(fromDate);
            
            dateFrom.setTime(d);

        } catch (ParseException ex) {
            System.out.println("Date parse error");
            System.out.println(ex.getLocalizedMessage());
        }
        int countType = 4;
        String type = "";
        for (int i = 0; i < countType; i++) {
            switch (i + 1) {
                case 1:
                    type = "Gratister";
                    break;
                case 2:
                    type = "Voksne";
                    break;
                case 3:
                    type = "Børn";
                    break;
                case 4:
                    type = "Gruppe";
                    break;
            }

            int count = 6;
            for (int j = 0; j < count; j++) {
                if (j != 0) {
                    dateFrom.roll(Calendar.DAY_OF_MONTH, true);
                }
                String date1 = dateFrom.get(Calendar.YEAR) + "-" + (dateFrom.get(Calendar.MONTH)+1) + "-" + (dateFrom.get(Calendar.DAY_OF_MONTH))
                        + " " + "00:00:00";
                
                
                String date2 = dateFrom.get(Calendar.YEAR) + "-" + (dateFrom.get(Calendar.MONTH)+1) + "-" + (dateFrom.get(Calendar.DAY_OF_MONTH))
                        + " " + "23:59:59";
                
                DBConnection db = new DBConnection();
                try {
                    
                    ResultSet rse = db.getResult("select sum(ticketline_quantities) from ticketline, sale "
                            + "where sale_date > '" + date1 + "' and sale_date < '" + date2 + "' "
                            + "and ticketline_sale_id = sale_id "
                            + "and ticketline_tickettype_id = " + (i+1));

                    while (rse.next()) {

                        Line l = new Line(type, 0, 0);
                        l.setDay(j + 1);
                        l.setSum(rse.getInt("sum(ticketline_quantities)"));
                        stateList.add(l);

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Museum2.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(date1);
                System.out.println(date2);
                System.out.println(i+1);
                db.close();
            }
        }
        show();
    }

    public void show() {
        for (Line line : stateList) {
            System.out.println(line.getText() + " " + line.getDay() + " " + line.getSum());
        }
    }
}
