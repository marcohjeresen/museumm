/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package docTools;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Product;
import utillity.StockLine;

/**
 *
 * @author MarcoPc
 */
public class DocHandler {
private ArrayList<StockLine> StockLineList;
    public DocHandler(ArrayList<StockLine> StockLineList) {
        this.StockLineList = StockLineList;
//        File fil = new File("filnavn");

        String longText = "abcdefghijklmnop...";
        try {
            String filename = "testdoc.txt";
            File file = new File(filename);
            PrintWriter pw = new PrintWriter(file);
            for (StockLine product : StockLineList) {
                
                String proString = product.getProducName() +" "+ product.getProducQuantitis();
                pw.println(proString);
            }
            
            
            pw.close();
        } catch (IOException ex) {
            System.out.println("Det var ikke muligt at skrive til filen: " + ex.getMessage());
        }

    }

}
