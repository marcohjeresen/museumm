/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veiw;

import veiw.Panel.ProductVeiw;
import veiw.Panel.Groups;
import veiw.Panel.KurvPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPopupMenu;
import model.*;
import model.handler.*;
import veiw.Panel.SearchPanel;
import veiw.Panel.UserPanel;

/**
 *
 * @author markh_000
 */
public class Guie2 extends javax.swing.JFrame implements ActionListener {

    private ArrayList<Groups> groupsList;
    private ArrayList<ProductVeiw> prodList;
    private ArrayList<KurvPanel> kurvList;
    private EmployeeHandler employeeHandler;
    private ProductHandler pr;
    private Listeners listeners;
    private KurvHandler kurvHandler;
    private UserPanel up;
    private JPopupMenu popup;
    private SearchPanel searchPanel;

    /**
     * Creates new form Guie2
     */
    public Guie2(ProductHandler pr, Listeners listeners, KurvHandler kurvHandler, EmployeeHandler employeeHandler) {
        this.listeners = listeners;
        this.kurvHandler = kurvHandler;
        this.employeeHandler = employeeHandler;
        up = new UserPanel(employeeHandler, kurvHandler);
        searchPanel = new SearchPanel(pr, this);
        setSize(new Dimension(750, 600));
        this.pr = pr;
        groupsList = new ArrayList<>();
        prodList = new ArrayList<>();
        kurvList = new ArrayList<>();
        initComponents();
        pr.addListener(this);
        createPanels(pr.getGroupList());
        setProductPanels(pr.getSpecList());
        setKurvPanel();
        setLoginPanel();
        disableEnableBottoms();
        popup = new JPopupMenu();
    }

    public void disableEnableBottoms() {
        try {
            String name = employeeHandler.getLogIndEmployee().getName();
            jButton_betal.setEnabled(true);
            jButton_retunere.setEnabled(true);
        } catch (NullPointerException ex) {
            jButton_betal.setEnabled(false);
            jButton_retunere.setEnabled(false);
        }
    }

    private void createPanels(ArrayList<ProductGroup> groupList) {
        int x = 7;
        int y = 15;
        int height = 0;
        int width = 0;
        for (ProductGroup groupl : groupList) {
            Groups ap = new Groups(groupl, listeners, pr);
            if (groupsList.size() == 4) {
                x = 7;
                y = ap.getHeight() + 25;
            } else if (groupsList.size() == 8) {
                x = 7;
                y = 2 * ap.getHeight() + 35;
            }
            ap.setLocation(x, y);
            jPanel_groups.add(ap);
            jPanel_groups.revalidate();
            x += ap.getWidth() + 5;
            ap.setVisible(true);
            height = ap.getHeight();
            width = ap.getWidth();
            groupsList.add(ap);

        }
    }

    public void setProductPanels(ArrayList<Product> productList) {
        int x = 7;
        int y = 15;
        int height = 0;
        int width = 0;
        prodList.removeAll(prodList);
        jPanel2.removeAll();
        for (Product product : productList) {
            ProductVeiw pw = new ProductVeiw(product, kurvHandler);
            if (prodList.size() == 9) {
                y = 15;
                x = pw.getWidth() + 10;
            }
            pw.setLocation(x, y);
            jPanel2.add(pw);
            jPanel2.revalidate();
            y += pw.getHeight() + 5;
            pw.setVisible(true);
            height = pw.getHeight();
            width = pw.getWidth();
            prodList.add(pw);
        }
    }

    public void setKurvPanel() {
        int x = 7;
        int y = 15;
        int height = 0;
        int width = 0;
        jPanel_KurvList.removeAll();
        kurvList.removeAll(kurvList);
        KurvPanel k = new KurvPanel(kurvHandler.toString(), kurvHandler.priceToString());

        k.setLocation(x, y);
        jPanel_KurvList.add(k);
        jPanel_KurvList.revalidate();
        x += k.getWidth() + 5;
        k.setVisible(true);
        height = k.getHeight();
        width = k.getWidth();
        kurvList.add(k);

    }

