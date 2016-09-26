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
    
    NeuronNet(WeightArray weights) {
        int[] npl = weights.getNPL();
        neuron = new Neuron[npl.length][];
        // Looping backwards through layers (to make possible to add weights)
        for (int l = neuron.length - 1; l >= 0; l--) {
            neuron[l] = new Neuron[npl[l]];
            // Loop through neurons in layer l
            for (int n = 0; n < neuron[l].length; n++) {
                neuron[l][n] = new Neuron();
                // If not in the last layer, add connections to next layer
                if (l < neuron.length - 1) {
                    // Loop through weights in l, n and add connections
                    for (int c = 0; c < weights.getNumConnections(l, n); c++) {
                        Connection con = new Connection(weights.get(l, n, c), 
                                neuron[l + 1][c]);
                        neuron[l][n].addConnection(con);
                    }
                }
            }
        }
    }
    
    
}
