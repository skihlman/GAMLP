/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GA;

/**
 *
 * @author sebastian
 */
public abstract class AbstractIndividual {
    public double[] constraints;
    
    public AbstractIndividual() {
        constraints = new double[0];
        setConstraints();
    }
    
    public abstract double fitness();
    
    public abstract boolean isViable(double[] constraints);
    
    public abstract void setConstraints();
}
