/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamlp;

/**
 *
 * @author sebastian
 */
public class Connection {
    private double weight;
    private Neuron target;
    
    Connection(double w, Neuron t) {
        this.weight = w;
        this.target = t;
    }
    
    public void push(double load) {
        target.addLoad(load * weight);
    }
}
