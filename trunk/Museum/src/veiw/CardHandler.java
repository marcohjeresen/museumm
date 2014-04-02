/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veiw;

import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Anders Bo Rasmussen
 */
public class CardHandler {

    private CardLayout cl;
    private JPanel main;
    private ArrayList<JPanel> pages;
    private JFrame frame;

    public CardHandler(CardLayout cl, JPanel main, JFrame frame) {
        this.frame = frame;
        this.cl = cl;
        this.main = main;
        pages = new ArrayList<>();
        this.cl = (CardLayout) main.getLayout();
    }

    public void addPage(JPanel panel, String ref) {
        main.add(panel, ref);
    }

    public void show(String ref) {
        cl.show(main, ref);
        switch (ref) {
            case "cp":
                frame.setBounds(0, 0, 310, 700);
                break;

            case "hp":
                frame.setBounds(0, 0, 910, 600);
                break;

            case "as":
                frame.setBounds(0, 0, 600, 610);
                break;

            case "cu":
                frame.setBounds(0, 0, 200, 200);
                break;
        }
        main.revalidate();
    }
}
