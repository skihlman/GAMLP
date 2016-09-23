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
public class FFNetwork {
    private WeightArray weights;
    
    FFNetwork(Chromosome chromosome) {
        weights = new WeightArray(chromosome);
    }
    
    
}
