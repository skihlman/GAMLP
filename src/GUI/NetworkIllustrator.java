package GUI;

import gamlp.NeuronNet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.SwingConstants;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sebastian
 */
public class NetworkIllustrator extends javax.swing.JPanel {
    // The network to be illustrated
    private NeuronNet net;

    /**
     * Creates new form NetworkIllustrator
     * @param net
     */
    public NetworkIllustrator(NeuronNet net) {
        super(new FlowLayout(SwingConstants.LEADING, 10, 10));
        this.net = net;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // Buiding the GUI
    private void initComponents() {
        this.setMinimumSize(new Dimension(400, 400));
        //this.setPreferredSize(new Dimension(400, 400));
        setBackground(new java.awt.Color(255, 255, 255));
        setAutoscrolls(true);
        setFont(new java.awt.Font("Arial", 0, 10)); 

    }

    

    
    
    public void drawNet(Graphics g) {
        int xMid = this.getWidth() / 2;
        int yMid = this.getHeight() / 2;
        int layers = net.numLayers();
        int maxNeurons = 0;
        for (int i = 0; i < layers; i++) {
            if (net.neuronsInLayer(i) > maxNeurons)
                maxNeurons = net.neuronsInLayer(i);
        }
        int xStep = (int)(1.8 * xMid / layers);
        int yStep = (int)(1.8 * yMid / maxNeurons);
        int xStart = (int)(xMid * 0.1 + xStep / 2);
        int yStart = (int)(yMid * 0.1 + yStep / 2);
        int radius = (int) (0.4 * Math.min(xStep, yStep));
        // Loop through layers of net
        for (int l = 0; l < layers; l++){
            int x1 = xStart + xStep * l;
            int numNeurons = net.neuronsInLayer(l);
            // loop through neurons in layer l
            for (int n = 0; n < numNeurons; n++) {
                int y1 = (int)(yMid - yStep * (double)numNeurons / 2 + yStep * n + yStep / 2);
                // Set color depending on type of neuron to be drawn
                if (n < numNeurons - 1 || l == layers - 1) 
                    g.setColor(new Color(16, 32, 16));
                else
                    g.setColor(new Color(180, 120, 180));
                // Draw neuron n in layer l
                g.drawOval(x1 - radius, y1 - radius, 2 * radius, 2 * radius);
                // Draw connections (if not in last layer)
                if (l < layers - 1) {
                    int x2 = x1 + xStep;
                    // Get the weights from the current neuron
                    double[] weights = net.getNeuron(l, n).getWeightArray();
                    // Loop through connections from neuron n in layer l
                    for (int c = 0; c < weights.length; c++) {
                        g.setColor(getWeightColor(weights[c]));
                        int numNeur2 = net.neuronsInLayer(l + 1);
                        int y2 = (int)(yMid - yStep * (double) numNeur2 / 2 + yStep * c + yStep / 2);
                        g.drawLine(x1, y1, x2, y2);
                        g.setColor(getWTextColor(weights[c]));
                        g.setFont(g.getFont().deriveFont(8));
                        double d1 = (double)numNeurons / (numNeurons + net.neuronsInLayer(l + 1));
                        double d2 = 1 - d1;
                        g.drawString(("" + weights[c]).substring(0, 5), 
                                (int)(d1 * x1 + d2 * x2), 
                                (int)(d1 * y1 + d2 * y2));
                    }
                }
            }
        }
    }
    
    public static Color getWeightColor(double w) {
        int byteVal = (int)(200 / (1 + Math.abs(w)));
        int red = w > 0 ? 255 : byteVal;
        int green = byteVal;
        int blue = w > 0 ? byteVal : 255;
        return new Color(red, green, blue);
    }
    
    public static Color getWTextColor(double w) {
        int byteVal = (int)(60 / (1 + Math.abs(w)));
        int red = w > 0 ? 200 : byteVal;
        int green = byteVal;
        int blue = w > 0 ? byteVal : 200;
        return new Color(red, green, blue);
    }
    
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawNet(g);
    }
}
