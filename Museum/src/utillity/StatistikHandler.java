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
import model.TicketLine;
import model.handler.SaleHandler;
import museum2.Museum2;
import utillity.view.CalenderPanel;

/**
 *
 * @author MarcoPc
 */
public class StatistikHandler {

    private static StatistikHandler statistikHandler;
    private ArrayList<Line> lineList;
    private DateFormatTools dateFormat;
    private SaleHandler saleHandler;

    private StatistikHandler() throws SQLException {

        lineList = new ArrayList<>();
        dateFormat = DateFormatTools.getDateFormat();
        saleHandler = SaleHandler.getSaleHandler();
    }

    public static StatistikHandler getStatistikHandler() throws SQLException {
        if (statistikHandler == null) {
            statistikHandler = new StatistikHandler();
        }
        return statistikHandler;
    }

//    public void getStat(String fromDate) {
//        lineList = new ArrayList<>();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        Calendar dateFrom = Calendar.getInstance();
//        
//        try {
//            Date d = formatter.parse(fromDate);
//            
//            dateFrom.setTime(d);
//            
//        } catch (ParseException ex) {
//            System.out.println("Date parse error");
//            System.out.println(ex.getLocalizedMessage());
//        }
//        int countType = 4;
//        String type = "";
//        for (int i = 0; i < countType; i++) {
//            switch (i + 1) {
//                case 1:
//                    type = "Gratister";
//                    break;
//                case 2:
//                    type = "Voksne";
//                    break;
//                case 3:
//                    type = "Børn";
//                    break;
//                case 4:
//                    type = "Gruppe";
//                    break;
//            }
//            
//            int count = 6;
//            for (int j = 0; j < count; j++) {
//                if (j != 0) {
//                    dateFrom.roll(Calendar.DAY_OF_MONTH, true);
//                }
//                String date1 = dateFrom.get(Calendar.YEAR) + "-" + (dateFrom.get(Calendar.MONTH) + 1) + "-" + (dateFrom.get(Calendar.DAY_OF_MONTH))
//                        + " " + "00:00:00";
//                
//                String date2 = dateFrom.get(Calendar.YEAR) + "-" + (dateFrom.get(Calendar.MONTH) + 1) + "-" + (dateFrom.get(Calendar.DAY_OF_MONTH))
//                        + " " + "23:59:59";
//                
//                DBConnection db = new DBConnection();
//                try {
//                    
//                    ResultSet rse = db.getResult("select sum(ticketline_quantities) from ticketline, sale "
//                            + "where sale_date > '" + date1 + "' and sale_date < '" + date2 + "' "
//                            + "and ticketline_sale_id = sale_id "
//                            + "and ticketline_tickettype_id = " + (i + 1));
//                    
//                    while (rse.next()) {
//                        
//                        Line l = new Line(type, 0, 0);
//                        l.setDay(j + 1);
//                        l.setSum(rse.getInt("sum(ticketline_quantities)"));
//                        lineList.add(l);
//                        
//                    }
//                } catch (SQLException ex) {
//                    Logger.getLogger(Museum2.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                System.out.println(date1);
//                System.out.println(date2);
//                System.out.println(i + 1);
//                db.close();
//            }
//        }
//        show();
//    }
    public ArrayList<Line> getWeekStat(String Date) {

        lineList.removeAll(lineList);

        Calendar fromDate = dateFormat.getStartDateFromString(Date);
        Calendar endDate = dateFormat.getNextday(dateFormat.getDateFromString(Date), 7);

        for (TicketLine tl : saleHandler.getTicketLinesList()) {
            Calendar tlDate = dateFormat.getStartDateFromString(tl.getDate());
            boolean erder = false;
            if (tlDate.after(fromDate) && tlDate.before(endDate) || tlDate.equals(fromDate) && tlDate.before(endDate)) {
                if (!lineList.isEmpty()) {

                    for (int i = 0; i < lineList.size(); i++) {
                        Calendar lnDate = dateFormat.getStartDateFromString(lineList.get(i).getTicketDate());
                        
                        if (tl.getTicketType().getType().equals("Voksenbillet, over 18 år") && tlDate.equals(lnDate)) {
                            erder = true;
                            int quantities = lineList.get(i).getTkAdultQu();
                            lineList.get(i).setTkAdultQu(quantities + tl.getQuantities());
                           
                        } else if (tl.getTicketType().getType().equals("Børnebillet, under 18 år") && tlDate.equals(lnDate)) {
                            erder = true;
                            int quantities = lineList.get(i).getTkKidsQu();
                            lineList.get(i).setTkKidsQu(quantities + tl.getQuantities());
                           
                        } else if (tl.getTicketType().getType().equals("Gruppebillet, min. 10 personer") && tlDate.equals(lnDate)) {
                            erder = true;
                            int quantities = lineList.get(i).getTkAGroupQu();
                            lineList.get(i).setTkAGroupQu(quantities + tl.getQuantities());
                           
                        }else if (tl.getTicketType().getType().equals("Gratister (Museumskort, foregning, ovs)") && tlDate.equals(lnDate)) {
                            erder = true;
                            int quantities = lineList.get(i).getTkFreeQu();
                            lineList.get(i).setTkFreeQu(quantities + tl.getQuantities());
                           
                        }
                    }
                    if (!erder) {
                        Line l = new Line(tl.getTicketType().getType(), 0, 0);

                        l.setTicketDate(dateFormat.getDateFromCal(tlDate));
                        l.setTicketQuantities(tl.getQuantities());
                        
                        lineList.add(l);
                    }
                } else {
                    Line l = new Line(tl.getTicketType().getType(), 0, 0);
                    l.setTicketDate(dateFormat.getDateFromCal(tlDate));
                    l.setTicketQuantities(tl.getQuantities());
                    
                    lineList.add(l);
                }
            }
        }
        show();
        return lineList;
    }

    public void show() {
        for (Line line : lineList) {
            System.out.println(line.getText() + " " + line.getTicketDate() + " " + line.getTicketQuantities() + " ");
        }
    }
}
