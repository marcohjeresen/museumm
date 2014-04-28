/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utillity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import model.EventLine;
import model.ProductLine;
import model.Sale;
import model.TicketLine;
import model.handler.SaleHandler;

/**
 *
 * @author MarcoPc
 */
public class PrintHandler implements Printable {

    public static final int LINES_PER_PAGE = 23;
    private ArrayList<Line> lines;
    ArrayList<ProductLine> productLines;
    private int xCord;
    private SaleHandler saleHandler;

    public PrintHandler(SaleHandler saleHandler) {
        this.saleHandler = saleHandler;
        lines = new ArrayList<>();
        productLines = new ArrayList<>();
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

        doPrint();
    }

    public void cashReport() {
        lines.removeAll(lines);
        productLines.removeAll(productLines);
        xCord = 45;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String text = "";
        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        int monunh = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        Calendar calfirst = Calendar.getInstance();
        calfirst.set(year, monunh, day, 00, 00, 00);
        Calendar calSecond = Calendar.getInstance();
        calSecond.set(year, monunh, day, 23, 59, 59);

        Calendar date2 = Calendar.getInstance();

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
                            boolean erder = false;
                            System.out.println("heeej");
                            if (!productLines.isEmpty()) {
                                for (int i = 0; i < productLines.size(); i++) {
                                    if (productLines.get(i).getProduct().equals(productLine.getProduct())) {
                                        erder = true;

                                        int antal = productLines.get(i).getQuantities();
                                        productLines.get(i).setQuantities(antal + productLine.getQuantities());
                                    } else {

                                    }
                                }
                                if (!erder) {
                                    System.out.println("pis");
                                    ProductLine p = new ProductLine(1, null, productLine.getProduct(), productLine.getQuantities());
                                    productLines.add(p);
                                }

                            } else {
                                System.out.println("tis");
                                ProductLine p = new ProductLine(1, null, productLine.getProduct(), productLine.getQuantities());
                                productLines.add(p);
                            }
                        }
                    }
                }
            }
        }
        
        for (ProductLine productLine : productLines) {
            System.out.println("fuck");
            Line l = new Line("" + productLine.getProduct().getProductNumber() + "  " + productLine.getProduct().getName() + "", productLine.getQuantities(), -1);
            lines.add(l);
        }

        doPrint();

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
                g.drawString(lines.get(i).getText(), 10, yCoord);
                if (lines.get(i).getPriceDk() != 0) {
                    if (lines.get(i).getPriceEuro() ==-1) {
                        int priceDk = (lines.get(i).getPriceDk());
                        g.drawString(priceDk + "", 9 * xCord, yCoord);
                    }else{
                        
                    double priceDk = (lines.get(i).getPriceDk() / 100);
                    g.drawString(priceDk + " kr", 9 * xCord, yCoord);
                }}

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

    public void doPrint() {
        PrinterJob job = PrinterJob.getPrinterJob();
        Printable doc = this;
        job.setPrintable(doc);
        boolean accept = job.printDialog();
        if (accept) {
            try {
                job.print();
            } catch (PrinterException ex) {
                // Noget gik galt – håndtér det!
            }
        }
    }

}
