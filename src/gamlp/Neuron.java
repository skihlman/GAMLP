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
        lastLoad = load;
        load = 0;
    }
    
    public double getLoad() {
        return load;
    }
    
    // The activationFunction of the neuron
    private double activationFunction() {
        // This basic neuron has a linear activation function
        return load > 0 ? load : 0;        
    }
}
