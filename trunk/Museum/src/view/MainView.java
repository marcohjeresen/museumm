/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import view.Panel.GroupsPanel;
import handler.*;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPopupMenu;
import model.*;
import view.Panel.EventView;
import view.Panel.ProductView;
import view.Panel.TicketView;
import view.Panel.*;

/**
 *
 * @author markh_000
 */
public class MainView extends javax.swing.JFrame implements ActionListener {

    private MoneyHandler moneyHandler;
    private SaleHandler saleHandler;
    private StoreHandler storeHandler;
    private Listeners listeners;
    private ArrayList<ProductView> productViewsList;
    private ArrayList<TicketView> ticketViewsList;
    private ArrayList<EventView> eventViewList;
    private ArrayList<BasketView> BasketViewList;
    private UserPanel userPanel;
    private JPopupMenu popPanl;
    private SearchPanel searchPanel;
    private CashRegistre cashRegistre;
    private BuyStuff buyStuff;
    private CalenderPanel calenderPanel;

    /**
     * Creates new form MainView
     */
    public MainView(MoneyHandler moneyHandler, SaleHandler saleHandler, StoreHandler storeHandler, Listeners listeners) {
        this.moneyHandler = moneyHandler;
        this.saleHandler = saleHandler;
        this.storeHandler = storeHandler;
        this.listeners = listeners;
        listeners.addListener(this);
        userPanel = new UserPanel(storeHandler);
        searchPanel = new SearchPanel(storeHandler);
        productViewsList = new ArrayList<>();
        ticketViewsList = new ArrayList<>();
        eventViewList = new ArrayList<>();
        cashRegistre = new CashRegistre(moneyHandler);
        BasketViewList = new ArrayList<>();

        initComponents();
        popPanl = new JPopupMenu();
        jPanel1.setVisible(true);
        jPanel1.setBounds(0, 0, 1000, 695);
        jPanel2.setVisible(false);
        jPanel2.setBounds(0, 0, 1000, 695);
        
        userPanel.setLocation(7, 15);
        jPanel_user.add(userPanel);
        userPanel.setVisible(true);
        jLabel1.setText("Kassens Beløb Dk:");
        jLabel2.setText("Kassens Beløb Euro:");
        calenderPanel = new CalenderPanel(saleHandler.getEventlineListFromData(), storeHandler, listeners);
        jPanel2.add(calenderPanel);
        createNewSale();
        setLoginPanel();
    }

    public void createNewSale() {
        saleHandler.createNewSale();
    }

    public void createGroupPanel(String Type) {
        ArrayList<GroupsPanel> specGroupsList = new ArrayList<>();
        int x = 7;
        int y = 15;
        int height = 0;
        int width = 0;
        jPanel_group.removeAll();
        repaint();
        switch (Type) {
            case "Product":
                specGroupsList.removeAll(specGroupsList);
                for (ProductGroup productGroup : storeHandler.getProductGroupList()) {
                    GroupsPanel groupsPanel = new GroupsPanel(productGroup, null, null, listeners, storeHandler);
                    if (specGroupsList.size() == 5) {
                        x = 7;
                        y = groupsPanel.getHeight() + 25;
                    } else if (specGroupsList.size() == 10) {
                        x = 7;
                        y = 2 * groupsPanel.getHeight() + 35;
                    }
                    groupsPanel.setLocation(x, y);
                    jPanel_group.add(groupsPanel);
                    jPanel_group.revalidate();
                    x += groupsPanel.getWidth() + 5;
                    groupsPanel.setVisible(true);
                    height = groupsPanel.getHeight();
                    width = groupsPanel.getWidth();
                    specGroupsList.add(groupsPanel);
                }
                break;
            case "Ticket":
                specGroupsList.removeAll(specGroupsList);
                for (TicketType ticketType : storeHandler.getTicketTypesList()) {
                    GroupsPanel groupsPanel = new GroupsPanel(null, ticketType, null, listeners, storeHandler);
                    if (specGroupsList.size() == 3) {
                        x = 7;
                        y = groupsPanel.getHeight() + 25;
                    } else if (specGroupsList.size() == 6) {
                        x = 7;
                        y = 2 * groupsPanel.getHeight() + 35;
                    }
                    groupsPanel.setLocation(x, y);
                    jPanel_group.add(groupsPanel);
                    jPanel_group.revalidate();
                    x += groupsPanel.getWidth() + 5;
                    groupsPanel.setVisible(true);
                    height = groupsPanel.getHeight();
                    width = groupsPanel.getWidth();
                    specGroupsList.add(groupsPanel);
                }
                break;
            case "Event":
                specGroupsList.removeAll(specGroupsList);
                for (EventType eventType : storeHandler.getEventTypesList()) {
                    GroupsPanel groupsPanel = new GroupsPanel(null, null, eventType, listeners, storeHandler);
                    if (specGroupsList.size() == 3) {
                        x = 7;
                        y = groupsPanel.getHeight() + 25;
                    } else if (specGroupsList.size() == 6) {
                        x = 7;
                        y = 2 * groupsPanel.getHeight() + 35;
                    }
                    groupsPanel.setLocation(x, y);
                    jPanel_group.add(groupsPanel);
                    jPanel_group.revalidate();
                    x += groupsPanel.getWidth() + 5;
                    groupsPanel.setVisible(true);
                    height = groupsPanel.getHeight();
                    width = groupsPanel.getWidth();
                    specGroupsList.add(groupsPanel);
                }
                break;
        }
    }
    public void updateQuantitis(){
        for (ProductView productView : productViewsList) {
            productView.addName();
        }
    }

