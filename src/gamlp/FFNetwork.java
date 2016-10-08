/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamlp;

import Data.DataSet;


/**
 *
 * @author sebastian
 */
public class FFNetwork extends NeuronNet{
    
    FFNetwork(int[] npl, DataSet sample) {
        super(new WeightArray(npl), sample);
    }
    
    
}
