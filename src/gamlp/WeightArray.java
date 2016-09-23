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
        if (layer < 0 || neuron < 0 || weight < 0) 
            return 0;
        if (layer >= weights.size() || neuron >= weights.get(layer).size() 
                || weight >= weights.get(layer).get(neuron).size())
            return 0;
        return weights.get(layer).get(neuron).get(weight);
    }
    
}
