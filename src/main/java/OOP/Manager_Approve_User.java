/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package OOP;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Justin Yong
 */
public class Manager_Approve_User extends javax.swing.JFrame {
    
    private List<String> passwords;
    private DefaultTableModel model;
    private String columnNames[] = {"Position", "Name", "Email", "Phone Number", "Password"};

    public Manager_Approve_User() {
        passwords = new ArrayList<>();
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }

            @Override
            public Object getValueAt(int row, int column) {
                if (column == 4) { // Mask the password column
                    return "********";
                }
                return super.getValueAt(row, column);
            }
        };
        try{
        model.setColumnIdentifiers(columnNames);
        FileReader fr = new FileReader("src/main/java/OOP/Unapprove_User.txt");
        BufferedReader br = new BufferedReader(fr);
        
        String line = null;

        while ((line = br.readLine()) != null){
            String values[] = line.split(", ");
            model.addRow(values);
            passwords.add(values[4]);
        }
        br.close();
        fr.close();
        
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        initComponents();        
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        unapproveUserTable = new javax.swing.JTable();
        Select = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Exit = new javax.swing.JButton();
        Delete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));

        unapproveUserTable.setModel(model);
        unapproveUserTable.setCellSelectionEnabled(true);
        unapproveUserTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                unapproveUserTablePropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(unapproveUserTable);
        unapproveUserTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        Select.setText("Select");
        Select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Unapprove User");

        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Select)
                .addGap(26, 26, 26)
                .addComponent(Delete)
                .addGap(26, 26, 26)
                .addComponent(Exit)
                .addGap(115, 115, 115))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(jLabel1)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Select)
                    .addComponent(Exit)
                    .addComponent(Delete))
                .addGap(18, 18, 18))
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

    private void SelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectActionPerformed
        // TODO add your handling code here:
        int selectedRow = unapproveUserTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(Manager_Approve_User.this, "Please select a user to approve.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
            

        String position = (String) model.getValueAt(selectedRow, 0);
        String name = (String) model.getValueAt(selectedRow, 1);
        String email = (String) model.getValueAt(selectedRow, 2);
        String phoneNumber = (String) model.getValueAt(selectedRow, 3);
        String password = (String) passwords.get(selectedRow);
        
        try {
            if (isUserAlreadyRegistered(position, email, phoneNumber)) {
                JOptionPane.showMessageDialog(Manager_Approve_User.this, "This user cannot be approved because the email or phone number is already registered.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(Manager_Approve_User.this, "An error occurred while checking user registration: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(Manager_Approve_User.this, "Are you sure you want to approve this user?", "Confirm Approval", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Remove the selected user from Unapprove_User.txt
                removeUserFromFile(position, email, phoneNumber);

                // Add the user to the appropriate file based on their position
                String userId = generateUserId(position);
                addUserToFile(userId, name, email, phoneNumber, position, password);

                // Remove the row from the table
                model.removeRow(selectedRow);

                JOptionPane.showMessageDialog(Manager_Approve_User.this, "User approved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(Manager_Approve_User.this, "An error occurred while approving the user: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }//GEN-LAST:event_SelectActionPerformed
    
    public boolean isUserAlreadyRegistered(String position, String email, String phoneNumber) throws IOException {
        String filePath = "src/main/java/OOP/" + position + "_Info.txt";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(", ");
            if (parts.length >= 5 && (parts[2].equals(email) || parts[3].equals(phoneNumber))) {
                reader.close();
                return true;
            }
        }
        reader.close();
        return false;
    }
    
    private void removeUserFromFile(String position, String email, String phoneNumber) throws IOException {
        File inputFile = new File("src/main/java/OOP/Unapprove_User.txt");
        File tempFile = new File("src/main/java/OOP/Unapprove_User_temp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(", ");
            if (parts.length >= 5 && parts[2].equals(email) && parts[3].equals(phoneNumber)) {
                continue;
            }
            writer.write(line + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();

        if (!inputFile.delete()) {
            throw new IOException("Failed to delete the original file.");
        }
        if (!tempFile.renameTo(inputFile)) {
            throw new IOException("Failed to rename the temporary file.");
        }
    }

    private String generateUserId(String position) throws IOException {
        String filePath = "src/main/java/OOP/" + position + "_Info.txt";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        int count = 0;
        while (reader.readLine() != null) {
            count++;
        }
        reader.close();

        String prefix = position.substring(0, 1).toUpperCase();
        return prefix + String.format("%02d", count + 1);
    }

    private void addUserToFile(String userId, String name, String email, String phoneNumber, String position, String password) throws IOException {
        String filePath = "src/main/java/OOP/" + position + "_Info.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
        writer.write(userId + ", " + name + ", " + email + ", " + phoneNumber + ", " + password + "\n");
        writer.close();
    }
    

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        // TODO add your handling code here:
        Manager mg = new Manager();
        mg.setVisible(true);
        this. dispose();
    }//GEN-LAST:event_ExitActionPerformed

    private void unapproveUserTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_unapproveUserTablePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_unapproveUserTablePropertyChange

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
        int selectedRow = unapproveUserTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(Manager_Approve_User.this, "Please select a user to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String position = (String) model.getValueAt(selectedRow, 0);
        String email = (String) model.getValueAt(selectedRow, 2);
        String phoneNumber = (String) model.getValueAt(selectedRow, 3);

        int confirm = JOptionPane.showConfirmDialog(Manager_Approve_User.this, "Are you sure you want to delete this user?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Remove the selected user from Unapprove_User.txt
                removeUserFromFile(position, email, phoneNumber);

                // Remove the row from the table
                model.removeRow(selectedRow);

                JOptionPane.showMessageDialog(Manager_Approve_User.this, "User deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(Manager_Approve_User.this, "An error occurred while deleting the user: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_DeleteActionPerformed

    /**
     * @param args the command line arguments
     */
    
    /*private void loadDataFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(Unapprove_User.txt))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] rowData = line.split(","); // Adjust the delimiter if needed
                tableModel.addRow(rowData);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }*/
    
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
            java.util.logging.Logger.getLogger(Manager_Approve_User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manager_Approve_User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manager_Approve_User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manager_Approve_User.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manager_Approve_User().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Delete;
    private javax.swing.JButton Exit;
    private javax.swing.JButton Select;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable unapproveUserTable;
    // End of variables declaration//GEN-END:variables
}
