/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamlp;

/**
 *
 * @author sebastian
 */
public class NeuronNet {
    private Neuron[][] neuron;
    
    NeuronNet(int[] npl) {
        neuron = new Neuron[npl.length][];
        for (int l = 0; l < neuron.length; l++) {
            neuron[l] = new Neuron[npl[l]];
            for (int n = 0; n < neuron[n].length; n++)
                neuron[l][n] = new Neuron();
        }
    }
    
    
}