    public void createShowType(boolean search) {

        int x = 7;
        int y = 15;
        int height = 0;
        int width = 0;
        jPanel_product.revalidate();
        jPanel_product.removeAll();
        jPanel_product.repaint();
        productViewsList.removeAll(productViewsList);
        eventViewList.removeAll(eventViewList);
        ticketViewsList.removeAll(ticketViewsList);
        if (storeHandler.getEmployee() == null) {
            userPanel.popUpPanel();
        }
        switch (storeHandler.getShowType()) {
            case "Product":
                if (!search) {
                    for (Product product : storeHandler.getProductsList()) {
                        if (product.getGroupNumber().equals(storeHandler.getProductGroup())) {
                            ProductView pw = new ProductView(product, saleHandler, storeHandler);
                            if (productViewsList.size() == 9) {
                                y = 15;
                                x = pw.getWidth() + 10;
                            }
                            pw.setLocation(x, y);
                            jPanel_product.add(pw);
                            jPanel_product.revalidate();
                            y += pw.getHeight() + 5;
                            pw.setVisible(true);
                            height = pw.getHeight();
                            width = pw.getWidth();
                            productViewsList.add(pw);
                        }
                    }
                } else {
                    ProductView pw = new ProductView(storeHandler.getSearchProduct(), saleHandler, storeHandler);
                    pw.setLocation(x, y);
                    jPanel_product.add(pw);
                    jPanel_product.revalidate();
                    y += pw.getHeight() + 5;
                    pw.setVisible(true);
                    height = pw.getHeight();
                    width = pw.getWidth();
                    productViewsList.add(pw);
                }
                break;
            case "Ticket":
                int antalplus = 3;
                for (int i = 0; i < antalplus; i++) {
                    TicketView tw = new TicketView(storeHandler.getTicketType(), saleHandler, i + 1);
                    if (ticketViewsList.size() == 9) {
                        y = 15;
                        x = tw.getWidth() + 10;
                    }
                    tw.setLocation(x, y);
                    jPanel_product.add(tw);
                    jPanel_product.revalidate();
                    y += tw.getHeight() + 5;
                    tw.setVisible(true);
                    height = tw.getHeight();
                    width = tw.getWidth();
                    ticketViewsList.add(tw);
                }
                break;
            case "Event":
                EventView pw = new EventView(storeHandler.getEventType(), saleHandler);

                pw.setLocation(x, y);
                jPanel_product.add(pw);
                jPanel_product.revalidate();
                y += pw.getHeight() + 5;
                pw.setVisible(true);
                height = pw.getHeight();
                width = pw.getWidth();
                eventViewList.add(pw);
                break;
        }
    }

    public void fillBasket() {
        int x = 3;
        int y = 15;
        jPanel_kurv.removeAll();
        jPanel_kurv.revalidate();
        jPanel_kurv.repaint();
        BasketViewList.removeAll(eventViewList);
        if (!saleHandler.getSale().getProductLine().isEmpty()) {
            for (ProductLine productLine : saleHandler.getSale().getProductLine()) {
                BasketView bv = new BasketView(productLine, null, null, saleHandler, listeners);

                bv.setLocation(x, y);
                y += bv.getHeight() + 5;
                jPanel_kurv.add(bv);
                jPanel_kurv.revalidate();
                bv.setVisible(true);
                BasketViewList.add(bv);
            }
        }
        if (!saleHandler.getSale().getTicketLine().isEmpty()) {
            for (TicketLine ticketLine : saleHandler.getSale().getTicketLine()) {
                BasketView bv = new BasketView(null, null, ticketLine, saleHandler, listeners);

                bv.setLocation(x, y);
                y += bv.getHeight() + 5;
                jPanel_kurv.add(bv);
                jPanel_kurv.revalidate();
                bv.setVisible(true);
                BasketViewList.add(bv);
            }
        }
        if (!saleHandler.getSale().getEventLine().isEmpty()) {
            for (EventLine eventLine : saleHandler.getSale().getEventLine()) {
                BasketView bv = new BasketView(null, eventLine, null, saleHandler, listeners);

                bv.setLocation(x, y);
                y += bv.getHeight() + 5;
                jPanel_kurv.add(bv);
                jPanel_kurv.revalidate();
                bv.setVisible(true);
                BasketViewList.add(bv);
            }
        }

    }

