/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory_system;

import java.util.ArrayList;
import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author imran
 */
public class saleItemX1 extends javax.swing.JFrame {

    /**
     * Creates new form saleItemX1
     */
    public saleItemX1() {
        initComponents();
        addCat();
        setDate();
        setInvoiceNumber();
    }
    
    private void setDate() {
        SimpleDateFormat sDate = new SimpleDateFormat("dd-MM-yyyy");
        Calendar gCal = Calendar.getInstance();
        gCal.set(year, month, day);
        //viewStudent.setingText(txtField, sDate.format(gCal.getTime()));
        txtDate.setText(sDate.format(gCal.getTime()));
    }
    
    ArrayList<JButton> buttons = new ArrayList<>();
    ArrayList<String> bNames = new ArrayList<>();
    ArrayList<String> bcat_id = new ArrayList<>();
    ArrayList<ArrayList<Object>> toDecrease = new ArrayList<>();
    ArrayList<ArrayList<Object>> items = new ArrayList<>();
    
    int sn = 0;
    int month = Calendar.getInstance().get(Calendar.MONTH);
    int year = Calendar.getInstance().get(Calendar.YEAR);
    int day = Calendar.getInstance().get(Calendar.DATE);
    int profit = 0;
    int actualTotal = 0;
    
    private void addCat() {
        try {
            
            try (Connection con = databaseCon.getConn()) {
                String sql = "SELECT * From categories";
                PreparedStatement st = con.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    bNames.add(rs.getString("name"));
                    bcat_id.add(rs.getString("cat_id"));
                }
                con.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        int i = 0;
        while (i < bcat_id.size()) {
            x1 = new JButton(bNames.get(i));
            int k = i;
            x1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    //JOptionPane.showMessageDialog(null, bNames.get(k)+ " ::::: " + bcat_id.get(k));
                    int rows = tableMain.getRowCount();
                    itemAdd ia = new itemAdd();
                    ArrayList<Object> iii = ia.adding(bcat_id.get(k), rows);
                    
                    if (!iii.isEmpty()) {
//                        for (int i = 0; i < iii.size(); i++) {
//                            JOptionPane.showMessageDialog(null, iii.get(i));
//                        }
                        ArrayList<Object> dec = new ArrayList<>();
                        dec.add(iii.get(0));
                        dec.add(iii.get(1));
//                        for (int i = 0; i < dec.size(); i++) {
//                            JOptionPane.showMessageDialog(null, dec.get(i));
//                        }
                        toDecrease.add(dec);
                        ArrayList<Object> itemDB = getItembyID((String) iii.get(0));
//                        for (int i = 0; i < itemDB.size(); i++) {
//                            JOptionPane.showMessageDialog(null, itemDB.get(i));
//                        }
                        actualTotal = actualTotal + Integer.parseInt((String) itemDB.get(5));
                        ArrayList<Object> item = new ArrayList<>();
                        
                        item.add(itemDB.get(1));
                        item.add(iii.get(1));
                        item.add(iii.get(2));
                        item.add((Integer) iii.get(1) * (Integer) iii.get(2));
                        
                        items.add(item);
                        buildTable(items);
                        
                    }
                }
            });
            toolX2.add(x1);
            toolX2.add(new javax.swing.JToolBar.Separator());
            
