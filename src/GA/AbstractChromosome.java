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
public abstract class AbstractChromosome {
    @Override
    public abstract AbstractChromosome clone();
    
    public abstract AbstractChromosome mutate(double rate, double sd, 
            double pStructureChange);
    
    public abstract AbstractChromosome recombine(AbstractChromosome partner, 
            double pRandom, double sd);
}
