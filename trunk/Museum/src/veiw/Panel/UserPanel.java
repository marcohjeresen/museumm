/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veiw.Panel;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.*;
import javax.swing.PopupFactory;
import model.handler.EmployeeHandler;
import model.handler.KurvHandler;

/**
 *
 * @author MarcoPc
 */
public class UserPanel extends javax.swing.JPanel {
    
    private EmployeeHandler employeeHandler;
    private KurvHandler kurvHandler;
    private JPopupMenu popup;
    
    private LogInd logInd;


    /**
     * Creates new form UserPanel
     */
    public UserPanel(EmployeeHandler employeeHandler, KurvHandler kurvHandler) {
        setSize(new Dimension(348, 70));
        this.employeeHandler = employeeHandler;
        popup = new JPopupMenu();
        logInd = new LogInd(employeeHandler);
        initComponents();
        setPicAndName();

        
    }
    
    public void setPicAndName() {
        
        try {
            jLabel1.setText(employeeHandler.getLogIndEmployee().getName());
            jButton_logInd.setEnabled(false);

        } catch (NullPointerException ex) {
            jLabel1.setText("Bruger ikke logget ind");
            jButton_logInd.setEnabled(true);
            
        }
        
    }
    
    public void logInd() {
        employeeHandler.setLoginEmployee(1421);
        setPicAndName();
    }
    
    public void logUd() {
        employeeHandler.logEmployeeUd();
        setPicAndName();
    }
    
    public void popUpPanel() {
        
        logInd.setPreferredSize(new Dimension(220, 400));
        
        popup.add(logInd);
        popup.setLocation(200, 200);
        popup.setVisible(true);
 
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton_logInd = new javax.swing.JButton();
        jButton_logUd = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Employee"));

        jButton_logInd.setText("Log Ind");
        jButton_logInd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_logIndActionPerformed(evt);
            }
        });

        jButton_logUd.setText("Log Ud");
        jButton_logUd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_logUdActionPerformed(evt);
            }
        });

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_logUd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_logInd))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton_logInd, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton_logUd, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_logUdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_logUdActionPerformed
        logUd();
        popup.setVisible(false);
    }//GEN-LAST:event_jButton_logUdActionPerformed

    private void jButton_logIndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_logIndActionPerformed
        popUpPanel();
    }//GEN-LAST:event_jButton_logIndActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_logInd;
    private javax.swing.JButton jButton_logUd;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
