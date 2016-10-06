/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import gamlp.NeuronNet;
import gamlp.NeuronNetFactory;
import gamlp.WeightArray;
import java.awt.GridBagConstraints;
import static java.awt.GridBagConstraints.*;
import java.awt.GridBagLayout;

/**
 *
 * @author sebastian
 */
public class TestGUI extends javax.swing.JFrame {
    
    // Variables declaration
    // Panels
    private LeftPanel leftPanel;
    private TopPanel topPanel;
    private BottomPanel bottomPanel;
    private MainPanel mainPanel;
        
    // Tools
    private NetworkTool netTool;
    
    
    // End of variables declaration

    /**
     * Creates new form TestGUI
     */
    public TestGUI() {
        super("Test GUI for GAMLP");
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // Init the GUI
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        // Set layout
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        // Create the panels
        leftPanel = new LeftPanel();
        topPanel = new TopPanel();
        bottomPanel = new BottomPanel();
        mainPanel = new MainPanel();
        
        // Create and initialize tools
        netTool = new NetworkTool(new NeuronNetFactory(2, 2).create());
        //netTool.setPreferredSize(new java.awt.Dimension(600, 300));
        
        // Add panels
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 0.1;
        c.anchor = NORTH;
        c.fill = BOTH;
        c.gridheight = 1;
        c.gridwidth = 2;
        this.getContentPane().add(topPanel);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.2;
        c.weighty = 0.9;
        c.gridheight = 2;
        c.anchor = WEST;
        c.fill = BOTH;
        c.gridheight = 2;
        c.gridwidth = 1;
        this.getContentPane().add(leftPanel);
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.8;
        c.weighty = 0.6;
        c.anchor = EAST;
        c.fill = BOTH;
        c.gridheight = 1;
        c.gridwidth = 1;
        this.getContentPane().add(mainPanel);
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0.8;
        c.weighty = 0.3;
        c.anchor = SOUTH;
        c.fill = BOTH;
        c.gridheight = 1;
        c.gridwidth = 1;
        this.getContentPane().add(bottomPanel);
        
        // Add content to panels
        c.fill = BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        mainPanel.add(netTool, c);
        // Pack...
        pack();
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
            java.util.logging.Logger.getLogger(TestGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TestGUI().setVisible(true);
            }
        });
    }

}
