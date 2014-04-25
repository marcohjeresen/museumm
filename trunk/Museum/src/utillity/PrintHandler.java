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
import java.util.ArrayList;
import model.EventLine;
import model.ProductLine;
import model.Sale;
import model.TicketLine;

/**
 *
 * @author MarcoPc
 */
public class PrintHandler implements Printable {

    public static final int LINES_PER_PAGE = 10;
    private ArrayList<Line> lines;

    public PrintHandler() {
        lines = new ArrayList<Line>();
    }

    public void kvitteringPrint(Sale sale, boolean discount) {
        lines.removeAll(lines);
        Line startLine = new Line("Næstved Museum", 0,0);
        Line startLine2 = new Line("En afdeling af Museum Sydøstdanmark", 0,0);
        Line emty = new Line("", 0,0);
        lines.add(startLine);
        lines.add(startLine2);
        lines.add(emty);
        int priceDk = 0;
        int priceEuro = 0;
        
        if (!sale.getProductLine().isEmpty()) {
            Line produ = new Line("Produkter: ", 0,0);
            lines.add(produ);
            lines.add(emty);
            for (ProductLine productLine : sale.getProductLine()) {
                priceDk = productLine.getProduct().getPriceDk() * productLine.getQuantities();
                priceEuro = productLine.getProduct().getPriceEuro() * productLine.getQuantities();
                
                Line product = new Line(productLine.getProduct().getName() + "", 0,0);
                Line productPrice = new Line("Antal: " + productLine.getQuantities(),priceDk ,priceEuro);
                lines.add(product);
                lines.add(productPrice);
            }
            lines.add(emty);
        }
        if (!sale.getEventLine().isEmpty()) {
            Line event = new Line("Event: ", 0,0);
            lines.add(event);
            lines.add(emty);
            for (EventLine eventLine : sale.getEventLine()) {
                priceDk = eventLine.getEventlinePriceDk();
                priceEuro = eventLine.getEventlineEuro();
                
                Line eventLineText = new Line(eventLine.getEventtype().getType()+"", 0,0);
                Line eventLinePlace = new Line(eventLine.getPlace()+"", 0,0);
                Line eventLinePrice = new Line("Antal: " + eventLine.getQuantities(), priceDk, priceEuro);
                lines.add(eventLineText);
                lines.add(eventLinePlace);
                lines.add(eventLinePrice);
            }
            lines.add(emty);
        }
        if (!sale.getTicketLine().isEmpty()) {
            Line ticket = new Line("Ticket: ", 0,0);
            lines.add(ticket);
            lines.add(emty);
            for (TicketLine ticketLine : sale.getTicketLine()) {
                priceDk = ticketLine.getTicketType().getPriceDk() * ticketLine.getQuantities();
                priceEuro = ticketLine.getTicketType().getPriceEuro() * ticketLine.getQuantities();
                
                Line ticketLineText = new Line(ticketLine.getTicketType().getType(), 0, 0);
                Line ticketPrice = new Line("Antal: "+ ticketLine.getQuantities(), priceDk, priceEuro);
                lines.add(ticketLineText);
                lines.add(ticketPrice);
            }
            lines.add(emty);
        }
        Line totelPrice = new Line("Total Pris: ", sale.getEndpriceDk(discount),sale.getEndpriceEuro(discount));
        lines.add(totelPrice);
        Line employ = new Line("Du Bliv Betjent Af:", 0, 0);
        Line employ2 = new Line(sale.getEmployee().getName(), 0, 0);
        Line end = new Line("Tak For Besøget Og På Gensyn!", 0, 0);
        lines.add(employ);
        lines.add(employ2);
        lines.add(end);
                
        
        doPrint();
    }

    public void drawLines(Graphics g, int page) {
        g.setColor(new Color(150, 190, 255));
        g.fillRect(5, 10, 275, 75);
        g.setColor(new Color(50, 90, 155));
        g.drawRect(5, 10, 275, 75);
        g.setColor(Color.BLACK);

        int lineCount = 0;
        for (int i = page * LINES_PER_PAGE; i < (page + 1) * LINES_PER_PAGE; i++) {
            if (i < lines.size()) {
                int yCoord = 120 + 20 * (i - page * LINES_PER_PAGE);
                g.drawString(lines.get(i).getText(), 10, yCoord);
                g.drawString(lines.get(i).getPriceDk()+ " kr", 210, yCoord);
                lineCount++;
            }
        }
        if ((page + 1) * LINES_PER_PAGE >= lines.size()) {
            int totalDk = 0;
            int totalEuro = 0;
            for (int i = 0; i < lines.size(); i++) {
                totalDk = totalDk + lines.get(i).getPriceDk();
                totalEuro = totalEuro + lines.get(i).getPriceEuro();
            }
            g.drawLine(10, lineCount * 20 + 115, 275, lineCount * 20 + 115);
            g.drawString("Total", 10, 130 + lineCount * 20);
            g.drawString(totalDk + " kr", 210, 130 + lineCount * 20);
            g.drawLine(10, lineCount * 20 + 135, 275, lineCount * 20 + 135);
        }
        g.drawString("Side " + (page + 1), 10, 300);
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
