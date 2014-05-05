/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utillity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import utillity.view.StatestikView;

/**
 *
 * @author MarcoPc
 */
public class StatistikPrintHandler implements Printable {

    private StatestikView statistikView;

    public StatistikPrintHandler(StatestikView statistikView) {
        this.statistikView = statistikView;
    }

//    public void drawLines(Graphics g, int page) {
//        g.setColor(new Color(150, 190, 255));
//        g.fillRect(5, 10, 275, 75);
//        g.setColor(new Color(50, 90, 155));
//        g.drawRect(5, 10, 275, 75);
//        g.setColor(Color.BLACK);
//
//        int lineCount = 0;
//        for (int i = page * LINES_PER_PAGE; i < (page + 1) * LINES_PER_PAGE; i++) {
//            if (i < lines.size()) {
//                int yCoord = 120 + 20 * (i - page * LINES_PER_PAGE);
//                g.drawString(lines.get(i).getText(), 10, yCoord);
//                g.drawString(lines.get(i).getPriceDk()+ " kr", 210, yCoord);
//                lineCount++;
//            }
//        }
//        if ((page + 1) * LINES_PER_PAGE >= lines.size()) {
//            int total = 0;
//            for (int i = 0; i < lines.size(); i++) {
//                total = total + lines.get(i).getPrice();
//            }
//            g.drawLine(10, lineCount * 20 + 115, 275, lineCount * 20 + 115);
//            g.drawString("Total", 10, 130 + lineCount * 20);
//            g.drawString(total + " kr", 210, 130 + lineCount * 20);
//            g.drawLine(10, lineCount * 20 + 135, 275, lineCount * 20 + 135);
//        }
//        g.drawString("Side " + (page + 1), 10, 300);
//    }
    public void doPrint() {
        System.out.println("want to print");
        PrinterJob job = PrinterJob.getPrinterJob();
        Printable doc = this;
        job.setPrintable(doc);
        boolean accept = job.printDialog();
        if (accept) {
            try {
                System.out.println("jes");
                job.print();
            } catch (PrinterException ex) {
                System.out.println("exeption: StatestikPringHandler.doPrint() ");
                System.out.println(ex.getLocalizedMessage());
            }
        } else {
            System.out.println("diden priunt");
        }

    }

    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        int printResult;
        System.out.println("printing page: " + page);
        // Find øverste venstre hjørne i det printbare område
        // Forskyd g2d, så (0,0) svarer til øverste venstre hjørne
        if (page == 0) {
            Graphics2D g2d = (Graphics2D) g;
            double x0 = pf.getImageableX();
            double y0 = pf.getImageableY();
            g2d.translate(x0, y0);
            statistikView.drawLines(g2d, page);
            printResult = PAGE_EXISTS;
            System.out.println("okay");
        } else {
            printResult = NO_SUCH_PAGE;
            System.out.println("n o way");
        }

        return printResult;

    }

}
