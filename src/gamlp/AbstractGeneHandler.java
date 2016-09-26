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
public abstract class AbstractGeneHandler {
    public AbstractChromosome clone(AbstractChromosome parent) {
        return null;
    }
    
    public AbstractChromosome mutate(AbstractChromosome orig) {
        return null;
    }
    
    public AbstractChromosome recombine(AbstractChromosome parent1, AbstractChromosome parent2) {
        return null;
    }
}
