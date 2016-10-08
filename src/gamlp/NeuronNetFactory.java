/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamlp;

import Data.DataSet;
import GA.AbstractIndividual;
import GA.AbstractIndividualFactory;
import helpFunction.SaveObject;

/**
 *
 * @author sebastian
 */
public class NeuronNetFactory extends AbstractIndividualFactory{
    private final int inputs;
    private final int outputs;
    private final DataSet trainingSample;
    
    
    public NeuronNetFactory(DataSet sample) {
        inputs = sample.inputLength();
        outputs = sample.teachingLength();
        trainingSample = sample;
    }
    
    
    @Override
    public NeuronNet create() {
        WeightArray weights = new WeightArray(inputs, outputs);
        NeuronNet net = new NeuronNet(weights, trainingSample);
        return net;
    }

    @Override
    public AbstractIndividual create(SaveObject saveObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
