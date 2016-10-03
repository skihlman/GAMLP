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
public class InputNeuron extends Neuron {
    /*@Override
    public void push() {
        for (Connection con : connections) {
            con.push(super.getLoad(true));
        }
        clear();
    }*/
    
    // The activationFunction of the neuron
    @Override
    public double activationFunction() {
        // Input neurons just pass the value further
        return super.getLoad(true);        
    }
}
