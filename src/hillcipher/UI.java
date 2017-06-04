
package hillcipher;
import javax.swing.JOptionPane;
import org.apache.commons.math3.linear.*;

public class UI extends javax.swing.JFrame {
	
    //Default keys
	double[][] encryptKey = {{23,53,87},{98,56,2},{98,123,73}};
	double[][] decryptKey = {{23,58,26},{25,44,6},{71,51,18}};
	
	public UI() {
		initComponents();
		jTextArea1.setText("Standard key in use: 23 53 87 98 56 2 98 123 73");
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SetKeyDialog = new javax.swing.JDialog();
        keyEntryBox = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        OKButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        encryptButton = new javax.swing.JButton();
        decryptButton = new javax.swing.JButton();
        SetKeyButton = new javax.swing.JButton();

        SetKeyDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        SetKeyDialog.setTitle("Encryption Key");
        SetKeyDialog.setLocation(new java.awt.Point(300, 300));
        SetKeyDialog.setMinimumSize(new java.awt.Dimension(274, 95));

        keyEntryBox.setText("3 1 2 1 1 2 2 1 4");

        jLabel1.setText("Edit Key: ");

        OKButton.setText("OK");
        OKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SetKeyDialogLayout = new javax.swing.GroupLayout(SetKeyDialog.getContentPane());
        SetKeyDialog.getContentPane().setLayout(SetKeyDialogLayout);
        SetKeyDialogLayout.setHorizontalGroup(
            SetKeyDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SetKeyDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SetKeyDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(SetKeyDialogLayout.createSequentialGroup()
                        .addComponent(OKButton)
                        .addGap(18, 18, 18)
                        .addComponent(cancelButton))
                    .addGroup(SetKeyDialogLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(keyEntryBox, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)))
                .addGap(13, 13, 13))
        );
        SetKeyDialogLayout.setVerticalGroup(
            SetKeyDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SetKeyDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SetKeyDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(keyEntryBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SetKeyDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(OKButton))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hill Cipher");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        encryptButton.setText("Encrypt");
        encryptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encryptButtonActionPerformed(evt);
            }
        });

        decryptButton.setText("Decrypt");
        decryptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decryptButtonActionPerformed(evt);
            }
        });

        SetKeyButton.setText("Set Key");
        SetKeyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SetKeyButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(encryptButton)
                .addGap(18, 18, 18)
                .addComponent(decryptButton)
                .addGap(18, 18, 18)
                .addComponent(SetKeyButton)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(decryptButton)
                    .addComponent(encryptButton)
                    .addComponent(SetKeyButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void encryptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encryptButtonActionPerformed
        String message = jTextArea1.getText();
		message = HillCipher.cipher(message, encryptKey);
		jTextArea1.setText(message);
    }//GEN-LAST:event_encryptButtonActionPerformed

    private void decryptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decryptButtonActionPerformed
        String message = jTextArea1.getText();
		message = HillCipher.cipher(message, decryptKey);
		jTextArea1.setText(message);
    }//GEN-LAST:event_decryptButtonActionPerformed

    private void SetKeyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SetKeyButtonActionPerformed
        SetKeyDialog.setVisible(true);
    }//GEN-LAST:event_SetKeyButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        SetKeyDialog.setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void OKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKButtonActionPerformed
        String keyInput = keyEntryBox.getText();
		double[][] keyBuffer;  
		try{
			keyBuffer = HillCipher.parseKey(keyInput);
		}
		catch(java.lang.NumberFormatException e){ //checks for integer input
			JOptionPane.showMessageDialog(null, "Error: Key provided contains non-integer values.",
											    "Key Entry Error",
												JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if (keyBuffer == null){
			JOptionPane.showMessageDialog(null, "Error: Key doesn't contain a square number of values.",
												"Key Entry Error",
												JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		double[][] decryptKeyBuffer;
		try{
			decryptKeyBuffer = HillCipher.makeInverseKey(keyBuffer);
		}
		catch(SingularMatrixException e){
			JOptionPane.showMessageDialog(null, "Error: Matrix is singular",
												"Key Entry Error",
												JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if (decryptKeyBuffer == null){
			JOptionPane.showMessageDialog(null, "error: Matrix's determinant not coprime to # of symbols.",
												"Key Entry Error",
												JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		//if all successful, update keys.
		encryptKey = keyBuffer;
		decryptKey = decryptKeyBuffer;
		SetKeyDialog.setVisible(false);
    }//GEN-LAST:event_OKButtonActionPerformed

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
			java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new UI().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OKButton;
    private javax.swing.JButton SetKeyButton;
    private javax.swing.JDialog SetKeyDialog;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton decryptButton;
    private javax.swing.JButton encryptButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField keyEntryBox;
    // End of variables declaration//GEN-END:variables
}
