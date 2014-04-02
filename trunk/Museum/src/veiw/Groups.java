/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veiw;

import java.awt.Dimension;
import model.ProductGroup;
import model.handler.*;

/**
 *
 * @author markh_000
 */
public class Groups extends javax.swing.JPanel {

    private ProductGroup productGroup;

    /**
     * Creates new form Groups
     */
    public Groups(ProductGroup groups) {
        int x = 0;
        if (groups.getGroupType().length() < 6) {
            x = groups.getGroupType().length() * 16;
        } else if (groups.getGroupType().length() > 5 && groups.getGroupType().length() < 8) {
            x = groups.getGroupType().length() * 12 + 6;
        } else if (groups.getGroupType().length() > 7 && groups.getGroupType().length() < 12){
            x = groups.getGroupType().length() * 11;
        }else if (groups.getGroupType().length() > 12 && groups.getGroupType().length() < 20){
             x = groups.getGroupType().length() * 10;
        }else{
            x = groups.getGroupType().length() * 8;
        }

        

        System.out.println(x + " " + groups.getGroupType().length());
        setSize(new Dimension(x, 40));
        this.productGroup = groups;
        initComponents();
        jButton1.setText(groups.getGroupType());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
