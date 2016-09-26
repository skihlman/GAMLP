/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamlp;

import java.util.ArrayList;

/**
 *
 * @author sebastian
 */
public class WeightArray {
    private ArrayList<ArrayList<ArrayList<Double>>> weights;
    
    WeightArray(Chromosome chromosome) {
        weights = GeneHandler.getWeightArray(chromosome);
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
        npl = new int[weights.size()];
        for (int i = 0; i < npl.length; i++)
            npl[i] = weights.get(i).size();
        return npl;
    }
    
}
