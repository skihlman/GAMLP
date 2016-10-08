/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamlp;

import GUI.TestGUI;

/**
 *
 * @author sebastian
 */
public class GAMLP_tester {
    
    public static void main(String[] args) {
        //nonsenseTestCode();
        final TestGUI GUI = new TestGUI();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //NeuronNet net = new NeuronNet(3, 2);
                GUI.setVisible(true);
            }
        });
    }
    
    // This method contains the current code to be tested. Change as you like
    public static void nonsenseTestCode() {
        /*double[] val = new double[1000];
        for (int i = 0; i < 1000; i++) {
            val[i] = helpFunction.Distribution.normal();
            System.out.println(val[i]);
        }
        // Check cdf
        System.out.println("CDF:");
        for (double x = -2; x <= 2; x += 0.1) {
            double p = (double)countSmaller(x, val) / 1000;
            //System.out.println(x + ": " + p);
            for (double dp = 0; dp < p; dp += 0.01)
                System.out.print("|");
            System.out.println();
        }
        
        NeuronNet net = new NeuronNet(new WeightArray(1, 1));
        double[] in = new double[]{0, 0, 0};
        double[] out = net.getOutput(in);
        for (double d : out)
            System.out.println(d + " ");*/
                
    }
    
    private static int countSmaller(double x, double[] val) {
        int sum = 0;
        for (int i = 0; i < val.length; i++)
            if (val[i] < x)
                sum++;
        return sum;
    }
}
