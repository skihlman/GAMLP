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
    private int[] npl;
    private ArrayList<ArrayList<ArrayList<Double>>> weights;
    
    public static final int MAX_HIDDEN_LAYERS = 3;
    public static final int MIN_NEURONS_IN_HIDDEN_LAYER = 2;
    public static final double MAX_NEURONS_REL_PREV_LAYER = 2.0;
    public static final double WEIGHT_SD = 0.1;
    public static final double WEIGHT_MEAN = 0;
    
    public WeightArray(int in, int out) {
        this.inputs = in;
        this.outputs = out;
        npl = createNPL();
        generateWeights(npl);
    }
    
    public WeightArray(int[] npl) {
        this.inputs = npl[0] - 1;   // Disgard threshold neuron
        this.outputs = npl[npl.length - 1];
        generateWeights(npl);
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
    
    private int[] createNPL(int[] npl1, int[] npl2) {
        int minL = Math.min(npl1.length, npl2.length);
        int maxL = Math.max(npl1.length, npl2.length);       
        int layers = helpFunction.Distribution.equalInt(minL, maxL) + minL;
        int[] npl = new int[layers];
        npl[0] = inputs + 1; // Threshold neuron
        npl[layers - 1] = outputs; // No threshold neuron
        for (int l = 1;  l < layers - 1; l++) {
            int minN = Math.min(npl1[l], npl2[l]);            
            int maxN = Math.max(npl1[l], npl2[l]);
            npl[l] = minN + Distribution.equalInt(minN, maxN);
        }
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
                    double w = createWeight();
                    weights.get(l).get(n).add(w);
                }
            }
        }        
    }
    
    public double createWeight() {
        return Distribution.normal(WEIGHT_MEAN, WEIGHT_SD);
    }
    
    public double get(int layer, int neuron, int weightNum) {
        // Try to return the weight based on layer, neuron and weight no indices
        try {
            return weights.get(layer).get(neuron).get(weightNum);
        }
        // If any of the given layer, neuron and weight indices doesn't exist
        // return 0
        catch (Exception e) {
            return 0;
        }        
    }
    
    public void set(int layer, int neuron, int weightNum, double weight) {
        // Try to set the weight based on layer, neuron and weight no. indices
        try {
            weights.get(layer).get(neuron).set(weightNum, weight);
        }
        // If any of the given layer, neuron and weight indices doesn't exist
        // return 0
        catch (Exception e) {
            // Do nothing
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
    
    public int numLayers() {
        return this.weights.size();
    }
    
    private double getRandW(double val1, double val2, double sd) {
        double mean = (val1 + val2) / 2;
        double dist = Math.abs(val1 - mean);
        double newDist = dist * sd;
        return mean + newDist;
    }
    
    private double getRandW(double val1, double val2) {
        return Math.random() < 0.5 ? val1 : val2;
    }

    @Override
    public WeightArray recombine(AbstractChromosome partner, double pRandom, double sd) {
        WeightArray wPartner = (WeightArray)partner;
        int[] npl = createNPL(this.getNPL(), wPartner.getNPL());
        int layers = npl.length;
        WeightArray child = new WeightArray(npl);
        for (int l = 0; l < layers - 1; l++) {
            for (int n = 0; n < npl[l]; n++) {
                int thInNextLayer = l < layers - 2 ? 1 : 0;
                for (int c = 0; c < npl[l + 1] - thInNextLayer; c++) {
                    double w = (Math.random() < pRandom) ?
                            getRandW(this.get(l, n, c), wPartner.get(l, n, c), sd) :
                            getRandW(this.get(l, n, c), wPartner.get(l, n, c));
                    child.set(l, n, c, w);
                }
            }
        }     
        return child;
    }
    
    @Override
    public WeightArray clone() {
        WeightArray copy = new WeightArray(this.getNPL());
        for (int l = 0; l < this.weights.size(); l++)
            for (int n = 0; n < this.weights.get(l).size(); n++)
                for (int c = 0; c < this.weights.get(l).get(n).size(); c++)
                    copy.set(l, n, c, this.get(l, n, c));
        return copy;
    }

    /* 
    Only weights are mutated by adding to the weight value from a normal 
    distribution with mean 0 and sd.
    Not yet implemented is the structural change part of the mutation.
    */
    @Override
    public WeightArray mutate(double rate, double sd, double pStructureChange) {
        int layers = npl.length;
        for (int l = 0; l < layers - 1; l++) {
            for (int n = 0; n < npl[l]; n++) {
                int thInNextLayer = l < layers - 2 ? 1 : 0;
                for (int c = 0; c < npl[l + 1] - thInNextLayer; c++) {
                    if (Math.random() < rate) {
                        double w = weights.get(l).get(n).get(c);
                        w += helpFunction.Distribution.normal(0, sd);
                        this.set(l, n, c, w);
                    }
                }
            }
        }
        return this;
    }
    
    

    
}
