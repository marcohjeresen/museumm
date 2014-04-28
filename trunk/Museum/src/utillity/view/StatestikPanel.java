/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utillity.view;
import model.handler.SaleHandler;
import model.handler.StoreHandler;
import model.*;
import utillity.StatistikHandler;
import java.util.ArrayList;

/**
 *
 * @author markh_000
 */
public class StatestikPanel extends javax.swing.JPanel {
private StoreHandler storeHandler;
private SaleHandler saleHandler;
private ArrayList<StateStikGroup> StateList;
private StatistikHandler statistikHandler;

    /**
     * Creates new form StatestikPanel
     */
    public StatestikPanel(StoreHandler storeHandler, SaleHandler saleHandler, StatistikHandler statistikHandler) {
        this.storeHandler =storeHandler;
        this.saleHandler = saleHandler;
        this.statistikHandler  = statistikHandler;
        StateList = new ArrayList<>();
        setSize(805, 660);
        initComponents();
    }
    public void setGroup(String type){
        StateList.removeAll(StateList);
        int x = 5;
        int y = 15;
        String TypeName = "";
        jPanel_grups.removeAll();
        jPanel_grups.revalidate();
        jPanel_grups.repaint();
        switch (type) {
            case "Product":
                TypeName = "Mest Solgte";
                StateStikGroup p1 = new StateStikGroup(TypeName, storeHandler, type, statistikHandler);
                p1.setLocation(x, y);
                jPanel_grups.add(p1);
                p1.setVisible(true);
                y = 5 + p1.getHeight();
                
                TypeName = "Størst Indtjening";
                StateStikGroup p2 = new StateStikGroup(TypeName, storeHandler, type, statistikHandler);
                p2.setLocation(x , y);
                jPanel_grups.add(p2);
                p2.setVisible(true);
                
                x = 5 + p1.getWidth();
                y = 15;
                TypeName = "Mindst Antal";
                StateStikGroup p3 = new StateStikGroup(TypeName, storeHandler, type, statistikHandler);
                p3.setLocation(x , y);
                jPanel_grups.add(p3);
                p3.setVisible(true);
                jPanel_grups.revalidate();
                break;
            case "Ticket":
                 TypeName = "Mest Solgte";
                StateStikGroup t1 = new StateStikGroup(TypeName, storeHandler, type, statistikHandler);
                t1.setLocation(x, y);
                jPanel_grups.add(t1);
                t1.setVisible(true);
                y = 5 + t1.getHeight();
                TypeName = "Størst Indtjening";
                StateStikGroup t2 = new StateStikGroup(TypeName, storeHandler, type, statistikHandler);
                t2.setLocation(x , y);
                jPanel_grups.add(t2);
                t2.setVisible(true);
                x = 5 + t1.getWidth();
                y = 15;
                TypeName = "Mindst Antal";
                StateStikGroup t3 = new StateStikGroup(TypeName, storeHandler, type, statistikHandler);
                t3.setLocation(x , y);
                jPanel_grups.add(t3);
                t3.setVisible(true);
                jPanel_grups.revalidate();
                break;
            case "Event":
                 TypeName = "Mest Solgte";
                StateStikGroup e1 = new StateStikGroup(TypeName, storeHandler, type, statistikHandler);
                e1.setLocation(x, y);
                jPanel_grups.add(e1);
                e1.setVisible(true);
                y = 5 + e1.getHeight();
                TypeName = "Størst Indtjening";
                StateStikGroup e2 = new StateStikGroup(TypeName, storeHandler, type, statistikHandler);
                e2.setLocation(x , y);
                jPanel_grups.add(e2);
                e2.setVisible(true);
                x = 5 + e1.getWidth();
                y = 15;
                TypeName = "Mindst Antal";
                StateStikGroup e3 = new StateStikGroup(TypeName, storeHandler, type, statistikHandler);
                e3.setLocation(x , y);
                jPanel_grups.add(e3);
                e3.setVisible(true);
                jPanel_grups.revalidate();
                break;
            case "Viseters":
                  TypeName = "Gratister";
                StateStikGroup v1 = new StateStikGroup(TypeName, storeHandler, type, statistikHandler);
                v1.setLocation(x, y);
                jPanel_grups.add(v1);
                v1.setVisible(true);
                
                y = 5+ v1.getHeight();
                TypeName = "Voksne";
                StateStikGroup v2 = new StateStikGroup(TypeName, storeHandler, type, statistikHandler);
                v2.setLocation(x , y);
                jPanel_grups.add(v2);
                v2.setVisible(true);
                
                y = y + v1.getHeight() -10;
                x = 5;
                TypeName = "Børn";
                StateStikGroup v3 = new StateStikGroup(TypeName, storeHandler, type, statistikHandler);
                v3.setLocation(x , y);
                jPanel_grups.add(v3);
                v3.setVisible(true);
                
                x = 5 + v1.getWidth();
                y = 15;
                TypeName = "Grupper";
                StateStikGroup v4 = new StateStikGroup(TypeName, storeHandler, type, statistikHandler);
                v4.setLocation(x , y);
                jPanel_grups.add(v4);
                v4.setVisible(true);
                
                
                jPanel_grups.revalidate();
                break;
            case "Money":
                 StateStikGroup state = new StateStikGroup(type, storeHandler, type, statistikHandler);
                break;
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel_group = new javax.swing.JPanel();
        jButton_ticket = new javax.swing.JButton();
        jButton_evetn = new javax.swing.JButton();
        jButton_product = new javax.swing.JButton();
        jButton_viseters = new javax.swing.JButton();
        jButton_monye = new javax.swing.JButton();
        jPanel_grups = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Statestik"));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
        );

        jPanel_group.setBorder(javax.swing.BorderFactory.createTitledBorder("Statestik Type"));

        jButton_ticket.setText("Billetter");
        jButton_ticket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ticketActionPerformed(evt);
            }
        });

        jButton_evetn.setText("Eventter");
        jButton_evetn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_evetnActionPerformed(evt);
            }
        });

        jButton_product.setText("Produkter");
        jButton_product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_productActionPerformed(evt);
            }
        });

        jButton_viseters.setText("Besøgende");
        jButton_viseters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_visetersActionPerformed(evt);
            }
        });

        jButton_monye.setText("Omsætning");
        jButton_monye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_monyeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_groupLayout = new javax.swing.GroupLayout(jPanel_group);
        jPanel_group.setLayout(jPanel_groupLayout);
        jPanel_groupLayout.setHorizontalGroup(
            jPanel_groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_groupLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jButton_ticket, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton_evetn, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton_product, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton_viseters, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton_monye, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_groupLayout.setVerticalGroup(
            jPanel_groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_groupLayout.createSequentialGroup()
                .addGroup(jPanel_groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_ticket, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_evetn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_viseters, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_product, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_monye, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jPanel_grups.setBorder(javax.swing.BorderFactory.createTitledBorder("Grupper"));

        javax.swing.GroupLayout jPanel_grupsLayout = new javax.swing.GroupLayout(jPanel_grups);
        jPanel_grups.setLayout(jPanel_grupsLayout);
        jPanel_grupsLayout.setHorizontalGroup(
            jPanel_grupsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
        );
        jPanel_grupsLayout.setVerticalGroup(
            jPanel_grupsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Finish"));

        jCheckBox1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jCheckBox1.setText("Print");

        jCheckBox2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jCheckBox2.setText("Doc");

        jButton1.setText("Afslut");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ny Statistik");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(7, 7, 7)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_group, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_grups, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_group, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel_grups, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_ticketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ticketActionPerformed
        setGroup("Ticket");
    }//GEN-LAST:event_jButton_ticketActionPerformed

    private void jButton_evetnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_evetnActionPerformed
        setGroup("Event");
    }//GEN-LAST:event_jButton_evetnActionPerformed

    private void jButton_productActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_productActionPerformed
        setGroup("Product");
    }//GEN-LAST:event_jButton_productActionPerformed

    private void jButton_visetersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_visetersActionPerformed
        setGroup("Viseters");
    }//GEN-LAST:event_jButton_visetersActionPerformed

    private void jButton_monyeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_monyeActionPerformed
        setGroup("Money");
    }//GEN-LAST:event_jButton_monyeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       jTextArea1.setText(statistikHandler.getString());
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton_evetn;
    private javax.swing.JButton jButton_monye;
    private javax.swing.JButton jButton_product;
    private javax.swing.JButton jButton_ticket;
    private javax.swing.JButton jButton_viseters;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel_group;
    private javax.swing.JPanel jPanel_grups;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
