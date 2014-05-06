/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utillity;

import db.DBConnection;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.EventLine;
import model.ProductLine;
import model.*;
import model.TicketLine;
import model.handler.MoneyHandler;
import model.handler.SaleHandler;
import model.handler.StoreHandler;
import museum2.Museum2;

/**
 *
 * @author MarcoPc
 */
public class PrintHandler implements Printable {

    public static final int LINES_PER_PAGE = 23;
    private ArrayList<Line> lines;
    ArrayList<ProductLine> productLines;
    ArrayList<TicketLine> ticketLines;
    ArrayList<EventLine> eventLines;
    private int xCord;
    private SaleHandler saleHandler;
    private StoreHandler storeHandler;
    private MoneyHandler moneyHandler;

    public PrintHandler(SaleHandler saleHandler, StoreHandler storeHandler, MoneyHandler moneyHandler) {
        this.saleHandler = saleHandler;
        this.storeHandler = storeHandler;
        this.moneyHandler = moneyHandler;
        lines = new ArrayList<>();
        productLines = new ArrayList<>();
        ticketLines = new ArrayList<>();
        eventLines = new ArrayList<>();
        xCord = 10;
    }

    public void kvitteringPrint(Sale sale, boolean discount) {
        lines.removeAll(lines);
        Line startLine = new Line("Næstved Museum", 0, 0);
        Line startLine2 = new Line("En afdeling af Museum Sydøstdanmark", 0, 0);
        Line emty = new Line("", 0, 0);
        lines.add(startLine);
        lines.add(startLine2);
        lines.add(emty);
        int priceDk = 0;
        int priceEuro = 0;
        xCord = 27;

        if (!sale.getProductLine().isEmpty()) {
            Line produ = new Line("Produkter: ", 0, 0);
            lines.add(produ);
            lines.add(emty);
            for (ProductLine productLine : sale.getProductLine()) {
                priceDk = productLine.getProduct().getPriceDk() * productLine.getQuantities();
                priceEuro = productLine.getProduct().getPriceEuro() * productLine.getQuantities();

                Line product = new Line(productLine.getProduct().getName() + "", 0, 0);
                Line productPrice = new Line("Antal: " + productLine.getQuantities(), priceDk, priceEuro);
                lines.add(product);
                lines.add(productPrice);
            }
            lines.add(emty);
        }
        if (!sale.getEventLine().isEmpty()) {
            Line event = new Line("Event: ", 0, 0);
            lines.add(event);
            lines.add(emty);
            for (EventLine eventLine : sale.getEventLine()) {
                priceDk = eventLine.getEventlinePriceDk();
                priceEuro = eventLine.getEventlineEuro();

                Line eventLineText = new Line(eventLine.getEventtype().getType() + "", 0, 0);
                Line eventLinePlace = new Line(eventLine.getPlace() + "", 0, 0);
                Line eventLinePrice = new Line("Antal: " + eventLine.getQuantities(), priceDk, priceEuro);
                lines.add(eventLineText);
                lines.add(eventLinePlace);
                lines.add(eventLinePrice);
            }
            lines.add(emty);
        }
        if (!sale.getTicketLine().isEmpty()) {
            Line ticket = new Line("Ticket: ", 0, 0);
            lines.add(ticket);
            lines.add(emty);
            for (TicketLine ticketLine : sale.getTicketLine()) {
                priceDk = ticketLine.getTicketType().getPriceDk() * ticketLine.getQuantities();
                priceEuro = ticketLine.getTicketType().getPriceEuro() * ticketLine.getQuantities();

                Line ticketLineText = new Line(ticketLine.getTicketType().getType(), 0, 0);
                Line ticketPrice = new Line("Antal: " + ticketLine.getQuantities(), priceDk, priceEuro);
                lines.add(ticketLineText);
                lines.add(ticketPrice);
            }
            lines.add(emty);
        }
        Line totelPrice = new Line("Total Pris: ", sale.getEndpriceDk(discount), sale.getEndpriceEuro(discount));
        lines.add(totelPrice);
        Line employ = new Line("Du Bliv Betjent Af:", 0, 0);
        Line employ2 = new Line(sale.getEmployee().getName(), 0, 0);
        Line end = new Line("Tak For Besøget Og På Gensyn!", 0, 0);
        lines.add(employ);
        lines.add(employ2);
        lines.add(end);

        doPrint("Kvit");
    }

