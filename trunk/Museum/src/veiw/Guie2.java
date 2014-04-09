/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and openSearchPanel the template in the editor.
 */
package veiw;

import veiw.Panel.ProductVeiw;
import veiw.Panel.ProductGroupsPanel;
import veiw.Panel.KurvPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPopupMenu;
import model.*;
import model.handler.*;
import veiw.Panel.*;

/**
 *
 * @author markh_000
 */
public class Guie2 extends javax.swing.JFrame implements ActionListener {

    private ArrayList<ProductGroupsPanel> productgroupsList;
    private ArrayList<ProductVeiw> prodList;

    private ArrayList<EventGroupPanel> eventgroupList;
    private ArrayList<EventView> eventviewList;

    private ArrayList<TicketGroupPanel> ticketGroupList;
    private ArrayList<TicketVeiw> ticketViewList;

    private ArrayList<KurvPanel> kurvList;
    private EmployeeHandler employeeHandler;
    private ProductHandler pr;
    private EventHandler eventHandler;
    private TicketHandler ticketHandler;
    private Listeners listeners;
    private KurvHandler kurvHandler;
    private UserPanel userPanel;
    private JPopupMenu searchPop;
    private JPopupMenu cashRegistre;
    private SearchPanel searchPanel;
    private CashRegistre cashRegistre1;
    private boolean openSearchPanel;