            i++;
        }
        
    }
    
    private void buildTable(ArrayList<ArrayList<Object>> itemss) {
        int row = 1;
        DefaultTableModel model = (DefaultTableModel) tableMain.getModel();
        model.setRowCount(0);
        for (ArrayList<Object> itemx : itemss) {
            Object[] itemxs = new Object[itemx.size() + 1];
            itemxs[0] = row;
            itemxs[1] = itemx.get(0);
            itemxs[2] = itemx.get(1);
            itemxs[3] = itemx.get(2);
            itemxs[4] = itemx.get(3);
            //           itemxs[5] = itemx.get(4);

            model.addRow(itemxs);
            columnSum();
            row++;
        }
    }
    
    private ArrayList<Object> getItembyID(String ID) {
        ArrayList<Object> list = new ArrayList<>();
        try {
            
            try (Connection con = databaseCon.getConn()) {
                String sql = "SELECT * From invitems where item_id = ?";
                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, ID);
                ResultSet rs = st.executeQuery();
                
                while (rs.next()) {
                    int column = rs.getMetaData().getColumnCount();
                    for (int i = 1; i <= column; i++) {
                        list.add(rs.getString(i));
                        //JOptionPane.showMessageDialog(null, "ok item data");
                    }
                }
                con.close();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }
    
    private void setInvoiceNumber() {
        try {
            
            try (Connection con = databaseCon.getConn()) {
                String sql = "SELECT MAX(inv_id)+1 From invoice";
                PreparedStatement st = con.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    int i = rs.getInt(1);
                    if (i == 0) {
                        txtInvoiceNumber.setText("1");
                    } else {
                        txtInvoiceNumber.setText(rs.getString(1));
                    }
                    
                }
                con.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void columnSum() {
        int total = 0;
        for (int i = 0; i < tableMain.getRowCount(); i++) {
            
            int Amount = Integer.parseInt(tableMain.getValueAt(i, 4) + "");
            total = Amount + total;
            
        }
        totalAmount.setText(Integer.toString(total));
        int b = total - Integer.parseInt(txtDiscount.getText());
        lBalance.setText(Integer.toString(b));
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
        toolX2 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        txtInvoiceNumber = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCustomer = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMain = new javax.swing.JTable();
        btnDone = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        totalAmount = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lBalance = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtDiscount = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        toolX2.setFloatable(false);
        toolX2.setToolTipText("");
        toolX2.setBorderPainted(false);

        jLabel1.setText("Invoice #");

        txtInvoiceNumber.setText("0");

        jLabel2.setText("Customer :");

        txtCustomer.setText("Name");

        jLabel3.setText("Date:");

        txtDate.setText("28-02-2016");

        jLabel4.setText("jLabel1");

        jTextField4.setText("jTextField1");

        tableMain.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SN", "Item", "Qty", "Unit Price", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableMain.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableMain.setGridColor(new java.awt.Color(0, 0, 0));
        tableMain.setOpaque(false);
        tableMain.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableMain.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableMain);
        if (tableMain.getColumnModel().getColumnCount() > 0) {
            tableMain.getColumnModel().getColumn(0).setResizable(false);
            tableMain.getColumnModel().getColumn(0).setPreferredWidth(40);
            tableMain.getColumnModel().getColumn(1).setPreferredWidth(400);
            tableMain.getColumnModel().getColumn(2).setResizable(false);
            tableMain.getColumnModel().getColumn(2).setPreferredWidth(70);
            tableMain.getColumnModel().getColumn(3).setResizable(false);
            tableMain.getColumnModel().getColumn(3).setPreferredWidth(70);
            tableMain.getColumnModel().getColumn(4).setResizable(false);
            tableMain.getColumnModel().getColumn(4).setPreferredWidth(70);
        }

        btnDone.setText("Done");
        btnDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoneActionPerformed(evt);
            }
        });

        jLabel5.setText("Total");

        totalAmount.setText("0");

        jLabel8.setText("Discount");

        lBalance.setText("0");

        jLabel10.setText("Balance");

        txtDiscount.setText("0");
        txtDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiscountActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jButton6.setText("...");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton1.setText("test");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Remove Item");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolX2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtInvoiceNumber))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(103, 103, 103)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDone, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDiscount)
                    .addComponent(totalAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtInvoiceNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toolX2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDone)
                    .addComponent(jLabel5)
                    .addComponent(totalAmount)
                    .addComponent(btnCancel)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lBalance))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiscountActionPerformed
        columnSum();
    }//GEN-LAST:event_txtDiscountActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        txtDate.setText(getDate(txtDate.getText()));
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoneActionPerformed
        saveInvoice();
        for (ArrayList<Object> itemx : items) {
            
            saveInvoiceItems(itemx);
        }
        
        decItem();
    }//GEN-LAST:event_btnDoneActionPerformed
    private void decItem() {
        for (ArrayList<Object> e : toDecrease) {
            String sql = "UPDATE `myinventory`.`invitems` SET qty = qty - ? WHERE item_id = ?";
            try {
                Connection con = databaseCon.getConn();
                PreparedStatement st = con.prepareStatement(sql);
                //int pk = Integer.parseInt(txtItemQty.getText()) + Integer.parseInt(curQty);
                st.setInt(1, (Integer) e.get(1));
                st.setString(2, (String) e.get(0));
                st.executeUpdate();
                con.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
    
    private void saveInvoiceItems(ArrayList<Object> itemsx) {
        String itemName = (String) itemsx.get(0);
        int itemQty = (Integer) itemsx.get(1);
        int itemPrice = (Integer) itemsx.get(2);
        try {
            SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date invoiceDate = formatDate.parse(txtDate.getText());
            java.sql.Date sqlDate = new java.sql.Date(invoiceDate.getTime());
            String sql = "INSERT INTO `myinventory`.`invoicedetails` ( `inv_id`, `item`, `qty`, `unitPrice`, `saleDate`) VALUES (?, ?, ?,?, ?)";
            try {
                Connection con = databaseCon.getConn();
                PreparedStatement st = con.prepareStatement(sql);
                st.setInt(1, Integer.parseInt(txtInvoiceNumber.getText()));
                st.setString(2, itemName);
                st.setInt(3, itemQty);
                st.setInt(4, itemPrice);
                st.setDate(5, sqlDate);
                st.executeUpdate();
                con.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } catch (ParseException ex) {
            Logger.getLogger(saleItemX1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void saveInvoice() {
        try {
            SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date invoiceDate = formatDate.parse(txtDate.getText());
            java.sql.Date sqlDate = new java.sql.Date(invoiceDate.getTime());
            String sql = "INSERT INTO `myinventory`.`invoice` ( `inv_id`, `customer`, `saledate`, `totalAmount`, `Discount` ,`Profit`) VALUES (?, ?, ?,?, ?,?)";
            try {
                Connection con = databaseCon.getConn();
                PreparedStatement st = con.prepareStatement(sql);
                st.setInt(1, Integer.parseInt(txtInvoiceNumber.getText()));
                st.setString(2, txtCustomer.getText());
                st.setDate(3, sqlDate);
                st.setInt(4, Integer.parseInt(totalAmount.getText()));
                st.setInt(5, Integer.parseInt(txtDiscount.getText()));
                st.setInt(6, profit);
                st.executeUpdate();
                con.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } catch (ParseException ex) {
            Logger.getLogger(saleItemX1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JOptionPane.showMessageDialog(null, actualTotal);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JOptionPane.showMessageDialog(null, tableMain.getValueAt(tableMain.getSelectedRow(), 0).toString());
        int rIndex = (int) tableMain.getValueAt(tableMain.getSelectedRow(), 0);
        items.remove(rIndex - 1);
        buildTable(items);
    }//GEN-LAST:event_jButton2ActionPerformed
    private String getDate(String curDate) {
        String rDate = curDate;
        mDatePicker dp = new mDatePicker(null);
        String gDate = dp.getDate();
        if ("".equals(gDate)) {
            rDate = curDate;
        } else {
            rDate = gDate;
        }
        return rDate;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(saleItemX1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(saleItemX1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(saleItemX1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(saleItemX1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new saleItemX1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDone;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel lBalance;
    private javax.swing.JTable tableMain;
    private javax.swing.JToolBar toolX2;
    private javax.swing.JLabel totalAmount;
    private javax.swing.JTextField txtCustomer;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtDiscount;
    private javax.swing.JTextField txtInvoiceNumber;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JButton x1;
}