    public void cashReport() throws ParseException {
        lines.removeAll(lines);
        productLines.removeAll(productLines);
        ticketLines.removeAll(ticketLines);
        eventLines.removeAll(eventLines);
        xCord = 45;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String text = "";
        Calendar cal = Calendar.getInstance();
        Line emty = new Line("", 0, -1);
        int year = cal.get(Calendar.YEAR);
        int monunh = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        Calendar calfirst = Calendar.getInstance();
        calfirst.set(year, monunh, day, 00, 00, 00);
        Calendar calSecond = Calendar.getInstance();
        calSecond.set(year, monunh, day, 23, 59, 59);

        Calendar date2 = Calendar.getInstance();
        boolean erder = false;
        for (Sale sale : saleHandler.getSalesList()) {
            if (sale != null) {

                try {
                    date = formatter.parse(sale.getDate());
                    date2 = Calendar.getInstance();
                    date2.setTime(date);
                } catch (ParseException ex) {
                    System.out.println("Date parse error");
                    System.out.println(ex.getLocalizedMessage());
                }
                if (date2.getTime().after(calfirst.getTime())) {
                    if (date2.getTime().before(calSecond.getTime())) {
                        for (ProductLine productLine : sale.getProductLine()) {
                            erder = false;
                            if (!productLines.isEmpty()) {
                                for (int i = 0; i < productLines.size(); i++) {
                                    if (productLines.get(i).getProduct().equals(productLine.getProduct())) {
                                        erder = true;
                                        int antal = productLines.get(i).getQuantities();
                                        productLines.get(i).setQuantities(antal + productLine.getQuantities());
                                    }
                                }
                                if (!erder) {
                                    ProductLine p = new ProductLine(1, null, productLine.getProduct(), productLine.getQuantities());
                                    productLines.add(p);
                                }
                            } else {
                                ProductLine p = new ProductLine(1, null, productLine.getProduct(), productLine.getQuantities());
                                productLines.add(p);
                            }
                        }
                        for (TicketLine ticketLine : sale.getTicketLine()) {
                            erder = false;
                            if (!ticketLines.isEmpty()) {
                                for (int i = 0; i < ticketLines.size(); i++) {
                                    if (ticketLines.get(i).getTicketType().equals(ticketLine.getTicketType())) {
                                        erder = true;
                                        int antal = ticketLines.get(i).getQuantities();
                                        ticketLines.get(i).setQuantities(antal + ticketLine.getQuantities());
                                    }
                                }
                                if (!erder) {
                                    TicketLine t = new TicketLine(1, null, ticketLine.getQuantities(),ticketLine.getTicketType());
                                    ticketLines.add(t);
                                }
                            } else {
                                TicketLine t = new TicketLine(1, null, ticketLine.getQuantities(),ticketLine.getTicketType());
                                ticketLines.add(t);
                            }
                        }
                        for (EventLine eventLine : sale.getEventLine()) {
                            erder = false;
                            if (!eventLines.isEmpty()) {
                                for (int i = 0; i < eventLines.size(); i++) {
                                    if (eventLines.get(i).getEventtype().equals(eventLine.getEventtype())) {
                                        erder = true;
                                        int antal = eventLines.get(i).getQuantities();
                                        eventLines.get(i).setQuantities(antal + 1);
                                    }
                                }
                                if (!erder) {
                                    EventLine e = new EventLine(1, eventLine.getEventtype(), null, 1, eventLine.getDate(), eventLine.getCustomer(), eventLine.getPlace());
                                    eventLines.add(e);
                                }
                            } else {
                                EventLine e = new EventLine(1, eventLine.getEventtype(), null, 1, eventLine.getDate(), eventLine.getCustomer(), eventLine.getPlace());
                                eventLines.add(e);
                            }
                        }
                    }
                }
            }
        }
        Line pro = new Line("Produkter: ", 0, -1);
        lines.add(pro);
        int totalProd = 0;
        for (ProductLine productLine : productLines) {
            String title = "" + productLine.getProduct().getProductNumber() + "  " + productLine.getProduct().getName();
            Line l = new Line(title, productLine.getQuantities(), -1);
            lines.add(l);
            totalProd = totalProd + productLine.getQuantities();
        }
        Line tic = new Line("Billetter: ", 0, -1);
        lines.add(tic);
        int totalTic = 0;
        for (TicketLine ticketLine : ticketLines) {
            String title = "" + ticketLine.getTicketType().getId() + " " + ticketLine.getTicketType().getType();
            Line l = new Line(title, ticketLine.getQuantities(), -1);
            lines.add(l);
            totalTic = totalTic + ticketLine.getQuantities();
        }
        Line evt = new Line("Event: ", 0, -1);
        lines.add(evt);
        int totalEvt = 0;
        for (EventLine eventLine : eventLines) {
            String title = "" + eventLine.getEventtype().getId() + " " + eventLine.getEventtype().getType();
            Line l = new Line(title, eventLine.getQuantities(), -1);
            lines.add(l);
            totalEvt = totalEvt + eventLine.getQuantities();
        }
        Line t = new Line("-------------------------------------------------------", 0, -1);
        Line tot = new Line("Alt I Alt Salg: ", 0, -1);
        Line totalp = new Line("Product:    Antal I Alt: " + totalProd + "    Antal Forskelige: " + productLines.size(), 0, -1);
        Line totalt = new Line("Billetter:    Antal I Alt: " + totalTic + "    Antal Forskelige: " + ticketLines.size(), 0, -1);
        Line totale = new Line("Event:    Antal I Alt: " + totalEvt + "    Antal Forskelige: " + eventLines.size(), 0, -1);

        lines.add(t);
        lines.add(tot);
        lines.add(totalp);
        lines.add(totalt);
        lines.add(totale);
        lines.add(t);
        Line cash = new Line("Kasse Opgørelse:", 0, -1);
        lines.add(cash);
        DBConnection db = new DBConnection();
        for (CashRegister cashRegister : moneyHandler.getCashRegistersList()) {
            if (cashRegister != null) {
                date = formatter.parse(cashRegister.getDate());
                date2 = Calendar.getInstance();
                date2.setTime(date);
                if (date2.getTime().after(calfirst.getTime())) {
                    if (date2.getTime().before(calSecond.getTime())) {
                        try {
                            ResultSet rs = db.getResult("SELECT * FROM cashregistercontent where starting_id = " + cashRegister.getId() + ";");
                            while (rs.next()) {
                                for (Employee employee : storeHandler.getEmployeesList()) {
                                    if (employee.getCpr() == rs.getInt("starting_employee_cpr")) {
                                        Line ll = new Line("Kasse Start:   Dato: " + cashRegister.getDate(), 0, -1);
                                        double dk = rs.getInt("starting_amount_dk") / 100;
                                        double euro = rs.getInt("starting_amount_euro") / 100;
                                        Line l = new Line("Medarbejder: " + cashRegister.getEmployee().getName() + " DK I Kassen: " + dk + " EURO I Kassen: " + euro, 0, -1);
                                        lines.add(ll);
                                        lines.add(l);
                                    }
                                }

                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Museum2.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        try {
                            ResultSet rs = db.getResult("SELECT * FROM differance where differance_id = " + cashRegister.getId() + ";");
                            while (rs.next()) {
                                for (Employee employee : storeHandler.getEmployeesList()) {
                                    if (employee.getCpr() == rs.getInt("differance_employeecpr")) {
                                        DifferanceRegistre d = new DifferanceRegistre(rs.getInt("differance_id"), employee, rs.getInt("differance_currentcashdk"), rs.getInt("differance_currentcasheuro"),
                                                rs.getInt("differance_expecteddk"), rs.getInt("differance_expectedeuro"), rs.getInt("differance_differancedk"), rs.getInt("differance_differanceeuro"), rs.getString("differance_date"));
                                        Line lll = new Line("Kasse Slut:   Dato: " + d.getDate(), 0, -1);
                                        double fDk = d.getExpectedDk() / 100;
                                        double fEuro = d.getExpectedEuro() / 100;
                                        double SDk = d.getCurrentCashDk() / 100;
                                        double sEuro = d.getCurrentCahsEuro() / 100;
                                        Line llll = new Line("" + d.getEmployee().getName() + "  ForvDk: " + fDk + " ForvEuro: " + fEuro
                                                + " SlutDk: " + SDk + " SlutEuro: " + sEuro, 0, -1);
                                        double DDk = d.getDifferanceDk() / 100;
                                        double DEuro = d.getDifferanceEuro() / 100;
                                        Line dif = new Line("Difference Type: " + " DifDk: " + DDk + " DifEuro: " + DEuro, 0, -1);
                                        lines.add(lll);
                                        lines.add(llll);
                                        lines.add(dif);
                                        lines.add(emty);
                                    }
                                }

                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Museum2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        lines.add(t);
                    }
                }
            }

        }

        doPrint("Kvit");

    }

    public void stockReport(ArrayList<StockLine> stockList) {
        for (StockLine stockLine : stockList) {
            Line li = new Line("", 1, -2);
            li.setProductNumber(stockLine.getProducNumber());
            li.setProductTitle(stockLine.getProducName());
            li.setProductSupplier(stockLine.getProducSuppl());
            li.setProductBuyPrice(stockLine.getProducBuyPrice());
            li.setProductQuantities(stockLine.getProducQuantitis());
            lines.add(li);
        }
        doPrint("Kvit");
    }

    public void drawLines(Graphics g, int page) {
        g.setColor(new Color(150, 190, 255));
        g.fillRect(5, 10, 10 * xCord, 75);
        g.setColor(new Color(50, 90, 155));
        g.drawRect(5, 10, 10 * xCord, 75);

        g.setColor(Color.BLACK);
        Calendar cal = Calendar.getInstance();
        g.drawString(cal.getTime().toString(), 5, 80);

        int lineCount = 0;
        for (int i = page * LINES_PER_PAGE; i < (page + 1) * LINES_PER_PAGE; i++) {
            if (i < lines.size()) {
                int yCoord = 120 + 20 * (i - page * LINES_PER_PAGE);

                if (lines.get(i).getPriceDk() != 0) {
                    if (lines.get(i).getPriceEuro() == -1) {
                        g.drawString(lines.get(i).getText(), 10, yCoord);
                        int priceDk = (lines.get(i).getPriceDk());
                        g.drawString(priceDk + "", 9 * xCord, yCoord);
                    } else if (lines.get(i).getPriceEuro() == -2) {
                        g.drawString(lines.get(i).getProductNumber(), 10, yCoord);
                        g.drawString(lines.get(i).getProductTitle(), 70, yCoord);
                        g.drawString(lines.get(i).getProductSupplier(), 260, yCoord);
                        if (lines.get(i).getProductBuyPrice() != "KøbsPris") {
                            int priceeee = Integer.parseInt(lines.get(i).getProductBuyPrice());
                            double price = priceeee / 100;
                            g.drawString(price+"", 370, yCoord);
                        } else {
                            g.drawString(lines.get(i).getProductBuyPrice(), 370, yCoord);
                        }
                        g.drawString(lines.get(i).getProductQuantities(), 450, yCoord);
                    }
                } else {
                    g.drawString(lines.get(i).getText(), 10, yCoord);
                    double priceDk = (lines.get(i).getPriceDk() / 100);
                    g.drawString(priceDk + " kr", 9 * xCord, yCoord);
                }

                lineCount++;
            }
        }
//        if ((page + 1) * LINES_PER_PAGE >= lines.size()) {
//            int totalDk = 0;
//            int totalEuro = 0;
//            double totalDkend = 0;
//            double totalEuroEnd = 0;
//            for (int i = 0; i < lines.size(); i++) {
//                totalDk = totalDk + lines.get(i).getPriceDk();
//                totalEuro = totalEuro + lines.get(i).getPriceEuro();
//            }
//            totalDkend = (totalDk / 100);
//            totalEuroEnd = (totalEuro / 100);
//            g.drawLine(10, lineCount * 20 + 115, 275, lineCount * 20 + 115);
//            g.drawString("Total", 10, 130 + lineCount * 20);
//            g.drawString(totalDkend + " kr", 210, 130 + lineCount * 20);
//            g.drawLine(10, lineCount * 20 + 135, 275, lineCount * 20 + 135);
//        }
        g.drawString("Side " + (page + 1), 10, 175 + lineCount * 20);
    }

    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        int printResult;
        if (page > lines.size() / LINES_PER_PAGE) {
            printResult = NO_SUCH_PAGE;
        } else {
            // Find øverste venstre hjørne i det printbare område
            // Forskyd g2d, så (0,0) svarer til øverste venstre hjørne
            Graphics2D g2d = (Graphics2D) g;
            double x0 = pf.getImageableX();
            double y0 = pf.getImageableY();
            g2d.translate(x0, y0);
            drawLines(g2d, page);
            printResult = PAGE_EXISTS;
        }
        return printResult;

    }

    public void doPrint(String type) {
        PrinterJob job = PrinterJob.getPrinterJob();
        Printable doc = this;
        job.setPrintable(doc);
        boolean accept = job.printDialog();
        if (accept) {
            try {
                switch (type) {
                    case "Kvit":
                        job.print();

                        break;
                    case "Stock":

                        break;
                }

            } catch (PrinterException ex) {
                System.out.println("printer problemmer");
            }
        }
    }

}
