/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.handler.*;
import model.controller.*;
import utillity.view.StatestikPanel;
import utillity.view.*;
import view.Panel.GroupsPanel;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPopupMenu;
import model.*;
import utillity.PrintHandler;
import view.Panel.EventView;
import view.Panel.ProductView;
import view.Panel.TicketView;
import view.Panel.*;
import utillity.UtilHandler;

/**
 *
 * @author markh_000
 */
public class MainView extends javax.swing.JFrame implements ActionListener {

    private MoneyHandler moneyHandler;
    private SaleHandler saleHandler;
    private StoreHandler storeHandler;
    private StoreController storeController;
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
    private UtilHandler UtilHandler;
    private PrintHandler printHandler;

    /**
     * Creates new form MainView
     */
    public MainView(MoneyHandler moneyHandler, SaleHandler saleHandler, StoreHandler storeHandler, StoreController storeController, Listeners listeners, UtilHandler statistikHandler) {
        this.moneyHandler = moneyHandler;
        this.saleHandler = saleHandler;
        this.storeHandler = storeHandler;
        this.storeController = storeController;
        this.listeners = listeners;
        this.UtilHandler = statistikHandler;
        printHandler = new PrintHandler(saleHandler);
        listeners.addListener(this);
        userPanel = new UserPanel(storeHandler, listeners);
        searchPanel = new SearchPanel(storeHandler);
        productViewsList = new ArrayList<>();
        ticketViewsList = new ArrayList<>();
        eventViewList = new ArrayList<>();
        cashRegistre = new CashRegistre(moneyHandler, false, storeHandler.getEmployee());
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

    public void updateQuantitis() {
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
            jButton_pay.setEnabled(false);
            jButton_search.setEnabled(false);
            jButton_shoeProduct.setEnabled(false);
            jButton_showEvent.setEnabled(false);
            jButton_showTicket.setEnabled(false);
            jButton_emtybasket.setEnabled(false);
            jButton_closeregi.setEnabled(false);
        }
    }