    public void setLoginPanel() {
        if (storeHandler.getEmployee() != null) {
            saleHandler.addEmployeeToSale();
            userPanel.closepopup();
            userPanel.setPicAndName();
        } else {
            jLabel1.setText("Kassens Beløb Dk: ");
            jLabel2.setText("Kassens Beløb Euro: ");
            saleHandler.clearSale();
        }
    }

    public void setCashRegistre() {
        if (storeHandler.getEmployee() != null && moneyHandler.getCashRegister() == null) {
            popPanl.add(cashRegistre);
            popPanl.setLocation(250, 220);
            popPanl.setVisible(true);
        } else if (storeHandler.getEmployee() != null && moneyHandler.getCashRegister() != null) {
            double dk = moneyHandler.getCashRegister().getAmountDk();
            dk = dk / 100;
            double euro = moneyHandler.getCashRegister().getAmountEuro();
            euro = euro / 100;
            jLabel1.setText("Kassens Beløb Dk: " + dk);
            jLabel2.setText("Kassens Beløb Euro: " + euro);
            popPanl.setVisible(false);
        }
    }

    public void searchProduct(boolean openClose) {
        if (openClose) {
            popPanl.removeAll();
            popPanl.add(searchPanel);
            popPanl.setLocation(250, 220);
            popPanl.setVisible(true);
        } else if (!openClose) {
            popPanl.setVisible(false);
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
        jPanel_group = new javax.swing.JPanel();
        jPanel_kurv = new javax.swing.JPanel();
        jPanel_product = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel_user = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel_group.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Typer:"));

        javax.swing.GroupLayout jPanel_groupLayout = new javax.swing.GroupLayout(jPanel_group);
        jPanel_group.setLayout(jPanel_groupLayout);
        jPanel_groupLayout.setHorizontalGroup(
            jPanel_groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel_groupLayout.setVerticalGroup(
            jPanel_groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 143, Short.MAX_VALUE)
        );

        jPanel_kurv.setBorder(javax.swing.BorderFactory.createTitledBorder("Kurv:"));

        javax.swing.GroupLayout jPanel_kurvLayout = new javax.swing.GroupLayout(jPanel_kurv);
        jPanel_kurv.setLayout(jPanel_kurvLayout);
        jPanel_kurvLayout.setHorizontalGroup(
            jPanel_kurvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 458, Short.MAX_VALUE)
        );
        jPanel_kurvLayout.setVerticalGroup(
            jPanel_kurvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 387, Short.MAX_VALUE)
        );

        jPanel_product.setBorder(javax.swing.BorderFactory.createTitledBorder("Produkt:"));

        javax.swing.GroupLayout jPanel_productLayout = new javax.swing.GroupLayout(jPanel_product);
        jPanel_product.setLayout(jPanel_productLayout);
        jPanel_productLayout.setHorizontalGroup(
            jPanel_productLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 265, Short.MAX_VALUE)
        );
        jPanel_productLayout.setVerticalGroup(
            jPanel_productLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 387, Short.MAX_VALUE)
        );

        jButton2.setText("Retuner");

        jButton3.setText("Betal");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Tøm Kurv");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Fortryd");

        jButton6.setText("Søg På Product Nummer");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Vis Event Typer");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Vis Product Grupper");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Vis Ticket Typer");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jPanel_user.setBorder(javax.swing.BorderFactory.createTitledBorder("Bruger"));

        javax.swing.GroupLayout jPanel_userLayout = new javax.swing.GroupLayout(jPanel_user);
        jPanel_user.setLayout(jPanel_userLayout);
        jPanel_userLayout.setHorizontalGroup(
            jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 389, Short.MAX_VALUE)
        );
        jPanel_userLayout.setVerticalGroup(
            jPanel_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 53, Short.MAX_VALUE)
        );

        jButton10.setText("Andet Godt");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel_product, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addComponent(jPanel_kurv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jPanel_group, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel_group, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel_kurv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel_product, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, "card2");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(870, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(625, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, "card3");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        createGroupPanel("Product");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        createGroupPanel("Event");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        createGroupPanel("Ticket");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jPanel1.setVisible(true);
        jPanel2.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        jPanel1.setVisible(false);
        jPanel2.setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        searchProduct(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        saleHandler.clearSaleinventori();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       buyStuff = new BuyStuff(saleHandler.getSale(), moneyHandler, listeners, saleHandler, storeHandler);
       buyStuff.setBounds(0, 0, 350, 430);
        buyStuff.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel_group;
    private javax.swing.JPanel jPanel_kurv;
    private javax.swing.JPanel jPanel_product;
    private javax.swing.JPanel jPanel_user;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {

            case "ShowType Change":
                createShowType(false);
                break;
            case "Employee Login":
                setCashRegistre();
                setLoginPanel();
                break;
            case "Cash Registre":
                setCashRegistre();
                break;
            case "Search Product":
                createShowType(true);
                searchProduct(false);
                break;
            case "Update Basket":
                fillBasket();
                break;
            case "End Sale":
                createNewSale();
                setLoginPanel();
                setCashRegistre();
                fillBasket();
                updateQuantitis();
                break;
            default:

        }
    }
}
