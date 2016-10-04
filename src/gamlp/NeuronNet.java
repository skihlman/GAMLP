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
    private Neuron[][] neuron;
    
    // Constructor
    public NeuronNet(double[] constraints) {
        if (constraints.length < 2)
            set(1, 1);
        else
            set((int)constraints[0], (int)constraints[1]);
    }
    
    // Constructor
    public NeuronNet(int inputs, int outputs) {
        set(inputs, outputs);
    }
    
    // Set the network
    private void set(int in, int out) {
        WeightArray weights = new WeightArray(in, out);
        int[] npl = weights.getNPL();
        int layers = npl.length;
        neuron = new Neuron[layers][];
        // Looping backwards through layers (to make possible to add weights)
        for (int l = layers - 1; l >= 0; l--) {
            neuron[l] = new Neuron[npl[l]];
            // Loop through neurons in layer l
            for (int n = 0; n < neuron[l].length; n++) {
                neuron[l][n] = createNeuron(npl, l, n);
                // If not in the last layer, add connections to next layer
                if (l < layers - 1) {
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
    
    // Create a new neuron for the network
    private Neuron createNeuron(int[] npl, int layer, int nNeur) {
        Neuron neur;
        if (layer == 0) {
            if (nNeur == npl[0] - 1)
                neur = new ThresholdNeuron();
            else
                neur = new InputNeuron();
            
        }
        else if (layer == npl.length - 1) {
            neur = new Neuron();
        }
        else {
            if (nNeur == npl[layer] - 1)
                neur = new ThresholdNeuron();
            else
                neur = new Neuron();
        }
        return neur;
    }
    
    public int numInputs() {
        return neuronsInLayer(0) - 1; // - Threshold neuron
    }
    
    public int numOutputs() {
        return neuronsInLayer(numLayers() - 1);
    }
    
    // Get an output
    public double[] getOutput(double[] input) {
        int layers = numLayers();
        // Create the output array        
        double[] out = new double[neuronsInLayer(layers - 1)];
        // Add the inputs
        for (int n = 0; n < input.length; n++)
            neuron[0][n].addLoad(input[n]);
        // feed the signal through the network
        for (int l = 0; l < layers - 1; l++){
            for (Neuron neur : neuron[l]) {
                neur.push();
            }
        }
        // Get the output from the output layer
        for (int n = 0; n < neuronsInLayer(layers - 1); n++) 
            out[n] = neuron[layers - 1][n].getLoad();
        return out;
    }
    
    // Get a neuron from the network based on layer and neuron index in layer
    public Neuron getNeuron(int layer, int neuron) {
        try {
            return this.neuron[layer][neuron];
        }
        catch (Exception e) {
            return null;
        }
    }
    
    // Number of layers in this network
    public int numLayers() {
        return neuron.length;
    }
    
    // Number of neurons in a specified layer
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
    public boolean isViable(double[] constr) {
        if (constr.length != this.constraints.length)
            return false;
        for (int i = 0; i < this.constraints.length; i++)
            if (this.constraints[i] != constr[i])
                return false;
        return true;
    }

    @Override
    public void setConstraints() {
        this.constraints = new double[] {this.numInputs(), this.numOutputs()};
    }
    
}
