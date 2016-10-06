/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import gamlp.NeuronNet;
import static helpFunction.Numbers.*;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import static java.awt.GridBagConstraints.*;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

/**
 *
 * @author sebastian
 */
public class NetworkTool extends JPanel {
    private NeuronNet net;
    
    private javax.swing.JTextField[] txtInput;
    private javax.swing.JTextField[] txtOutput;
    private NetworkIllustrator nIllustrator;
    
    // Constructor taking NeuronNet as argument
    NetworkTool(NeuronNet net) {
        //this.setPreferredSize(new Dimension(600, 200));
        this.net = net;
        nIllustrator = new NetworkIllustrator(net);
        this.setLayout(new GridBagLayout());
        int inputs = net.numInputs();
        int outputs = net.numOutputs();
        int cols = Math.max(inputs, outputs);
        GridBagConstraints c = new GridBagConstraints();
        // Set inputFields
        txtInput = new javax.swing.JTextField[inputs];
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 12;
        c.insets = new Insets(12, 12, 12, 12);
        for (int i = 0; i < inputs; i++) {
            final int index = i;
            c.gridx = 0;
            c.gridy = i;
            c.weightx = 0.1;
            c.weighty = (double)1 / cols;
            c.anchor = WEST;
            txtInput[i] = new javax.swing.JTextField("0.0", 4);
            txtInput[i].setMaximumSize(new Dimension(8, 8));
            // Add a document listener to textfield i (checking for non-numerical input)
            txtInput[i].getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {txtChange(e, txtInput[index]);}
                @Override
                public void removeUpdate(DocumentEvent e) {txtChange(e, txtInput[index]);}
                @Override
                public void changedUpdate(DocumentEvent e) {/* Do nothing*/}
            });
            // Add focus listener to textfield i to highlight text when focused
            txtInput[i].addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {txtFocus(e, txtInput[index]);}
                @Override
                public void focusLost(FocusEvent e) {/*/ Do nothing*/}
            });
            this.add(txtInput[i], c);
        }
        // Set theNetworkIllustrator
        GridBagConstraints cI = new GridBagConstraints();
        cI.fill = GridBagConstraints.BOTH;
        cI.gridx = 1;
        cI.gridy = 0;
        cI.gridheight = cols;
        cI.weightx = 0.7;
        cI.weighty = 1;
        this.add(nIllustrator, cI);
        // Set outputFields
        txtOutput = new javax.swing.JTextField[outputs];
        for (int i = 0; i < outputs; i++) {
            c.gridx = 2;
            c.gridy = i;
            c.gridheight = 1;
            c.weightx = 0.2;
            c.weighty = (double)1 / cols;
            txtOutput[i] = new javax.swing.JTextField("0.0", 4);
            txtOutput[i].setEditable(false);
            //txtOutput[i].applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            txtOutput[i].setCaretPosition(0);
            // Add a document listener to textfield i (checking for non-numerical input)
            
            this.add(txtOutput[i], c);
        }
        //***
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        // Update the network output
        updateOutput();
    }
    
    
    private void txtChange(final DocumentEvent e, final javax.swing.JTextField txtF) {
        Runnable check = new Runnable() {
            @Override
            public void run() {
                try {
                    int len = e.getDocument().getLength();
                    String txt = txtF.getText(0, len);
                    if (!isNumeric(txt) && (txt.length() != 1 || txt.charAt(0) != '-')) {
                        txtF.setText("0.0");
                    }
                    // Remove leading zeros
                    if (txt.length() > 1 && txt.charAt(0) == '0') {
                        if (txt.charAt(1) != '.')
                            txtF.setText(txt.substring(1));
                    }
                } catch (BadLocationException ex) {
                    Logger.getLogger(NetworkTool.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };       
        SwingUtilities.invokeLater(check);
        // Update the network output
        updateOutput();
    }
    
    private void txtFocus(final FocusEvent e, final javax.swing.JTextField txtF) {
        txtF.selectAll();
    }
    
    private void formKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
    }
    
    // Calculate and display network output based on input
    private void updateOutput() {
        double[] input = new double[net.numInputs()];
        double[] output;        
        try {
            for (int i = 0; i < input.length; i++) {
                input[i] = Double.parseDouble(txtInput[i].getText());
            }
            output = net.getOutput(input);
            for (int i = 0; i < output.length; i++) {
                txtOutput[i].setText("" + output[i]);
                txtOutput[i].setCaretPosition(0);
            }
        }
        catch (Exception e) {
            // Do nothing...
            //System.out.println("Error:" + e);
        }
    }
   
}
