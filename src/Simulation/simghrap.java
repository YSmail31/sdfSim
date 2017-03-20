package Simulation;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import sdfreader.Reader;

public class simghrap extends javax.swing.JFrame {

    public simghrap() {
        initComponents();
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static class MyCellRenderer extends JLabel implements TableCellRenderer {

        public MyCellRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText("P" + Integer.toString(row));
            setOpaque(true);
            setBackground(Color.gray);
            return this;
        }
    }

    public static class MyCellRenderer2 extends JLabel implements TableCellRenderer {

        public MyCellRenderer2() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setOpaque(true);
            setBackground(Color.CYAN);
            return this;
        }
    }

    public static String[][] mat = new String[256][256];

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable() {

            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return true;
            }
        };
        jLabel17 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 0)));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField2.setText("                     ");
        jLabel2.setText("Default_Paquets_Size");
        jLabel3.setText("Default_token_size");
        jLabel4.setText("ALGORITHME_PLACEMENT");
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "FF", "NN_MANHATAN", "BN_MANHATAN", "NN_GBHD", "BN_GBHD" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jTextField3.setText("                     ");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jLabel6.setText("NB_APP");
        jLabel7.setText("Temp_envoi_paquet");
        jTextField5.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jLabel8.setText("Energie_envoi_paquet");
        jTextField6.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jLabel18.setText("(octet)");
        jLabel19.setText("(ms)");
        jLabel20.setText("(uJ)");
        jLabel23.setText("(octet)");
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel8).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabel20).addGap(10, 10, 10).addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel7).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabel19).addGap(12, 12, 12).addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel18).addGap(8, 8, 8).addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))).addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel6).addComponent(jLabel4).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel3).addGap(18, 18, 18).addComponent(jLabel23))).addGap(53, 53, 53).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel2).addComponent(jLabel6).addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel18)).addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel7).addComponent(jLabel19)).addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel3).addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel23))).addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel4).addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel8).addComponent(jLabel20)).addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Parametres Logiciel");
        jButton2.setText("start");
        jButton2.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton2ActionPerformed(evt);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });
        jLabel9.setBackground(new java.awt.Color(204, 204, 204));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel9.setForeground(new java.awt.Color(0, 0, 153));
        jLabel9.setText("Parametres Materiel");
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 0)));
        jLabel5.setText("PE TYPE 0");
        jLabel10.setText("PE TYPE 1");
        jLabel11.setText("PE TYPE 2");
        jLabel12.setText("PE TYPE 3");
        jLabel13.setText("ENERGIE");
        jLabel14.setText("FREQUENCE");
        jTextField4.setText("20");
        jTextField4.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jTextField7.setText("15");
        jTextField7.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        jTextField8.setText("20");
        jTextField8.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jTextField9.setText("20");
        jTextField9.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });
        jTextField10.setText("20");
        jTextField10.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });
        jTextField12.setText("20");
        jTextField12.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });
        jTextField13.setText("20");
        jTextField13.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });
        jTextField14.setText("20");
        jTextField14.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });
        jLabel15.setText("Taille_lien");
        jTextField15.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField15ActionPerformed(evt);
            }
        });
        jLabel16.setText("Taille_cluster");
        jTextField16.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 255), new java.awt.Color(204, 204, 255), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jTable2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTable2.setForeground(new java.awt.Color(255, 0, 51));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] {}));
        jTable2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable2.setName("");
        jTable2.setRowHeight(23);
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(50);
        }
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE));
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE));
        jLabel17.setText("Taille_NOC");
        jTextField17.setText("                     ");
        jTextField17.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });
        jTextField17.addKeyListener(new java.awt.event.KeyAdapter() {

            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField17KeyTyped(evt);
            }
        });
        jLabel21.setText("(uJ)");
        jLabel22.setText("(mips)");
        jLabel24.setText("(octet)");
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addComponent(jLabel12).addGap(18, 18, 18).addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(jPanel2Layout.createSequentialGroup().addComponent(jLabel11).addGap(18, 18, 18).addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup().addComponent(jLabel10).addGap(18, 18, 18).addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup().addComponent(jLabel5).addGap(18, 18, 18).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addComponent(jLabel13).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel21)).addComponent(jTextField4)))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addComponent(jLabel14).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel22)).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jTextField7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))))).addGroup(jPanel2Layout.createSequentialGroup().addGap(20, 20, 20).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addComponent(jLabel17).addGap(52, 52, 52).addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(jPanel2Layout.createSequentialGroup().addComponent(jLabel15).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel24).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(jPanel2Layout.createSequentialGroup().addComponent(jLabel16).addGap(18, 18, 18).addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(83, 83, 83)));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGap(17, 17, 17).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel13).addComponent(jLabel14).addComponent(jLabel21).addComponent(jLabel22)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel5).addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel10).addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel24)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(89, 89, 89)).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addContainerGap()));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(900, 900, 900).addComponent(jButton2)).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel1).addComponent(jLabel9)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jLabel1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel9).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButton2).addContainerGap()));
        pack();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) throws Throwable {
        if (isInteger(jTextField1.getText())) {
            ParametreSdf.Default_Paquets_Size = Integer.parseInt(jTextField1.getText());
        }
        if (isInteger(jTextField2.getText())) {
            ParametreSdf.Default_token_size = Integer.parseInt(jTextField2.getText());
        }
        ParametreSdf.ALGORITHME_PLACEMENT = jComboBox1.getSelectedItem().toString();
        if (isInteger(jTextField16.getText())) {
            StaticParametre.TAILLE_CLUSTER = Integer.parseInt(jTextField16.getText());
        }
        if (isInteger(jTextField5.getText())) {
            StaticParametre.Temps_envoie = Integer.parseInt(jTextField5.getText());
        }
        if (isInteger(jTextField6.getText())) {
            StaticParametre.Energie_envoi = Integer.parseInt(jTextField6.getText());
            StaticParametre.Energie_attente_envoie = StaticParametre.Energie_envoi / 10;
        }
        if (isInteger(jTextField3.getText())) {
            ParametreSdf.NB_APP = Integer.parseInt(jTextField3.getText());
        }
        if (isInteger(jTextField15.getText())) {
            ParametreSdf.Debit = Integer.parseInt(jTextField15.getText());
        }
        if (isInteger(jTextField17.getText())) {
            StaticParametre.LEGNHT_NOC = Integer.parseInt(jTextField17.getText());
        }
        if (isInteger(jTextField4.getText())) {
            ParametreSdf.ENERGIE_TYPE0 = Integer.parseInt(jTextField4.getText());
        }
        if (isInteger(jTextField7.getText())) {
            ParametreSdf.FREQUENCE_TYPE0 = Integer.parseInt(jTextField7.getText());
        }
        if (isInteger(jTextField8.getText())) {
            ParametreSdf.ENERGIE_TYPE1 = Integer.parseInt(jTextField8.getText());
        }
        if (isInteger(jTextField10.getText())) {
            ParametreSdf.FREQUENCE_TYPE1 = Integer.parseInt(jTextField10.getText());
        }
        if (isInteger(jTextField9.getText())) {
            ParametreSdf.ENERGIE_TYPE2 = Integer.parseInt(jTextField9.getText());
        }
        if (isInteger(jTextField13.getText())) {
            ParametreSdf.FREQUENCE_TYPE2 = Integer.parseInt(jTextField13.getText());
        }
        if (isInteger(jTextField12.getText())) {
            ParametreSdf.ENERGIE_TYPE3 = Integer.parseInt(jTextField12.getText());
        }
        if (isInteger(jTextField14.getText())) {
            ParametreSdf.FREQUENCE_TYPE3 = Integer.parseInt(jTextField14.getText());
        }
        if (StaticParametre.LEGNHT_NOC % StaticParametre.TAILLE_CLUSTER == 0) {
            StaticParametre.nbcluster = (StaticParametre.LEGNHT_NOC / StaticParametre.TAILLE_CLUSTER) * (StaticParametre.LEGNHT_NOC / StaticParametre.TAILLE_CLUSTER);
        } else {
            StaticParametre.nbcluster = (StaticParametre.LEGNHT_NOC / StaticParametre.TAILLE_CLUSTER + 1) * (StaticParametre.LEGNHT_NOC / StaticParametre.TAILLE_CLUSTER + 1);
        }
        FileWriter fichier = new FileWriter("value_lien.txt");
        String line = "";
        for (int i = 0; i < jTable2.getRowCount(); i++) {
            for (int j = 1; j < jTable2.getColumnCount(); j++) {
                if (isInteger((String) jTable2.getModel().getValueAt(i, j))) {
                    line = line + (String) jTable2.getModel().getValueAt(i, j) + ",";
                } else {
                    line = line + "0" + ",";
                }
            }
            line = line.substring(0, line.length() - 1);
            line = line + "\r\n";
        }
        fichier.write(line);
        fichier.close();
        SimulatorSdf.main(null);
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void jTextField17KeyTyped(java.awt.event.KeyEvent evt) {
        if (isInteger(jTextField17.getText())) {
            String[] NomColonne = new String[Integer.parseInt(jTextField17.getText()) + 1];
            Object[][] Donnees = new Object[Integer.parseInt(jTextField17.getText())][Integer.parseInt(jTextField17.getText()) + 1];
            NomColonne[0] = "  ";
            for (int i = 1; i < Math.max(0, Integer.parseInt(jTextField17.getText()) + 1); i++) {
                NomColonne[i] = "P" + Integer.toString(i - 1);
            }
            for (int i = 0; i < Integer.parseInt(jTextField17.getText()); i++) for (int j = 0; j < Integer.parseInt(jTextField17.getText()) + 1; j++) Donnees[i][j] = mat[i][j];
            DefaultTableModel m = new DefaultTableModel(Donnees, NomColonne);
            jTable2.setModel(m);
            jTable2.getColumnModel().getColumn(0).setCellRenderer(new MyCellRenderer());
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(simghrap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(simghrap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(simghrap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(simghrap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new simghrap().setVisible(true);
                jTextField1.setText(Integer.toString(ParametreSdf.Default_Paquets_Size));
                jTextField2.setText(Integer.toString(ParametreSdf.Default_token_size));
                jComboBox1.setSelectedItem("BN_GBHD");
                jTextField16.setText(Integer.toString(StaticParametre.TAILLE_CLUSTER));
                jTextField5.setText(Integer.toString(StaticParametre.Temps_envoie));
                jTextField6.setText(Integer.toString(StaticParametre.Energie_envoi));
                jTextField3.setText(Integer.toString(ParametreSdf.NB_APP));
                jTextField15.setText(Integer.toString(ParametreSdf.Debit));
                jTextField17.setText(Integer.toString(StaticParametre.LEGNHT_NOC));
                jTextField4.setText(Integer.toString(ParametreSdf.ENERGIE_TYPE0));
                jTextField7.setText(Integer.toString(ParametreSdf.FREQUENCE_TYPE0));
                jTextField8.setText(Integer.toString(ParametreSdf.ENERGIE_TYPE1));
                jTextField10.setText(Integer.toString(ParametreSdf.FREQUENCE_TYPE1));
                jTextField9.setText(Integer.toString(ParametreSdf.ENERGIE_TYPE2));
                jTextField13.setText(Integer.toString(ParametreSdf.FREQUENCE_TYPE2));
                jTextField12.setText(Integer.toString(ParametreSdf.ENERGIE_TYPE3));
                jTextField14.setText(Integer.toString(ParametreSdf.FREQUENCE_TYPE3));
                /*BufferedReader in = null;
                try {
                    in = new BufferedReader(new FileReader("lien.txt"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                int compteurLigne = 0;
                int id = 0;
                while (true) {
                    String line = null;
                    try {
                        line = in.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (line == null)
                        break;
                    String word;
                    StringTokenizer tok = new StringTokenizer(line, ",");
                    int count_word = tok.countTokens();
                    for (int i = 1; i < count_word + 1; i++) {
                        word = tok.nextToken();
                        mat[compteurLigne][i] = word;
                    }
                    compteurLigne++;
                }*/
                //ce que j'ai fait 
                Reader r = new Reader("h263decoder.xml");
                String[][] StrMat = r.convertString();
                mat = StrMat;
                StaticParametre.LEGNHT_NOC = r.getN();
                //fin 
                
                String[] NomColonne = new String[StaticParametre.LEGNHT_NOC + 1];
                NomColonne[0] = "  ";
                for (int i = 1; i < StaticParametre.LEGNHT_NOC + 1; i++) {
                    NomColonne[i] = "P" + Integer.toString(i - 1);
                }
                String[][] Donnees = new String[StaticParametre.LEGNHT_NOC][StaticParametre.LEGNHT_NOC + 1];
                for (int i = 0; i < StaticParametre.LEGNHT_NOC; i++) for (int j = 0; j < StaticParametre.LEGNHT_NOC + 1; j++) Donnees[i][j] = mat[i][j];
                DefaultTableModel model = new DefaultTableModel(Donnees, NomColonne);
                jTable2.setModel(model);
                jTable2.getColumnModel().getColumn(0).setCellRenderer(new MyCellRenderer());
            }
        });
    }

    private javax.swing.JButton jButton2;

    public static javax.swing.JComboBox jComboBox1;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JLabel jLabel10;

    private javax.swing.JLabel jLabel11;

    private javax.swing.JLabel jLabel12;

    private javax.swing.JLabel jLabel13;

    private javax.swing.JLabel jLabel14;

    private javax.swing.JLabel jLabel15;

    private javax.swing.JLabel jLabel16;

    private javax.swing.JLabel jLabel17;

    private javax.swing.JLabel jLabel18;

    private javax.swing.JLabel jLabel19;

    private javax.swing.JLabel jLabel2;

    private javax.swing.JLabel jLabel20;

    private javax.swing.JLabel jLabel21;

    private javax.swing.JLabel jLabel22;

    private javax.swing.JLabel jLabel23;

    private javax.swing.JLabel jLabel24;

    private javax.swing.JLabel jLabel3;

    private javax.swing.JLabel jLabel4;

    private javax.swing.JLabel jLabel5;

    private javax.swing.JLabel jLabel6;

    private javax.swing.JLabel jLabel7;

    private javax.swing.JLabel jLabel8;

    private javax.swing.JLabel jLabel9;

    private javax.swing.JPanel jPanel1;

    private javax.swing.JPanel jPanel2;

    private javax.swing.JPanel jPanel3;

    private javax.swing.JScrollPane jScrollPane2;

    public static javax.swing.JTable jTable2;

    public static javax.swing.JTextField jTextField1;

    public static javax.swing.JTextField jTextField10;

    public static javax.swing.JTextField jTextField12;

    public static javax.swing.JTextField jTextField13;

    public static javax.swing.JTextField jTextField14;

    public static javax.swing.JTextField jTextField15;

    public static javax.swing.JTextField jTextField16;

    public static javax.swing.JTextField jTextField17;

    public static javax.swing.JTextField jTextField2;

    public static javax.swing.JTextField jTextField3;

    public static javax.swing.JTextField jTextField4;

    public static javax.swing.JTextField jTextField5;

    public static javax.swing.JTextField jTextField6;

    public static javax.swing.JTextField jTextField7;

    public static javax.swing.JTextField jTextField8;

    public static javax.swing.JTextField jTextField9;
}
