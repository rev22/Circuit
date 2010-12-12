/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MessageDialog.java
 *
 * Created on Dec 12, 2010, 11:09:06 AM
 */

/**
 *
 * @author ermh
 */
public class MessageDialog extends java.awt.Dialog {

    /** Creates new form MessageDialog */
    public MessageDialog(java.awt.Frame parent, String message, String title) {
        super(parent, true);
        initComponents();
        this.label.setText(message);
        setTitle(title);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new java.awt.Panel();
        button1 = new java.awt.Button();
        jPanel1 = new javax.swing.JPanel();
        label = new java.awt.Label();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        button1.setLabel("OK");
        panel.add(button1);

        add(panel, java.awt.BorderLayout.SOUTH);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        label.setAlignment(1);
        label.setText("Message Text");
        jPanel1.add(label, new java.awt.GridBagConstraints());

        add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MessageDialog dialog = new MessageDialog(new java.awt.Frame(), "Test", "Test");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button1;
    private javax.swing.JPanel jPanel1;
    private java.awt.Label label;
    private java.awt.Panel panel;
    // End of variables declaration//GEN-END:variables

}
