/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utillity.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import utillity.DateFormatTools;
import utillity.Line;

/**
 *
 * @author MarcoPc
 */
public class StatestikView extends javax.swing.JPanel {

    private ArrayList<Line> lineList;
    private String date;
    private DateFormatTools dateTools;

    /**
     * Creates new form StatestikView
     */
    public StatestikView(ArrayList<Line> lineList, String date) throws ParseException {
        this.lineList = lineList;
        this.date = date;
        dateTools = DateFormatTools.getDateFormat();

        initComponents();
        setSize(700, 400);
        fillStat();

    }

    public void fillStat() throws ParseException {
        Calendar fromDate = dateTools.getShortDate(date);
        Calendar fromDate2 = dateTools.getShortDate(date);
        Calendar toDate = dateTools.getEndDateFromString(date);

        toDate = dateTools.getNextday(toDate, 7);
        SimpleDateFormat format = new SimpleDateFormat("EEEE dd MMM yyyy");

        jLabel_fra.setText("Fra: " + format.format(fromDate.getTime()));
        jLabel_to.setText("Til: " + format.format(toDate.getTime()));
        SimpleDateFormat format4 = new SimpleDateFormat("EEEE");
        int count = 6;
        for (int i = 0; i < count; i++) {
            
            switch (i) {
                case 0:
                    Calendar isDate = dateTools.getNextday(fromDate2,0);
                    jTextField_0.setText(" " + format4.format(isDate.getTime()));
                    break;
                case 1:
                    isDate = dateTools.getNextday(fromDate2, 1);
                    jTextField_1.setText(" " + format4.format(isDate.getTime()));
                    break;
                case 2:
                    isDate = dateTools.getNextday(fromDate2, 1);
                    jTextField_2.setText(" "+format4.format(isDate.getTime()));
                    break;
                case 3:
                    isDate = dateTools.getNextday(fromDate2, 1);
                    jTextField_3.setText(" " + format4.format(isDate.getTime()));
                    break;
                case 4:
                    isDate = dateTools.getNextday(fromDate2, 1);
                    jTextField_4.setText(" " + format4.format(isDate.getTime()));
                    break;
                case 5:
                    isDate = dateTools.getNextday(fromDate2, 1);
                    jTextField_5.setText(" " + format4.format(isDate.getTime()));
                    break;
            }
            count++;
        }
        
        
        
        for (Line line : lineList) {
            
           
//            
            

            if (line.getText().equals("Voksne")) {

            }
            
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

        jTextField_0 = new javax.swing.JTextField();
        jTextField_1 = new javax.swing.JTextField();
        jTextField_2 = new javax.swing.JTextField();
        jTextField_3 = new javax.swing.JTextField();
        jTextField_4 = new javax.swing.JTextField();
        jTextField_5 = new javax.swing.JTextField();
        jTextField_AduWed = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField_AduThu = new javax.swing.JTextField();
        jTextField_AduFri = new javax.swing.JTextField();
        jTextField_AduSat = new javax.swing.JTextField();
        jTextField_AduSun = new javax.swing.JTextField();
        jTextField_AduTue = new javax.swing.JTextField();
        jTextField_KidWed = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField_KidThu = new javax.swing.JTextField();
        jTextField_KidFri = new javax.swing.JTextField();
        jTextField_KidSat = new javax.swing.JTextField();
        jTextField_KidSun = new javax.swing.JTextField();
        jTextField_KidTue = new javax.swing.JTextField();
        jTextField_EvenWed = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField_EvenThu = new javax.swing.JTextField();
        jTextField_EvenFri = new javax.swing.JTextField();
        jTextField_EvenSat = new javax.swing.JTextField();
        jTextField_EvenSun = new javax.swing.JTextField();
        jTextField_EvenTue = new javax.swing.JTextField();
        jTextField_FreeWed = new javax.swing.JTextField();
        jTextField30 = new javax.swing.JTextField();
        jTextField_FreeTHu = new javax.swing.JTextField();
        jTextField_FreeFri = new javax.swing.JTextField();
        jTextField_FreeSat = new javax.swing.JTextField();
        jTextField_FreeSun = new javax.swing.JTextField();
        jTextField_FreeTue = new javax.swing.JTextField();
        jTextField_SumWed = new javax.swing.JTextField();
        jTextField37 = new javax.swing.JTextField();
        jTextField_SumThu = new javax.swing.JTextField();
        jTextField_SumFri = new javax.swing.JTextField();
        jTextField_SumSat = new javax.swing.JTextField();
        jTextField_SumSun = new javax.swing.JTextField();
        jTextField_SumTue = new javax.swing.JTextField();
        jLabel_fra = new javax.swing.JLabel();
        jLabel_to = new javax.swing.JLabel();

        jTextField_0.setEditable(false);
        jTextField_0.setText("      Tirsdag:");
        jTextField_0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_1.setEditable(false);
        jTextField_1.setText("      Onsdag:");
        jTextField_1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_2.setEditable(false);
        jTextField_2.setText("      Torsdag:");
        jTextField_2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_3.setEditable(false);
        jTextField_3.setText("      Fredag:");
        jTextField_3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_4.setEditable(false);
        jTextField_4.setText("      Lørdag:");
        jTextField_4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_5.setEditable(false);
        jTextField_5.setText("      Søndag:");
        jTextField_5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextField_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_5ActionPerformed(evt);
            }
        });

        jTextField_AduWed.setEditable(false);
        jTextField_AduWed.setText("jTextField1");
        jTextField_AduWed.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField9.setEditable(false);
        jTextField9.setText("      Voksne Entre:");
        jTextField9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_AduThu.setEditable(false);
        jTextField_AduThu.setText("jTextField1");
        jTextField_AduThu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_AduFri.setEditable(false);
        jTextField_AduFri.setText("jTextField1");
        jTextField_AduFri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_AduSat.setEditable(false);
        jTextField_AduSat.setText("jTextField1");
        jTextField_AduSat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_AduSun.setEditable(false);
        jTextField_AduSun.setText("jTextField1");
        jTextField_AduSun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_AduTue.setEditable(false);
        jTextField_AduTue.setText("jTextField1");
        jTextField_AduTue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_KidWed.setEditable(false);
        jTextField_KidWed.setText("jTextField1");
        jTextField_KidWed.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField16.setEditable(false);
        jTextField16.setText("      Børn Entre:");
        jTextField16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_KidThu.setEditable(false);
        jTextField_KidThu.setText("jTextField1");
        jTextField_KidThu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_KidFri.setEditable(false);
        jTextField_KidFri.setText("jTextField1");
        jTextField_KidFri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_KidSat.setEditable(false);
        jTextField_KidSat.setText("jTextField1");
        jTextField_KidSat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_KidSun.setEditable(false);
        jTextField_KidSun.setText("jTextField1");
        jTextField_KidSun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_KidTue.setEditable(false);
        jTextField_KidTue.setText("jTextField1");
        jTextField_KidTue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_EvenWed.setEditable(false);
        jTextField_EvenWed.setText("jTextField1");
        jTextField_EvenWed.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField23.setEditable(false);
        jTextField23.setText("      Events:");
        jTextField23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_EvenThu.setEditable(false);
        jTextField_EvenThu.setText("jTextField1");
        jTextField_EvenThu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_EvenFri.setEditable(false);
        jTextField_EvenFri.setText("jTextField1");
        jTextField_EvenFri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_EvenSat.setEditable(false);
        jTextField_EvenSat.setText("jTextField1");
        jTextField_EvenSat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_EvenSun.setEditable(false);
        jTextField_EvenSun.setText("jTextField1");
        jTextField_EvenSun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_EvenTue.setEditable(false);
        jTextField_EvenTue.setText("jTextField1");
        jTextField_EvenTue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_FreeWed.setEditable(false);
        jTextField_FreeWed.setText("jTextField1");
        jTextField_FreeWed.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField30.setEditable(false);
        jTextField30.setText("      Gratister:");
        jTextField30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_FreeTHu.setEditable(false);
        jTextField_FreeTHu.setText("jTextField1");
        jTextField_FreeTHu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_FreeFri.setEditable(false);
        jTextField_FreeFri.setText("jTextField1");
        jTextField_FreeFri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_FreeSat.setEditable(false);
        jTextField_FreeSat.setText("jTextField1");
        jTextField_FreeSat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_FreeSun.setEditable(false);
        jTextField_FreeSun.setText("jTextField1");
        jTextField_FreeSun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_FreeTue.setEditable(false);
        jTextField_FreeTue.setText("jTextField1");
        jTextField_FreeTue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_SumWed.setEditable(false);
        jTextField_SumWed.setText("jTextField1");
        jTextField_SumWed.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField37.setEditable(false);
        jTextField37.setText("      Gæster I Alt");
        jTextField37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_SumThu.setEditable(false);
        jTextField_SumThu.setText("jTextField1");
        jTextField_SumThu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_SumFri.setEditable(false);
        jTextField_SumFri.setText("jTextField1");
        jTextField_SumFri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_SumSat.setEditable(false);
        jTextField_SumSat.setText("jTextField1");
        jTextField_SumSat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_SumSun.setEditable(false);
        jTextField_SumSun.setText("jTextField1");
        jTextField_SumSun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField_SumTue.setEditable(false);
        jTextField_SumTue.setText("jTextField1");
        jTextField_SumTue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel_fra.setText("jLabel1");

        jLabel_to.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTextField_SumTue, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTextField_SumWed, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTextField_SumThu, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTextField_SumFri, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTextField_SumSat, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTextField_SumSun, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField_EvenTue, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField_EvenWed, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField_EvenThu, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField_EvenFri, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField_EvenSat, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField_EvenSun, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField_KidTue, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField_KidWed, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField_KidThu, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField_KidFri, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField_KidSat, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField_KidSun, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel_fra, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                .addComponent(jLabel_to, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(10, 10, 10)
                            .addComponent(jTextField_0, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jTextField_1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jTextField_2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jTextField_3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jTextField_4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jTextField_5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jTextField_AduTue, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jTextField_AduWed, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jTextField_AduThu, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jTextField_AduFri, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jTextField_AduSat, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jTextField_AduSun, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jTextField_FreeTue, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jTextField_FreeWed, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jTextField_FreeTHu, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jTextField_FreeFri, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jTextField_FreeSat, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jTextField_FreeSun, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField_0, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                        .addComponent(jTextField_1)
                        .addComponent(jTextField_2)
                        .addComponent(jTextField_3)
                        .addComponent(jTextField_4)
                        .addComponent(jTextField_5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel_fra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_to)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField9)
                    .addComponent(jTextField_AduTue)
                    .addComponent(jTextField_AduWed)
                    .addComponent(jTextField_AduThu)
                    .addComponent(jTextField_AduFri)
                    .addComponent(jTextField_AduSat)
                    .addComponent(jTextField_AduSun, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField16)
                    .addComponent(jTextField_KidTue)
                    .addComponent(jTextField_KidWed)
                    .addComponent(jTextField_KidThu)
                    .addComponent(jTextField_KidFri)
                    .addComponent(jTextField_KidSat)
                    .addComponent(jTextField_KidSun, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField23)
                    .addComponent(jTextField_EvenTue)
                    .addComponent(jTextField_EvenWed)
                    .addComponent(jTextField_EvenThu)
                    .addComponent(jTextField_EvenFri)
                    .addComponent(jTextField_EvenSat)
                    .addComponent(jTextField_EvenSun, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField30)
                    .addComponent(jTextField_FreeTue)
                    .addComponent(jTextField_FreeWed)
                    .addComponent(jTextField_FreeTHu)
                    .addComponent(jTextField_FreeFri)
                    .addComponent(jTextField_FreeSat)
                    .addComponent(jTextField_FreeSun, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField37)
                    .addComponent(jTextField_SumTue)
                    .addComponent(jTextField_SumWed)
                    .addComponent(jTextField_SumThu)
                    .addComponent(jTextField_SumFri)
                    .addComponent(jTextField_SumSat)
                    .addComponent(jTextField_SumSun, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel_fra;
    private javax.swing.JLabel jLabel_to;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField jTextField_0;
    private javax.swing.JTextField jTextField_1;
    private javax.swing.JTextField jTextField_2;
    private javax.swing.JTextField jTextField_3;
    private javax.swing.JTextField jTextField_4;
    private javax.swing.JTextField jTextField_5;
    private javax.swing.JTextField jTextField_AduFri;
    private javax.swing.JTextField jTextField_AduSat;
    private javax.swing.JTextField jTextField_AduSun;
    private javax.swing.JTextField jTextField_AduThu;
    private javax.swing.JTextField jTextField_AduTue;
    private javax.swing.JTextField jTextField_AduWed;
    private javax.swing.JTextField jTextField_EvenFri;
    private javax.swing.JTextField jTextField_EvenSat;
    private javax.swing.JTextField jTextField_EvenSun;
    private javax.swing.JTextField jTextField_EvenThu;
    private javax.swing.JTextField jTextField_EvenTue;
    private javax.swing.JTextField jTextField_EvenWed;
    private javax.swing.JTextField jTextField_FreeFri;
    private javax.swing.JTextField jTextField_FreeSat;
    private javax.swing.JTextField jTextField_FreeSun;
    private javax.swing.JTextField jTextField_FreeTHu;
    private javax.swing.JTextField jTextField_FreeTue;
    private javax.swing.JTextField jTextField_FreeWed;
    private javax.swing.JTextField jTextField_KidFri;
    private javax.swing.JTextField jTextField_KidSat;
    private javax.swing.JTextField jTextField_KidSun;
    private javax.swing.JTextField jTextField_KidThu;
    private javax.swing.JTextField jTextField_KidTue;
    private javax.swing.JTextField jTextField_KidWed;
    private javax.swing.JTextField jTextField_SumFri;
    private javax.swing.JTextField jTextField_SumSat;
    private javax.swing.JTextField jTextField_SumSun;
    private javax.swing.JTextField jTextField_SumThu;
    private javax.swing.JTextField jTextField_SumTue;
    private javax.swing.JTextField jTextField_SumWed;
    // End of variables declaration//GEN-END:variables
}
