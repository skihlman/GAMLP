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
public abstract class AbstractGeneHandler {
    public abstract AbstractChromosome clone(AbstractChromosome parent);
    
    public abstract AbstractChromosome mutate(AbstractChromosome orig);
    
    public abstract AbstractChromosome recombine(AbstractChromosome parent1, AbstractChromosome parent2);
}
