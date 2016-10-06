/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamlp;

import GA.AbstractIndividual;
import Forecaster.AbstractIndividualFactory;
import helpFunction.SaveObject;

/**
 *
 * @author sebastian
 */
public class NeuronNetFactory extends AbstractIndividualFactory{
    private int inputs;
    private int outputs;
    
    public NeuronNetFactory() {
        this(1, 1);
    }
    
    public NeuronNetFactory(int in, int out) {
        inputs = in;
        outputs = out;
    }
    
    public void setInputs(int in) {
        inputs = in;
    }
    
    public void setOutputs(int out) {
        outputs = out;
    }
    
    @Override
    public NeuronNet create() {
        WeightArray weights = new WeightArray(inputs, outputs);
        NeuronNet net = new NeuronNet(weights);
        return net;
    }

    @Override
    public AbstractIndividual create(SaveObject saveObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
