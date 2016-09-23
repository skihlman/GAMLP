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
            con.push(load);
        }
        lastLoad = load;
        load = 0;
    }
    
    
}
