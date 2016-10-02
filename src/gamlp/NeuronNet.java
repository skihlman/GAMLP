/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamlp;

import GA.AbstractIndividual;

/**
 *
 * @author sebastian
 */
public class NeuronNet extends AbstractIndividual {
    private final Neuron[][] neuron;
    
    // Constructor
    NeuronNet(int inputs, int outputs) {
        WeightArray weights = new WeightArray(inputs, outputs);
        int[] npl = weights.getNPL();
        neuron = new Neuron[npl.length][];
        // Looping backwards through layers (to make possible to add weights)
        for (int l = neuron.length - 1; l >= 0; l--) {
            neuron[l] = new Neuron[npl[l]];
            // Loop through neurons in layer l
            for (int n = 0; n < neuron[l].length; n++) {
                neuron[l][n] = (n < neuron[l].length - 1) ? 
                        new Neuron() : new ThresholdNeuron();
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
    
    // Get an output
    public double[] getOutput(double[] input) {
        int layers = numLayers();
        // Create the output array        
        double[] out = new double[neuronsInLayer(layers - 1)];
        // Add the inputs
        for (int n = 0; n < neuron[0].length; n++)
            neuron[0][n].addLoad(input[n]);
        // feed the signal through the network
        for (int l = 0; l < neuron.length; l++){
            for (int n = 0; n < neuron[l].length; n++) {
                neuron[l][n].push();
            }
        }
        // Get the output from the output layer
        for (int n = 0; n < neuronsInLayer(layers - 1); n++)
            out[n] = neuron[layers - 1][n].getLoad();
        return out;
    }
    
    public Neuron getNeuron(int layer, int neuron) {
        try {
            return this.neuron[layer][neuron];
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public int numLayers() {
        return neuron.length;
    }
    
    public int neuronsInLayer(int layer) {
        try {
            return neuron[layer].length;
        }
        catch (Exception e) {
            return 0;
        }
    }

    @Override
    public double fitness() {
        return 0;
    }

    @Override
    public boolean isViable() {
        return true;
    }
    
}