    public void setLoginPanel() {
        int x = 0;
        int y = 0;
        int height = 0;
        int width = 0;

        up.closepopup();
        up.setPicAndName();
        jPanel_user.removeAll();
        up.setLocation(x, y);
        jPanel_user.add(up);
        jPanel_user.revalidate();
        y += up.getHeight() + 5;
        up.setVisible(true);
        height = up.getHeight();
        width = up.getWidth();
repaint();
    }
    
    public void popUpPanel() {
        
        searchPanel.setPreferredSize(new Dimension(235, 410));
        
        popup.add(searchPanel);
        popup.setLocation(100, 100);
        popup.setVisible(true);
 
    }
    public void closePopupPanel(){
        popup.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton_retunere = new javax.swing.JButton();
        jPanel_groups = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel_KurvList = new javax.swing.JPanel();
        jButton_clearKurv = new javax.swing.JButton();
        jButton_betal = new javax.swing.JButton();
        jPanel_user = new javax.swing.JPanel();
        jButton_søg = new javax.swing.JButton();
        jButton_fortryd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton_retunere.setText("Retunere");

        jPanel_groups.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "ProductGroup"));

        javax.swing.GroupLayout jPanel_groupsLayout = new javax.swing.GroupLayout(jPanel_groups);
        jPanel_groups.setLayout(jPanel_groupsLayout);
        jPanel_groupsLayout.setHorizontalGroup(
            jPanel_groupsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 791, Short.MAX_VALUE)
        );
        jPanel_groupsLayout.setVerticalGroup(
            jPanel_groupsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Product"));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 253, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 382, Short.MAX_VALUE)
        );

        jPanel_KurvList.setBorder(javax.swing.BorderFactory.createTitledBorder("Kurv"));

        javax.swing.GroupLayout jPanel_KurvListLayout = new javax.swing.GroupLayout(jPanel_KurvList);
        jPanel_KurvList.setLayout(jPanel_KurvListLayout);
        jPanel_KurvListLayout.setHorizontalGroup(
            jPanel_KurvListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );
        jPanel_KurvListLayout.setVerticalGroup(
            jPanel_KurvListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 382, Short.MAX_VALUE)
        );

        jButton_clearKurv.setText("Clear Kurv");
        jButton_clearKurv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_clearKurvActionPerformed(evt);
            }
        });

        jButton_betal.setText("Betal");

        javax.swing.GroupLayout jPanel_userLayout = new javax.swing.GroupLayout(jPanel_user);
        jPanel_user.setLayout(jPanel_userLayout);
        jPanel_userLayout.setHorizontalGroup(
            jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel_userLayout.setVerticalGroup(
            jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 89, Short.MAX_VALUE)
        );

        jButton_søg.setText("Søg");
        jButton_søg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_søgActionPerformed(evt);
            }
        });

        jButton_fortryd.setText("Fortryd");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jButton_søg, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jPanel_groups, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton_fortryd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_clearKurv, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_retunere, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_betal, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel_KurvList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_groups, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jButton_søg, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_KurvList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_betal, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_retunere, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_clearKurv, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_fortryd, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_clearKurvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_clearKurvActionPerformed
       kurvHandler.clearKurv();
    }//GEN-LAST:event_jButton_clearKurvActionPerformed

    private void jButton_søgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_søgActionPerformed
        popUpPanel();
    }//GEN-LAST:event_jButton_søgActionPerformed

    /**
     * @param ae
     * @param args the command line arguments
     */
    public void actionPerformed(ActionEvent ae) {
        setProductPanels(pr.getSpecList());
        setKurvPanel();
        setLoginPanel();
        disableEnableBottoms();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_betal;
    private javax.swing.JButton jButton_clearKurv;
    private javax.swing.JButton jButton_fortryd;
    private javax.swing.JButton jButton_retunere;
    private javax.swing.JButton jButton_søg;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel_KurvList;
    private javax.swing.JPanel jPanel_groups;
    private javax.swing.JPanel jPanel_user;
    // End of variables declaration//GEN-END:variables
}
