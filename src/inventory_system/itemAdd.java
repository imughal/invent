/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory_system;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author imran
 */
public class itemAdd extends javax.swing.JDialog {

    /**
     * Creates new form itemAdd
     */
    public itemAdd() {
        super(new javax.swing.JFrame(), true);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbItem = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tAvail = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        SpinQty = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        tAmount = new javax.swing.JLabel();
        unitPrice = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cmbItem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbItemItemStateChanged(evt);
            }
        });

        jLabel1.setText("item");

        jLabel2.setText("Total Availale:");

        tAvail.setText("jLabel3");

        jLabel4.setText("Quantity");

        SpinQty.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        SpinQty.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SpinQtyStateChanged(evt);
            }
        });

        jLabel5.setText("Unit Price");

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setText("Total Amount:");

        tAmount.setText("0");

        unitPrice.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        unitPrice.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                unitPriceStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbItem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SpinQty, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                            .addComponent(unitPrice)))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tAvail))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(tAmount))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(tAvail))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(SpinQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(tAmount)
                    .addComponent(unitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    int row = 0;

    public ArrayList<Object> adding(String cat_id, int rows) {
        loading(cat_id);
        row = rows + 1;
        this.setVisible(true);
        return rList;
    }

    ArrayList<String> qtys = new ArrayList<>();
    ArrayList<String> item_ids = new ArrayList<>();
    ArrayList<String> itemName = new ArrayList<>();
    ArrayList<Object> rList = new ArrayList<>();

    private void loading(String cat_id) {
        try {

            try (Connection con = databaseCon.getConn()) {
                String sql = "SELECT * From invitems where cat_id = ?";
                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, cat_id);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {

                    qtys.add(rs.getString("qty"));
                    item_ids.add(rs.getString("item_id"));
                    //cmbItem.addItem(rs.getString("item"));
                    itemName.add(rs.getString("item"));
                }
                con.close();
            }
            for (String p : itemName) {
                cmbItem.addItem(p);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }


    private void SpinQtyStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpinQtyStateChanged
        int item_qty = (Integer) SpinQty.getValue();
        int item_price = (Integer) unitPrice.getValue();
        String total_amount = Integer.toString(item_price * item_qty);
        tAmount.setText(total_amount);
    }//GEN-LAST:event_SpinQtyStateChanged

    private void unitPriceStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_unitPriceStateChanged
        int item_qty = (Integer) SpinQty.getValue();
        int item_price = (Integer) unitPrice.getValue();
        String total_amount = Integer.toString(item_price * item_qty);
        tAmount.setText(total_amount);
    }//GEN-LAST:event_unitPriceStateChanged

    private void cmbItemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbItemItemStateChanged
        //int qtyo = qtys.indexOf(cmbItem.getSelectedIndex());
        tAvail.setText(qtys.get(cmbItem.getSelectedIndex()));
//        getSelectedData(item_ids.get(cmbItem.getSelectedIndex()));
        //JOptionPane.showMessageDialog(null, cmbItem.getSelectedIndex());
    }//GEN-LAST:event_cmbItemItemStateChanged
private void getSelectedData(String id){
            try {

            try (Connection con = databaseCon.getConn()) {
                String sql = "SELECT * From invitems where item_id = ?";
                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, id);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {


                }
                con.close();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
}
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int avail = Integer.parseInt(tAvail.getText());
        if ((int) SpinQty.getValue() > avail) {
            JOptionPane.showMessageDialog(null, "Error, Quantity Out of Stock");
        } else {

//            rList.add(row);
//            rList.add((String) cmbItem.getSelectedItem());
//            rList.add(SpinQty.getValue().toString());
//            rList.add(unitPrice.getValue().toString());
//            rList.add(tAmount.getText());

            rList.add(item_ids.get(cmbItem.getSelectedIndex()));
            rList.add((Integer) SpinQty.getValue());
            rList.add((Integer) unitPrice.getValue());

            this.setVisible(false);
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(itemAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(itemAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(itemAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(itemAdd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                itemAdd dialog = new itemAdd();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner SpinQty;
    private javax.swing.JComboBox<String> cmbItem;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel tAmount;
    private javax.swing.JLabel tAvail;
    private javax.swing.JSpinner unitPrice;
    // End of variables declaration//GEN-END:variables
}
