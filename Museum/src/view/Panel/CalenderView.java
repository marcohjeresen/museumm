/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view.Panel;

import handler.StoreHandler;
import java.util.Calendar;
import model.Listeners;

/**
 *
 * @author MarcoPc
 */
public class CalenderView extends javax.swing.JPanel {
private int day;
private String x;
private StoreHandler storeHandler;
private Calendar calendar;
private Listeners listeners;
    /**
     * Creates new form CalenderView
     */
    public CalenderView(int day,String x, StoreHandler storeHandler1 ,Calendar calendar1, Listeners listeners1) {
        this.day = day;
        this.x = x;
        this.storeHandler = storeHandler1;
        this.calendar = calendar1;
        this.listeners = listeners1;
        initComponents();
        setSize(60, 60);
        setText();
    }
    
    public void setText(){
        jButton1.setText(day+x);
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
            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        storeHandler.SetDateToCalender(calendar);
        listeners.notifyListeners("Calendar");
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
