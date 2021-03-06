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
public class Neuron {
    private double load;
    private double lastLoad;
    ArrayList<Connection> connections;
    
    Neuron() {
        load = 0;
        lastLoad = 0;
        connections = new ArrayList<>();
    }
    
    public void addLoad(double l) {
        load += l;
    }
    
    public void addConnection(Connection con) {
        connections.add(con);
    }
    
    public void push() {
        for (Connection con : connections) {
            con.push(activationFunction());
        }
        clear();
    }
    
    public double getLoad() {
        return getLoad(false);
    }
    
    public double getLoad(boolean preserveLoad) {
        double retVal = load;
        if (!preserveLoad) {
            clear();
        }
        return retVal;
    }
    
    public void clear() {
        lastLoad = load;
        load = 0;
    }
    
    public double[] getWeightArray() {
        double[] weights = new double[connections.size()]; 
        for (int i = 0; i < weights.length; i++)
            weights[i] = connections.get(i).getWeight();
        return weights;
    }
    
    // The activationFunction of the neuron
    public double activationFunction() {
        // This basic neuron has a linear activation function
        return load > 0 ? load : 0;        
    }
}
