/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamlp;

import GA.AbstractChromosome;
import helpFunction.Distribution;
import java.util.ArrayList;

/**
 *
 * @author sebastian
 */
public class WeightArray extends AbstractChromosome {
    private final int inputs;
    private final int outputs;
    private ArrayList<ArrayList<ArrayList<Double>>> weights;
    
    public static final int MAX_HIDDEN_LAYERS = 3;
    public static final int MIN_NEURONS_IN_HIDDEN_LAYER = 2;
    public static final double MAX_NEURONS_REL_PREV_LAYER = 2.0;
    public static final double WEIGHT_SD = 0.1;
    public static final double WEIGHT_MEAN = 0;
    
    WeightArray(int in, int out) {
        this.inputs = in;
        this.outputs = out;
        generateWeights(createNPL());
    }
    
    private int[] createNPL() {
        int layers = (int) Distribution.equal(2, MAX_HIDDEN_LAYERS + 2);
        int[] npl = new int[layers];
        npl[0] = inputs + 1; // Threshold neuron
        npl[layers - 1] = outputs; // No threshold neuron
        for (int l = 1;  l < layers - 1; l++) 
            npl[l] = Distribution.equalInt(MIN_NEURONS_IN_HIDDEN_LAYER, 
                    (int)MAX_NEURONS_REL_PREV_LAYER * npl[l - 1]) + 1; // + Threshold neuron
        return npl;
    }
    
    private void generateWeights(int[] npl) {
        int layers = npl.length;
        weights = new ArrayList<>();
        for (int l = 0; l < layers - 1; l++) {
            weights.add(new ArrayList<ArrayList<Double>>());
            for (int n = 0; n < npl[l]; n++) {
                weights.get(l).add(new ArrayList<Double>());
                int thInNextLayer = l < layers - 2 ? 1 : 0;
                for (int c = 0; c < npl[l + 1] - thInNextLayer; c++) {
                    double w = Distribution.normal(WEIGHT_MEAN, WEIGHT_SD);
                    weights.get(l).get(n).add(w);
                }
            }
        }
        
    }
    
    
    
    public double get(int layer, int neuron, int weight) {
        // Try to return the weight based on layer, neuron and weight no indices
        try {
            return weights.get(layer).get(neuron).get(weight);
        }
        // If any of the given layer, neuron and weight indices doesn't exist
        // return 0
        catch (Exception e) {
            return 0;
        }        
    }
    
    // Get the number of connections from neuron in layer
    public int getNumConnections(int layer, int neuron) {
        try {
            return weights.get(layer).get(neuron).size();
        }
        // If any of the given layer or neuron indices doesn't exist return 0
        catch (Exception e) {
            return 0;
        }    
    }
    
    public int[] getNPL() {
        int npl[];
        int layers = weights.size() + 1;
        npl = new int[layers];
        //npl[0] = inputs;
        for (int i = 0; i < layers - 1; i++)
            npl[i] = weights.get(i).size();
        npl[layers - 1] = outputs;
        return npl;
    }
    
    private int getMaxConnectionsInLayer(int layer) {
        int sum = 0;
        int maxSum = 0;
        for (ArrayList<Double> neuron : weights.get(layer)) {
            for (Double d : neuron) {
                sum++;
            }
            if (sum > maxSum)
                maxSum = sum;
        }
        return maxSum;
    }

    @Override
    public AbstractChromosome clone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractChromosome mutate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractChromosome recombine(AbstractChromosome partner) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
