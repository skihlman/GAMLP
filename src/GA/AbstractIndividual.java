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
    
    public abstract double fitness();
    
    public abstract boolean isViable();
    
    public abstract AbstractChromosome getChromosome();
    
}