    /**
     * Creates new form Guie2
     */
    public Guie2(ProductHandler pr, Listeners listeners, KurvHandler kurvHandler, EmployeeHandler employeeHandler, EventHandler eventHandler, TicketHandler ticketHandler1) {
        this.listeners = listeners;
        this.kurvHandler = kurvHandler;
        this.employeeHandler = employeeHandler;
        this.pr = pr;
        this.eventHandler = eventHandler;
        this.ticketHandler = ticketHandler1;
        userPanel = new UserPanel(employeeHandler, kurvHandler);
        searchPanel = new SearchPanel(pr, this, kurvHandler);
        cashRegistre1 = new CashRegistre(kurvHandler);
        setSize(new Dimension(750, 600));

        productgroupsList = new ArrayList<>();
        eventgroupList = new ArrayList<>();
        ticketGroupList = new ArrayList<>();
        prodList = new ArrayList<>();
        eventviewList = new ArrayList<>();
        ticketViewList = new ArrayList<>();
        kurvList = new ArrayList<>();
        initComponents();
        pr.addListener(this);
        createPanels("Product");
        searchPop = new JPopupMenu();
        cashRegistre = new JPopupMenu();
        openSearchPanel = false;
        kurvHandler.setTypeView("Product");
        setViewPanels();
        setKurvPanel();
        setLoginPanel();
        disableEnableBottoms();

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

    private void createPanels(String type) {
        int x = 7;
        int y = 15;
        int height = 0;
        int width = 0;
        switch (type) {
            case "Product":
                jPanel_groups.removeAll();

                repaint();
                productgroupsList.removeAll(productgroupsList);
                for (ProductGroup groupl : pr.getGroupList()) {
                    ProductGroupsPanel ap = new ProductGroupsPanel(groupl, listeners, pr, kurvHandler);
                    if (productgroupsList.size() == 5) {
                        x = 7;
                        y = ap.getHeight() + 25;
                    } else if (productgroupsList.size() == 10) {
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
                    productgroupsList.add(ap);
                }
                break;
            case "Event":
                jPanel_groups.removeAll();
                repaint();
                eventgroupList.removeAll(eventgroupList);
                for (EventType eventType : eventHandler.getEventTypeList()) {
                    EventGroupPanel ep = new EventGroupPanel(eventHandler, eventType, listeners, kurvHandler);
                    if (eventgroupList.size() == 3) {
                        x = 7;
                        y = ep.getHeight() + 25;
                    } else if (eventgroupList.size() == 6) {
                        x = 7;
                        y = 2 * ep.getHeight() + 35;
                    }
                    ep.setLocation(x, y);
                    jPanel_groups.add(ep);
                    jPanel_groups.revalidate();
                    x += ep.getWidth() + 5;
                    ep.setVisible(true);
                    height = ep.getHeight();
                    width = ep.getWidth();
                    eventgroupList.add(ep);
                }

                break;
            case "Ticket":
                jPanel_groups.removeAll();

                repaint();
                ticketGroupList.removeAll(ticketGroupList);
                for (TicketType ticketType : ticketHandler.getTpList()) {
                    TicketGroupPanel tp = new TicketGroupPanel(listeners, ticketHandler, ticketType, kurvHandler);
                    if (ticketGroupList.size() == 3) {
                        x = 7;
                        y = tp.getHeight() + 25;
                    } else if (ticketGroupList.size() == 6) {
                        x = 7;
                        y = 2 * tp.getHeight() + 35;
                    }
                    tp.setLocation(x, y);
                    jPanel_groups.add(tp);
                    jPanel_groups.revalidate();
                    x += tp.getWidth() + 5;
                    tp.setVisible(true);
                    height = tp.getHeight();
                    width = tp.getWidth();
                    ticketGroupList.add(tp);
                }

                break;

        }
    }

    public void setViewPanels() {
        int x = 7;
        int y = 15;
        int height = 0;
        int width = 0;
        jPanel2.revalidate();
        jPanel2.removeAll();
        jPanel2.repaint();
        if (employeeHandler.getLogIndEmployee() == null) {
            userPanel.popUpPanel();
        } else {
            switch (kurvHandler.getTypeView()) {
                case "Product":
                    prodList.removeAll(prodList);
                    jPanel2.removeAll();
                    for (Product product : pr.getSpecList()) {
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
                    break;
                case "Ticket":
                    ticketViewList.removeAll(ticketViewList);
                    jPanel2.removeAll();
                    int antalplus = 3;
                    for (int i = 0; i < antalplus; i++) {
                        TicketVeiw tw = new TicketVeiw(ticketHandler.getSpecticket(), kurvHandler, i + 1);
                        if (ticketViewList.size() == 9) {
                            y = 15;
                            x = tw.getWidth() + 10;
                        }
                        tw.setLocation(x, y);
                        jPanel2.add(tw);
                        jPanel2.revalidate();
                        y += tw.getHeight() + 5;
                        tw.setVisible(true);
                        height = tw.getHeight();
                        width = tw.getWidth();
                        ticketViewList.add(tw);

                    }

                    break;

                case "Event":
                    eventviewList.removeAll(eventviewList);
                    jPanel2.removeAll();

                    EventView pw = new EventView(kurvHandler, eventHandler.getEventType());
//                    
                    pw.setLocation(x, y);
                    jPanel2.add(pw);
                    jPanel2.revalidate();
                    y += pw.getHeight() + 5;
                    pw.setVisible(true);
                    height = pw.getHeight();
                    width = pw.getWidth();
                    eventviewList.add(pw);
                    break;
                default:
            }
        }
    }

    public void setKurvPanel() {
        if (employeeHandler.getLogIndEmployee() != null) {
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

    }

    public void setLoginPanel() {
        userPanel.closepopup();
        userPanel.setPicAndName();
        jPanel_user.removeAll();
        userPanel.setLocation(0, 0);
        jPanel_user.add(userPanel);
        jPanel_user.revalidate();
        userPanel.setVisible(true);
    }

    public void searchPanel() {

        if (!openSearchPanel) {
            openSearchPanel = true;
            searchPanel.setPreferredSize(new Dimension(235, 410));
            searchPop.add(searchPanel);
            searchPop.setLocation(200, 150);
            searchPop.setVisible(true);
        } else {
            closeSearchPanel();
            openSearchPanel = false;
        }
    }

    public void closeSearchPanel() {
        openSearchPanel = false;
        searchPop.setVisible(false);
    }

    public void setCashRegistre() {

        closeCashrige();
        if (employeeHandler.getLogIndEmployee() != null && kurvHandler.getCashRegister() == null) {
            cashRegistre.add(cashRegistre1);
            cashRegistre.setLocation(250, 220);
            cashRegistre.setVisible(true);
//        

        }

    }

    public void closeCashrige() {
        cashRegistre.setVisible(false);
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
        jButton_product = new javax.swing.JButton();
        jButton_event = new javax.swing.JButton();
        jButton_billet = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton_retunere.setText("Retunere");

        jPanel_groups.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "ProductGroup"));

        javax.swing.GroupLayout jPanel_groupsLayout = new javax.swing.GroupLayout(jPanel_groups);
        jPanel_groups.setLayout(jPanel_groupsLayout);
        jPanel_groupsLayout.setHorizontalGroup(
            jPanel_groupsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
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
            .addGap(0, 265, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel_KurvList.setBorder(javax.swing.BorderFactory.createTitledBorder("Kurv"));

        javax.swing.GroupLayout jPanel_KurvListLayout = new javax.swing.GroupLayout(jPanel_KurvList);
        jPanel_KurvList.setLayout(jPanel_KurvListLayout);
        jPanel_KurvListLayout.setHorizontalGroup(
            jPanel_KurvListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 506, Short.MAX_VALUE)
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

        jButton_søg.setText("Søg På Produkt Nummer");
        jButton_søg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_søgActionPerformed(evt);
            }
        });

        jButton_fortryd.setText("Fortryd");
        jButton_fortryd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_fortrydActionPerformed(evt);
            }
        });

        jButton_product.setText("Vis Produkt Grupper");
        jButton_product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_productActionPerformed(evt);
            }
        });

        jButton_event.setText("Vis Event Typer");
        jButton_event.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_eventActionPerformed(evt);
            }
        });

        jButton_billet.setText("Vis Billet Typer");
        jButton_billet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_billetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_fortryd, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_clearKurv, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_retunere, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_betal, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton_søg, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton_billet, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButton_product, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                                    .addComponent(jButton_event, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel_KurvList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(jPanel_groups, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_groups, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel_KurvList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jButton_søg, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jButton_product, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_event, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_billet, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        searchPanel();

    }//GEN-LAST:event_jButton_søgActionPerformed

    private void jButton_fortrydActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_fortrydActionPerformed
        kurvHandler.cancelLast();
    }//GEN-LAST:event_jButton_fortrydActionPerformed

    private void jButton_productActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_productActionPerformed
        createPanels("Product");
    }//GEN-LAST:event_jButton_productActionPerformed

    private void jButton_eventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_eventActionPerformed
        createPanels("Event");
    }//GEN-LAST:event_jButton_eventActionPerformed

    private void jButton_billetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_billetActionPerformed
        createPanels("Ticket");
    }//GEN-LAST:event_jButton_billetActionPerformed

    /**
     * @param ae
     * @param args the command line arguments
     */
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case "Update kurv":
                setKurvPanel();
                break;
            case "Employee log":
                setLoginPanel();
                setCashRegistre();
                break;
            case "CashRegister":
                setCashRegistre();
                break;
            case "Update view":
                setLoginPanel();
                setViewPanels();
                break;
            default:
                setKurvPanel();
                setLoginPanel();
                setViewPanels();
                disableEnableBottoms();
                setCashRegistre();

        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_betal;
    private javax.swing.JButton jButton_billet;
    private javax.swing.JButton jButton_clearKurv;
    private javax.swing.JButton jButton_event;
    private javax.swing.JButton jButton_fortryd;
    private javax.swing.JButton jButton_product;
    private javax.swing.JButton jButton_retunere;
    private javax.swing.JButton jButton_søg;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel_KurvList;
    private javax.swing.JPanel jPanel_groups;
    private javax.swing.JPanel jPanel_user;
    // End of variables declaration//GEN-END:variables
}
