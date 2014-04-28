/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Panel;

import model.handler.*;
import model.controller.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import model.Sale;
import model.Listeners;
import utillity.*;

/**
 *
 * @author markh_000
 */
public class BuyStuff extends javax.swing.JFrame implements ActionListener {

    private Listeners listeners;
    private Sale sale;
    private SaleHandler saleHandler;
    private MoneyHandler moneyHandler;
    private StoreHandler storeHandler;
    private StoreController storeController;
    private PrintHandler printHandler;
    private String modtaget;
    private String dkOrEuro;
    private ArrayList<String> modtag;
    private double penge;
    private Timer timer;
    private boolean discount;

    /**
     * Creates new form BuyStuff
     */
    public BuyStuff(Sale sale, MoneyHandler moneyHandler, Listeners listeners, SaleHandler saleHandler, StoreHandler storeHandler, StoreController storeController, PrintHandler printHandler) {
        this.sale = sale;
        this.moneyHandler = moneyHandler;
        this.storeHandler = storeHandler;
        this.listeners = listeners;
        this.saleHandler = saleHandler;
        this.storeController = storeController;
        this.printHandler = printHandler;
        modtag = new ArrayList<>();
        listeners.addListener(this);

        initComponents();
        jButton_betal.setEnabled(false);
        discount = false;
        modtaget = "";
        setBounds(0, 0, 400, 470);
        jLabel_melding.setText("");
        timer = new Timer(5000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                dispose();
                timer.stop();
            }
        });

    }

    public void Museumscard() {
        if (jCheckBox_rabat.isSelected()) {
            discount = true;
        } else {
            discount = false;
        }
        setBetalingsBeløb();
    }

    public void setBetalingsBeløb() {

        if (jCheckBox_danske.isSelected()) {
            double priceDk = sale.getEndpriceDk(discount) / 100;
            jTextField_beløb.setText("DK: " + priceDk);
            penge = sale.getEndpriceDk(discount) / 100;
        } else if (jCheckBox_euro.isSelected()) {
            double priceEuro = sale.getEndpriceEuro(discount) / 100;
            jTextField_beløb.setText("EURO: " + priceEuro);
            penge = sale.getEndpriceEuro(discount) / 100;
        }

    }

    public void setModtagetBeløb() {
        modtaget = "";
        if (!modtag.isEmpty()) {
            for (String string : modtag) {
                modtaget = modtaget + string;
                jButton_betal.setEnabled(true);
            }
        }

        jTextField_modtaget.setText(modtaget);
    }

    public void endSale() throws SQLException {
        boolean beløbGodkent = false;
        double modtagetTilBetaling;
        double retur = 0;
        int payid = 1;
        try {
            modtagetTilBetaling = Double.parseDouble(modtaget);
            if (penge <= modtagetTilBetaling) {
                beløbGodkent = true;
                if (jCheckBox_danske.isSelected()) {
                    retur = modtagetTilBetaling - penge;
                    jTextField_returBeløb.setText("Retur DK: " + retur);

                } else if (jCheckBox_euro.isSelected()) {
                    retur = modtagetTilBetaling - penge;
                    jTextField_returBeløb.setText("Retur Euro: " + retur);
                }

                storeController.alterProductQuantities(sale.getProductLine(), storeHandler.getProductsList());

                saleHandler.endSale(sale, discount);
                penge = penge;
                int money = (int) (penge * 100);
                moneyHandler.addCashAmount("+", dkOrEuro, money);

                listeners.notifyListeners("End Sale");
                jButton_betal.setEnabled(false);
                jButton_fortryd.setEnabled(false);
                timer.start();
                if (jCheckBox_kvit.isSelected()) {
                    printHandler.kvitteringPrint(sale, discount);
                }
            } else {
                jLabel_melding.setText("Beløb Ikke Nok!!");
            }

        } catch (NumberFormatException ex) {
            jLabel_melding.setText("Tjek indtastede beløb");
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jCheckBox_danske = new javax.swing.JCheckBox();
        jCheckBox_euro = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jTextField_beløb = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField_modtaget = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField_returBeløb = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton_betal = new javax.swing.JButton();
        jButton_fortryd = new javax.swing.JButton();
        jCheckBox_kvit = new javax.swing.JCheckBox();
        jCheckBox_rabat = new javax.swing.JCheckBox();
        jLabel_melding = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Vælg Betalings Type:");

        jCheckBox_danske.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jCheckBox_danske.setText("Danske Kroner");
        jCheckBox_danske.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_danskeActionPerformed(evt);
            }
        });

        jCheckBox_euro.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jCheckBox_euro.setText("Euro");
        jCheckBox_euro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_euroActionPerformed(evt);
            }
        });

        jLabel2.setText("Beløb Til Betaling:");

        jTextField_beløb.setEditable(false);

        jLabel3.setText("Beløb Modtaget:");

        jTextField_modtaget.setEditable(false);

        jLabel4.setText("Penge Retur:");

        jTextField_returBeløb.setEditable(false);

        jButton1.setText("1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("7");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("8");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("9");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("0");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText(".");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton_betal.setText("Betal");
        jButton_betal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_betalActionPerformed(evt);
            }
        });

        jButton_fortryd.setText("Fortryd");
        jButton_fortryd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_fortrydActionPerformed(evt);
            }
        });

        jCheckBox_kvit.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jCheckBox_kvit.setText("Kvitering");

        jCheckBox_rabat.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jCheckBox_rabat.setText("MuseumsKort");
        jCheckBox_rabat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_rabatActionPerformed(evt);
            }
        });

        jLabel_melding.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_melding.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_melding.setText("jLabel5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox_rabat)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel4)
                            .addComponent(jTextField_returBeløb)
                            .addComponent(jCheckBox_danske)
                            .addComponent(jTextField_beløb))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                                .addComponent(jCheckBox_kvit, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jCheckBox_euro, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_modtaget)
                                            .addComponent(jButton_fortryd, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                                            .addComponent(jButton_betal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel_melding, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jCheckBox_rabat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox_euro, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jCheckBox_danske))
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6)
                        .addComponent(jTextField_beløb, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6)
                        .addComponent(jTextField_modtaget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addComponent(jLabel4)
                .addGap(6, 6, 6)
                .addComponent(jTextField_returBeløb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox_kvit))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_melding))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton_fortryd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_betal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox_danskeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_danskeActionPerformed
        if (jCheckBox_danske.isSelected()) {
            jCheckBox_euro.setSelected(false);
            dkOrEuro = "DK";
        }
        setBetalingsBeløb();
    }//GEN-LAST:event_jCheckBox_danskeActionPerformed

    private void jCheckBox_euroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_euroActionPerformed
        if (jCheckBox_euro.isSelected()) {
            jCheckBox_danske.setSelected(false);
            dkOrEuro = "EURO";
        }
        setBetalingsBeløb();
    }//GEN-LAST:event_jCheckBox_euroActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        modtag.add("1");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        modtag.add("2");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        modtag.add("3");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        modtag.add("4");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        modtag.add("5");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        modtag.add("6");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        modtag.add("7");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        modtag.add("8");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        modtag.add("9");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        modtag.add("0");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        modtag.add(".");
        setModtagetBeløb();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton_fortrydActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_fortrydActionPerformed
        modtag.remove(modtag.size() - 1);
        setModtagetBeløb();
    }//GEN-LAST:event_jButton_fortrydActionPerformed

    private void jButton_betalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_betalActionPerformed
        try {
            endSale();
        } catch (SQLException ex) {
            Logger.getLogger(BuyStuff.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_betalActionPerformed

    private void jCheckBox_rabatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_rabatActionPerformed

        if (jCheckBox_rabat.isSelected()) {

        }
        setBetalingsBeløb();
    }//GEN-LAST:event_jCheckBox_rabatActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButton_betal;
    private javax.swing.JButton jButton_fortryd;
    private javax.swing.JCheckBox jCheckBox_danske;
    private javax.swing.JCheckBox jCheckBox_euro;
    private javax.swing.JCheckBox jCheckBox_kvit;
    private javax.swing.JCheckBox jCheckBox_rabat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel_melding;
    private javax.swing.JTextField jTextField_beløb;
    private javax.swing.JTextField jTextField_modtaget;
    private javax.swing.JTextField jTextField_returBeløb;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {

            case "Update kurv":

                break;
            default:
                break;
        }
    }
}
