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
        // If population was previously empty we reset generation
        if (population.isEmpty())
            generation = 0;
        // Fill the generation up with randomly generated individuals 
        while (population.size() < settings.populationSize) {
            population.add(factory.create());
        }
        
    }
    
    public int getGeneration() {
        return generation;
    }
    
    public Population getPopulation() {
        return population;
    }
    
    public GASettings getSettings() {
        return settings;
    }
    
    public void setPopulation(Population pop) {
        population = pop;
        population.sort();
        // Adjust the size of the new populatione if needed
        while (population.size() < settings.populationSize)
            population.add(factory.create());
        while (population.size() > settings.populationSize)
            population.remove(settings.populationSize);
        // As population doesn't know it's generation, set generation to zero
        generation = 0;
    }
    
    
    public void set(GASettings set) {
        this.settings = set;
    }
    
    // Perform GA - i.e. a generation shift
    public void generationShift() {
        population.sort();
        
        // ********************************************
        // * Perform the genetic algorithm here!!!    *
        // ********************************************
        
        // Increment the generation
        generation++;
    }
    
    
}
