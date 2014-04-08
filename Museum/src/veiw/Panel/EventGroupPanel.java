/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package veiw.Panel;

import java.awt.Dimension;
import model.*;
import model.handler.*;

/**
 *
 * @author MarcoPc
 */
public class EventGroupPanel extends javax.swing.JPanel {
private Listeners listners;
private EventHandler eventHandler;
private EventType eventType;
private KurvHandler kurvHandler;
    /**
     * Creates new form EventGroup
     */
    public EventGroupPanel(EventHandler eventHandler1, EventType eventType1, Listeners listeners, KurvHandler kurvHandler) {
        this.eventHandler = eventHandler1;
        this.eventType = eventType1;
        this.listenerList = listenerList;
        this.kurvHandler = kurvHandler;
        int x = 0;
        x = 323;
        setSize(new Dimension(x, 40));
        initComponents();
        jButton1.setText(eventType1.getType());
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
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        eventHandler.setSpecEventType(eventType);
        kurvHandler.setTypeView("Event");
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
