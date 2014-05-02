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
        dateFormat = new DateFormatTools();
        saleHandler = SaleHandler.getSaleHandler();
    }

    public static StatistikHandler getStatistikHandler() throws SQLException {
        if (statistikHandler == null) {
            statistikHandler = new StatistikHandler();
        }
        return statistikHandler;
    }

    public ArrayList<Line> getWeekStat(String Date) {

        lineList.removeAll(lineList);
        Calendar StringDate = dateFormat.getStartDateFromString(Date);
        Calendar fromDate = StringDate;
        int count = 6;
        for (int i = 0; i < count; i++) {
            if (count != 0) {
                fromDate = dateFormat.getNextday(dateFormat.getDateFromString(Date), i);
            }
            for (TicketLine tl : saleHandler.getTicketLinesList()) {
                Calendar tlDate = dateFormat.getStartDateFromString(tl.getDate());
                boolean erder = false;
                if (tlDate.equals(fromDate)) {
                    if (!lineList.isEmpty()) {
                        for (int j = 0; j < lineList.size(); j++) {
                            Calendar lnDate = dateFormat.getStartDateFromString(lineList.get(j).getTicketDate());
                            if (lnDate.equals(fromDate)) {
                                erder = false;
                                switch (tl.getTicketType().getType()) {
                                    case "Voksenbillet, over 18 år":
                                        {
                                            erder = true;
                                            int quantities = lineList.get(j).getTkAdultQu();
                                            lineList.get(j).setTkAdultQu(quantities + tl.getQuantities());
                                            break;
                                        }
                                    case "Børnebillet, under 18 år":
                                        {
                                            erder = true;
                                            int quantities = lineList.get(j).getTkKidsQu();
                                            lineList.get(j).setTkKidsQu(quantities + tl.getQuantities());
                                            break;
                                        }
                                    case "Gruppebillet, min. 10 personer":
                                        {
                                            erder = true;
                                            int quantities = lineList.get(j).getTkAGroupQu();
                                            lineList.get(j).setTkAGroupQu(quantities + tl.getQuantities());
                                            break;
                                        }
                                    case "Gratister (Museumskort, foregning, ovs)":
                                        {
                                            erder = true;
                                            int quantities = lineList.get(j).getTkFreeQu();
                                            lineList.get(j).setTkFreeQu(quantities + tl.getQuantities());
                                            break;
                                        }
                                }
                            }
                        }
                        if (!erder) {
                            Line l = new Line("", 0, 0);
                            switch (tl.getTicketType().getType()) {
                                case "Voksenbillet, over 18 år":
                                    {
                                        int quantities = tl.getQuantities();
                                        l.setTkAdultQu(quantities);
                                        break;
                                    }
                                case "Børnebillet, under 18 år":
                                    {
                                        int quantities = tl.getQuantities();
                                        l.setTkKidsQu(quantities);
                                        break;
                                    }
                                case "Gruppebillet, min. 10 personer":
                                    {
                                        int quantities = tl.getQuantities();
                                        l.setTkAGroupQu(quantities);
                                        break;
                                    }
                                case "Gratister (Museumskort, foregning, ovs)":
                                    {
                                        int quantities = tl.getQuantities();
                                        l.setTkFreeQu(quantities);
                                        break;
                                    }
                            }
                            l.setTicketDate(dateFormat.getDateFromCal(tlDate));
                            lineList.add(l);
                        }
                    } else {
                        Line l = new Line("", 0, 0);
                        switch (tl.getTicketType().getType()) {
                            case "Voksenbillet, over 18 år":
                                {
                                    int quantities = tl.getQuantities();
                                    l.setTkAdultQu(quantities);
                                    break;
                                }
                            case "Børnebillet, under 18 år":
                                {
                                    int quantities = tl.getQuantities();
                                    l.setTkKidsQu(quantities);
                                    break;
                                }
                            case "Gruppebillet, min. 10 personer":
                                {
                                    int quantities = tl.getQuantities();
                                    l.setTkAGroupQu(quantities);
                                    break;
                                }
                            case "Gratister (Museumskort, foregning, ovs)":
                                {
                                    int quantities = tl.getQuantities();
                                    l.setTkFreeQu(quantities);
                                    break;
                                }
                        }
                        l.setTicketDate(dateFormat.getDateFromCal(tlDate));
                        lineList.add(l);
                    }
                }
            }
        }
        show();
        return lineList;
    }

    public void show() {
        for (Line line : lineList) {
            System.out.println(line.getText() + " " + line.getTicketDate() + " a " + line.getTkAdultQu() + " g " + line.getTkAGroupQu() + " f " + line.getTkFreeQu() + " k " + line.getTkKidsQu());
        }
    }
}
