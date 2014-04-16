/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view.Panel;
import model.*;
import handler.*;
/**
 *
 * @author MarcoPc
 */
public class BasketView extends javax.swing.JPanel {
private Sale sale;
private SaleHandler saleHandler;
private ProductLine productLine;
private EventLine eventLine;
private TicketLine ticketLine;
private Listeners listeners;

    /**
     * Creates new form BasketView
     */
    public BasketView(ProductLine productLine, EventLine eventLine, TicketLine ticketLine, SaleHandler saleHandler1, Listeners listeners1) {
        this.saleHandler = saleHandler1;
        this.productLine = productLine;
        this.eventLine = eventLine;
        this.ticketLine = ticketLine;
        this.listeners = listeners1;
        initComponents();
        setSize(450, 40);
        setLabel();
    }
    
    public void setLabel(){
        if (productLine != null) {
            jLabel_title.setText(productLine.getProduct().getName());
            jLabel_quantities.setText("Antal: " + productLine.getQuantities());
            double priceDk = (productLine.getProduct().getPriceDk() * productLine.getQuantities());
            priceDk = priceDk / 100;
            double priceEuro = (productLine.getProduct().getPriceEuro() * productLine.getQuantities());
            priceEuro = priceEuro /100;
            jLabel_dk.setText("Pris Dk: " + priceDk);
            jLabel_euro.setText("Pris Euro: " + priceEuro);
        }else if (eventLine != null) {
            jLabel_title.setText(eventLine.getEventtype().getType());
            jLabel_quantities.setText("Antal: " + eventLine.getQuantities());
            double priceDk = (eventLine.getEventtype().getPriceDk() * eventLine.getQuantities()) /100;
            double priceEuro = (eventLine.getEventtype().getPriceEuro() * eventLine.getQuantities()) /100;
            jLabel_dk.setText("Pris Dk: " + priceDk);
            jLabel_euro.setText("Pris Euro: " + priceEuro);
        }else if (ticketLine != null) {
            jLabel_title.setText(ticketLine.getTicketType().getType());
            jLabel_quantities.setText("Antal: " + ticketLine.getQuantities());
            double priceDk = (ticketLine.getTicketType().getPriceDk() * ticketLine.getQuantities()) /100;
            double priceEuro = (ticketLine.getTicketType().getPriceEuro() * ticketLine.getQuantities()) /100;
            jLabel_dk.setText("Pris Dk: " + priceDk);
            jLabel_euro.setText("Pris Euro: " + priceEuro);
        }
    }
    
    public void addQuantities(){
        if (productLine != null) {
            productLine.setQuantities(productLine.getQuantities() +1);
        }else if (eventLine != null){
            eventLine.setQuantities(eventLine.getQuantities() +1);
        }else if (ticketLine != null) {
            ticketLine.setQuantities(ticketLine.getQuantities() +1);
        }
        setLabel();
    }
    
    public void removeQuantities(){
        if (productLine != null) {
            if (productLine.getQuantities() != 1) {
                productLine.setQuantities(productLine.getQuantities() -1);
            }
        }else if (eventLine != null){
            if (eventLine.getQuantities() != 1) {
                eventLine.setQuantities(eventLine.getQuantities() -1);
            }
        }else if (ticketLine != null) {
            if (ticketLine.getQuantities() != 1) {
                 ticketLine.setQuantities(ticketLine.getQuantities() -1);
            }
        }
        setLabel();
    }
    
    public void clearLine(){
        if(productLine != null){
          saleHandler.removeProductLineFromSale(productLine.getId());
        }else if(eventLine != null){
          saleHandler.removeEventLineFromSale(eventLine.getId());
        }else if(ticketLine != null){
          saleHandler.removeTicketLineFromSale(ticketLine.getId());
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

        jButton_clear = new javax.swing.JButton();
        jButton_add = new javax.swing.JButton();
        jButton_remove = new javax.swing.JButton();
        jLabel_title = new javax.swing.JLabel();
        jLabel_quantities = new javax.swing.JLabel();
        jLabel_dk = new javax.swing.JLabel();
        jLabel_euro = new javax.swing.JLabel();

        jButton_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pict/fjeern2.jpg"))); // NOI18N
        jButton_clear.setText("X");
        jButton_clear.setMaximumSize(new java.awt.Dimension(45, 23));
        jButton_clear.setMinimumSize(new java.awt.Dimension(45, 23));
        jButton_clear.setName(""); // NOI18N
        jButton_clear.setPreferredSize(new java.awt.Dimension(45, 23));
        jButton_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_clearActionPerformed(evt);
            }
        });

        jButton_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pict/tilføj3.jpg"))); // NOI18N
        jButton_add.setText("+");
        jButton_add.setPreferredSize(new java.awt.Dimension(45, 23));
        jButton_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addActionPerformed(evt);
            }
        });

        jButton_remove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pict/romove2.jpg"))); // NOI18N
        jButton_remove.setText("-");
        jButton_remove.setMaximumSize(new java.awt.Dimension(45, 23));
        jButton_remove.setMinimumSize(new java.awt.Dimension(45, 23));
        jButton_remove.setPreferredSize(new java.awt.Dimension(45, 23));
        jButton_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_removeActionPerformed(evt);
            }
        });

        jLabel_title.setText("jLabel1");

        jLabel_quantities.setText("jLabel2");

        jLabel_dk.setText("jLabel3");

        jLabel_euro.setText("jLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton_clear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jButton_add, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_remove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel_quantities, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel_dk, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel_euro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel_title, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton_add, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton_remove, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel_title)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_quantities)
                    .addComponent(jLabel_dk)
                    .addComponent(jLabel_euro)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_clearActionPerformed
       clearLine();
    }//GEN-LAST:event_jButton_clearActionPerformed

    private void jButton_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addActionPerformed
        addQuantities();
    }//GEN-LAST:event_jButton_addActionPerformed

    private void jButton_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_removeActionPerformed
        removeQuantities();
    }//GEN-LAST:event_jButton_removeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_add;
    private javax.swing.JButton jButton_clear;
    private javax.swing.JButton jButton_remove;
    private javax.swing.JLabel jLabel_dk;
    private javax.swing.JLabel jLabel_euro;
    private javax.swing.JLabel jLabel_quantities;
    private javax.swing.JLabel jLabel_title;
    // End of variables declaration//GEN-END:variables
}
