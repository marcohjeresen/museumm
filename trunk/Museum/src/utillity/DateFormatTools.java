/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utillity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author markh_000
 */
public class DateFormatTools {

    private static DateFormatTools dateFormat;

    private DateFormatTools() {
    }

    public static DateFormatTools getDateFormat() {
        if (dateFormat == null) {
            dateFormat = new DateFormatTools();
        }
        return dateFormat;
    }

    public Calendar getDateNowCal() {
        Calendar c = Calendar.getInstance();
        return c;
    }

    public String getDateNowString() {
        Calendar c = Calendar.getInstance();
        String year = "" + c.get(Calendar.YEAR);
        String month = "" + (c.get(Calendar.MONTH) + 1);
        String day = "" + c.get(Calendar.DAY_OF_MONTH);
        String hour = "" + c.get(Calendar.HOUR_OF_DAY);
        String minut = "" + c.get(Calendar.MINUTE);
        String second = "" + c.get(Calendar.SECOND);

        String date = "" + year + "-" + month + "-" + day + " " + hour + ":" + minut + ":" + second;
        return date;

    }

    public String getDateFromCal(Calendar calendar) {
        String year = "" + calendar.get(Calendar.YEAR);
        String month = "" + (calendar.get(Calendar.MONTH) + 1);
        String day = "" + calendar.get(Calendar.DAY_OF_MONTH);
        String hour = "" + calendar.get(Calendar.HOUR_OF_DAY);
        String minut = "" + calendar.get(Calendar.MINUTE);
        String second = "" + calendar.get(Calendar.SECOND);

        String date = "" + year + "-" + month + "-" + day + " " + hour + ":" + minut + ":" + second;
        return date;
    }

    public Calendar getShortDate(String date) throws ParseException {

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        
        Calendar c = Calendar.getInstance();
        c.setTime(format1.parse(date));
        
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar dateCal = Calendar.getInstance();
//        Date d = null;
//        try {
//            d = formatter.parse(date);
//            dateCal.setTime(d);
//        } catch (ParseException ex) {
//            System.out.println("Date parse error");
//            System.out.println(ex.getLocalizedMessage());
//        }
        return c;
    }

    public Calendar getDateFromString(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar dateCal = Calendar.getInstance();
        try {
            Date d = formatter.parse(date);
            dateCal.setTime(d);
        } catch (ParseException ex) {
            System.out.println("Date parse error");
            System.out.println(ex.getLocalizedMessage());
        }
        return dateCal;
    }

    public Calendar getNextday(Calendar calendar, int days) {

        calendar.roll(Calendar.DAY_OF_YEAR, days);

        Calendar c = calendar;
        return c;
    }

    public Calendar getStartDateFromString(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar dateCal = Calendar.getInstance();
        try {
            Date d = formatter.parse(date);
            dateCal.setTime(d);
        } catch (ParseException ex) {
            System.out.println("Date parse error");
            System.out.println(ex.getLocalizedMessage());
        }
        dateCal.set(Calendar.HOUR_OF_DAY, 00);
        dateCal.set(Calendar.MINUTE, 00);
        dateCal.set(Calendar.SECOND, 00);
        return dateCal;
    }

    public Calendar getEndDateFromString(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar dateCal = Calendar.getInstance();
        try {
            Date d = formatter.parse(date);
            dateCal.setTime(d);
        } catch (ParseException ex) {
            System.out.println("Date parse error");
            System.out.println(ex.getLocalizedMessage());
        }
        dateCal.set(Calendar.HOUR_OF_DAY, 23);
        dateCal.set(Calendar.MINUTE, 59);
        dateCal.set(Calendar.SECOND, 59);
        return dateCal;
    }
}
