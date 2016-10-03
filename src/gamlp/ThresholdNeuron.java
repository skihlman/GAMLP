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
public class ThresholdNeuron extends Neuron {
    private final double load;
    private final double lastLoad;
    
    ThresholdNeuron() {
        load = 1;
        lastLoad = 1;
    }
    
    @Override
    public void addLoad(double l) {
        // Do nothing. Load is always 1
    }
    
    @Override
    public void push() {
        for (Connection con : connections) {
            con.push(load);
        }
    }
    
    @Override
    public double getLoad() {
        return load;
    }
}
