/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
//this is one of the page in manager
package OOP;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class Manager_Change_Rates extends javax.swing.JFrame {
    
    private DefaultTableModel model = new DefaultTableModel();
    private String columnNames[] = {"Rates","Value"};

    public Manager_Change_Rates() {
        try{
        model.setColumnIdentifiers(columnNames);
        FileReader fr = new FileReader("src/main/java/OOP/rates.txt");
        BufferedReader br = new BufferedReader(fr);
        
        String line = null;
        
        while ((line = br.readLine()) != null){
            String values[] = line.split(", ");
            model.addRow(values);
        }
        br.close();
        fr.close();
        
        } catch(Exception e){
            
        }
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        RatesTable = new javax.swing.JTable();
        Update = new javax.swing.JButton();
        Exit = new javax.swing.JButton();
        Add = new javax.swing.JButton();
        Delete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Rates");

        RatesTable.setModel(model);
        jScrollPane1.setViewportView(RatesTable);

        Update.setText("Update");
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });

        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });

        Add.setText("Add");
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        Delete.setText("Delete");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(Update)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Add)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Delete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Exit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(226, 226, 226))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Update)
                    .addComponent(Exit)
                    .addComponent(Add)
                    .addComponent(Delete))
                .addContainerGap(134, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        // TODO add your handling code here:
        int selectedRow = RatesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a rate to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String rateName = (String) model.getValueAt(selectedRow, 0);
        String rateValue = (String) model.getValueAt(selectedRow, 1);

        String newRateName = JOptionPane.showInputDialog(this, "Enter new rate name:", rateName);
        String newRateValue = JOptionPane.showInputDialog(this, "Enter new rate value:", rateValue);

        if (newRateName == null || newRateName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Rate name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (newRateValue == null || newRateValue.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Rate value cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            if (Validator.isRateNameRegistered(newRateName, "src/main/java/OOP/rates.txt")&& !newRateName.equals(rateName)) {
                JOptionPane.showMessageDialog(this, "Name is already exist.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "An error occurred while checking the rates: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            Double.parseDouble(newRateValue);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Rate value must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        model.setValueAt(newRateName, selectedRow, 0);
        model.setValueAt(newRateValue, selectedRow, 1);

        saveData();
        JOptionPane.showMessageDialog(this, "Rate updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    
    }//GEN-LAST:event_UpdateActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        // TODO add your handling code here:
        Manager mg = new Manager();
        mg.setVisible(true);
        this. dispose();
    }//GEN-LAST:event_ExitActionPerformed

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        // TODO add your handling code here:
        String newRateName = JOptionPane.showInputDialog(this, "Enter new rate name:");
        String newRateValue = JOptionPane.showInputDialog(this, "Enter new rate value:");

        if (newRateName == null || newRateName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Rate name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (newRateValue == null || newRateValue.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Rate value cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            if (Validator.isRateNameRegistered(newRateName, "src/main/java/OOP/rates.txt")) {
                JOptionPane.showMessageDialog(this, "Name is already exist.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "An error occurred while checking the rate name: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            Double.parseDouble(newRateValue);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Rate value must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        model.addRow(new Object[]{newRateName, newRateValue});

        saveData();
        JOptionPane.showMessageDialog(this, "New rate added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_AddActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
        int selectedRow = RatesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a rate to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this rate?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            model.removeRow(selectedRow);
            saveData();
            JOptionPane.showMessageDialog(this, "Rate deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_DeleteActionPerformed

    private void saveData () {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/OOP/rates.txt"))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                writer.write(model.getValueAt(i, 0) + ", " + model.getValueAt(i, 1));
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "An error occurred while saving the data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manager_Change_Rates().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JButton Delete;
    private javax.swing.JButton Exit;
    private javax.swing.JTable RatesTable;
    private javax.swing.JButton Update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
