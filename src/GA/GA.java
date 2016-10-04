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
public class GA {
    // The population on whitch to perform the genetic alghoritm
    private Population population;
    private AbstractIndividualFactory factory;
    private GASettings settings;
    private int generation;
    
    public GA(AbstractIndividualFactory fact) {
        this(fact, new GASettings());
    }
    
    // Constructor
    public GA(AbstractIndividualFactory fact, GASettings set) {
        factory = fact;
        settings = set;
        generatePopulation();
    }
    
    public final void generatePopulation() {
        while (population.size() < settings.populationSize) {
            population.add(factory.create());
        }
        generation = 0;
    }
    
    public Population getPopulation() {
        return population;
    }
    
    public GASettings getSettings() {
        return settings;
    }
    
    public void setPopulation(Population pop) {
        this.population = pop;
        generation = 0;
    }
    
    public void set(GASettings set) {
        this.settings = set;
    }
    
    // Perform GA - i.e. a generation shift
    public void generationShift() {
        population.sort();
    }
    
    
}