    public void setCashRegistre(Boolean open) {
        if (open) {
            cashRegistre = new CashRegistre(moneyHandler, true, storeHandler.getEmployee());
            if (storeHandler.getEmployee() != null && moneyHandler.getCashRegister() == null) {
                popPanl.setVisible(false);
                popPanl.removeAll();
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
                userPanel.closepopup();
                userPanel.setPicAndName();
                jButton_pay.setEnabled(true);
                jButton_search.setEnabled(true);
                jButton_shoeProduct.setEnabled(true);
                jButton_showEvent.setEnabled(true);
                jButton_showTicket.setEnabled(true);
                jButton_emtybasket.setEnabled(true);
                jButton_closeregi.setEnabled(true);
            }
        } else if (!open) {
            popPanl.removeAll();
            cashRegistre = new CashRegistre(moneyHandler, false, storeHandler.getEmployee());
            popPanl.add(cashRegistre);
            popPanl.setLocation(250, 220);
            popPanl.setVisible(true);
        }
    }
    public void LogOut(){
        storeHandler.logoutEmployee();
        jPanel_kurv.removeAll();
        jPanel_group.removeAll();
        jPanel_product.removeAll();
        popPanl.setVisible(false);
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
        jButton_pay = new javax.swing.JButton();
        jButton_emtybasket = new javax.swing.JButton();
        jButton_search = new javax.swing.JButton();
        jButton_showEvent = new javax.swing.JButton();
        jButton_shoeProduct = new javax.swing.JButton();
        jButton_showTicket = new javax.swing.JButton();
        jPanel_user = new javax.swing.JPanel();
        jButton_stuff = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton_bag = new javax.swing.JButton();
        jPanel_stof = new javax.swing.JPanel();
        jButton_calender = new javax.swing.JButton();
        jButton_closeregi = new javax.swing.JButton();
        jButton_statestik = new javax.swing.JButton();
        jButton_lager = new javax.swing.JButton();

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

        jButton_pay.setText("Betal");
        jButton_pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_payActionPerformed(evt);
            }
        });

        jButton_emtybasket.setText("Tøm Kurv");
        jButton_emtybasket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_emtybasketActionPerformed(evt);
            }
        });

        jButton_search.setText("Søg På Product Nummer");
        jButton_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_searchActionPerformed(evt);
            }
        });

        jButton_showEvent.setText("Vis Event Typer");
        jButton_showEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_showEventActionPerformed(evt);
            }
        });

        jButton_shoeProduct.setText("Vis Product Grupper");
        jButton_shoeProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_shoeProductActionPerformed(evt);
            }
        });

        jButton_showTicket.setText("Vis Ticket Typer");
        jButton_showTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_showTicketActionPerformed(evt);
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

        jButton_stuff.setText("Andet Godt");
        jButton_stuff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_stuffActionPerformed(evt);
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
                            .addComponent(jButton_search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_shoeProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_showEvent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_showTicket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel_product, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addComponent(jPanel_kurv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_stuff, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_emtybasket, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_pay, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addComponent(jButton_search, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_shoeProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_showEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_showTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(jButton_emtybasket, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_pay, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_stuff, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, "card2");

        jButton_bag.setText("Tilbage");
        jButton_bag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_bagActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_stofLayout = new javax.swing.GroupLayout(jPanel_stof);
        jPanel_stof.setLayout(jPanel_stofLayout);
        jPanel_stofLayout.setHorizontalGroup(
            jPanel_stofLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 808, Short.MAX_VALUE)
        );
        jPanel_stofLayout.setVerticalGroup(
            jPanel_stofLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButton_calender.setText("Kalender");
        jButton_calender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_calenderActionPerformed(evt);
            }
        });

        jButton_closeregi.setText("Luk Kassen");
        jButton_closeregi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_closeregiActionPerformed(evt);
            }
        });

        jButton_statestik.setText("Statestik");
        jButton_statestik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_statestikActionPerformed(evt);
            }
        });

        jButton_lager.setText("Lager");
        jButton_lager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_lagerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_closeregi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addComponent(jButton_bag, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_calender, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_statestik, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_lager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_stof, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanel_stof, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jButton_calender, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jButton_statestik, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jButton_lager, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jButton_closeregi, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(262, 262, 262)
                .addComponent(jButton_bag, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, "card3");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_shoeProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_shoeProductActionPerformed
        createGroupPanel("Product");
    }//GEN-LAST:event_jButton_shoeProductActionPerformed

    private void jButton_showEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_showEventActionPerformed
        createGroupPanel("Event");
    }//GEN-LAST:event_jButton_showEventActionPerformed

    private void jButton_showTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_showTicketActionPerformed
        createGroupPanel("Ticket");
    }//GEN-LAST:event_jButton_showTicketActionPerformed

    private void jButton_bagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_bagActionPerformed
        jPanel1.setVisible(true);
        jPanel2.setVisible(false);
    }//GEN-LAST:event_jButton_bagActionPerformed

    private void jButton_stuffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_stuffActionPerformed
        jPanel1.setVisible(false);
        jPanel2.setVisible(true);
    }//GEN-LAST:event_jButton_stuffActionPerformed

    private void jButton_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_searchActionPerformed
        searchProduct(true);
    }//GEN-LAST:event_jButton_searchActionPerformed

    private void jButton_emtybasketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_emtybasketActionPerformed
        saleHandler.clearSaleinventori();
    }//GEN-LAST:event_jButton_emtybasketActionPerformed

    private void jButton_payActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_payActionPerformed
        buyStuff = new BuyStuff(saleHandler.getSale(), moneyHandler, listeners, saleHandler, storeHandler, storeController, printHandler);
        buyStuff.setBounds(0, 0, 400, 500);
        buyStuff.setLocation(150, 50);
        buyStuff.setVisible(true);
    }//GEN-LAST:event_jButton_payActionPerformed

    private void jButton_calenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_calenderActionPerformed
        calenderPanel = new CalenderPanel(saleHandler.getEventlineListFromData(), storeHandler, listeners);
        jPanel_stof.removeAll();
        calenderPanel.setLocation(5, 7);
        jPanel_stof.add(calenderPanel);
        calenderPanel.setVisible(true);

        jPanel_stof.repaint();
        jPanel_stof.revalidate();
    }//GEN-LAST:event_jButton_calenderActionPerformed

    private void jButton_closeregiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_closeregiActionPerformed
        jPanel_stof.removeAll();
        Checkout ch = new Checkout(moneyHandler, storeHandler.getEmployee(), printHandler);
        ch.setLocation(5, 7);
        jPanel_stof.add(ch);
        jPanel_stof.revalidate();
        jPanel_stof.repaint();
    }//GEN-LAST:event_jButton_closeregiActionPerformed

    private void jButton_statestikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_statestikActionPerformed
        jPanel_stof.removeAll();
        StatestikPanel statestikPanel = new StatestikPanel(storeHandler, saleHandler, UtilHandler);
        statestikPanel.setLocation(5, 7);
        jPanel_stof.add(statestikPanel);
        jPanel_stof.revalidate();
        jPanel_stof.repaint();
    }//GEN-LAST:event_jButton_statestikActionPerformed

    private void jButton_lagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_lagerActionPerformed
        jPanel_stof.removeAll();
        StockVeiw st = new StockVeiw(UtilHandler);
        st.setLocation(5, 7);
        jPanel_stof.add(st);
        jPanel_stof.revalidate();
        jPanel_stof.repaint();
    }//GEN-LAST:event_jButton_lagerActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_bag;
    private javax.swing.JButton jButton_calender;
    private javax.swing.JButton jButton_closeregi;
    private javax.swing.JButton jButton_emtybasket;
    private javax.swing.JButton jButton_lager;
    private javax.swing.JButton jButton_pay;
    private javax.swing.JButton jButton_search;
    private javax.swing.JButton jButton_shoeProduct;
    private javax.swing.JButton jButton_showEvent;
    private javax.swing.JButton jButton_showTicket;
    private javax.swing.JButton jButton_statestik;
    private javax.swing.JButton jButton_stuff;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel_group;
    private javax.swing.JPanel jPanel_kurv;
    private javax.swing.JPanel jPanel_product;
    private javax.swing.JPanel jPanel_stof;
    private javax.swing.JPanel jPanel_user;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {

            case "ShowType Change":
                createShowType(false);
                break;
            case "Employee Login":
                setCashRegistre(true);
                setLoginPanel();
                break;
            case "Cash Registre":
                setCashRegistre(true);
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
                setCashRegistre(true);
                fillBasket();
                updateQuantitis();
                break;
            case "Log Down":
                LogOut();
                break;
            default:

        }
    }
}
