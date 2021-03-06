/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GA;

import static GA.GASettings.*;

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
    
    // Warning!! By calling this method the previous population is disgarded!
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
        Population fertilePopulation = getFertilePopulation();
        // ********************************************
        // * Perform the genetic algorithm here!!!    *
        // ********************************************
        kill();
        repopulate(fertilePopulation);
        
        // Increment the generation
        generation++;
    }
    
    // Get the part of the population that is fertile (as the base for next generation)
    private Population getFertilePopulation() {
        Population fertilePopulation = new Population();
        double avgFitness = population.averageFitness(true);
        boolean stop = false;
        int index = 0;
        /* 
         *  calculate how many individuals to inlude into the fertile population
         *  limits inclusive!
        */
        while (!stop && index < population.size()) {
            switch (settings.fertilityCondition()) {
                case ANY:
                    stop = (settings.useFertileAvgFitnessFactorLimit && 
                            population.get(index).fitness() < 
                            settings.fertileAvgFitnessFactorLimit * avgFitness)
                            && 
                            (settings.useFertileFitnessLimit && 
                            population.get(index).fitness() < 
                            settings.fertileFitnessLimit)
                            &&
                            (settings.useFertilePercentile && 
                            (double)index / population.size() > 
                            settings.fertilePercentile);
                    break;
                case ALL:
                    stop = (settings.useFertileAvgFitnessFactorLimit && 
                            population.get(index).fitness() < 
                            settings.fertileAvgFitnessFactorLimit * avgFitness)
                            || 
                            (settings.useFertileFitnessLimit && 
                            population.get(index).fitness() < 
                            settings.fertileFitnessLimit)
                            ||
                            (settings.useFertilePercentile && 
                            (double)index / population.size() > 
                            settings.fertilePercentile);
                    break;
                default:
                    // Should not happen... :)
                    
            }
            if (!stop)
                fertilePopulation.add(population.get(index));
            index++;
        }
        return fertilePopulation;
    }
    
    // Kill all individuals in the population, except the survivors
    private void kill() {
        double avgFitness = population.averageFitness(true);
        boolean stop = false;
        int index = population.size();
        // Kill (remove) individuals who doesn't "fit" according to constraints
        // Need to be better than constraints to survive
        while (!stop && index >= 0) {
            switch (settings.fertilityCondition()) {
                case ANY:
                    stop = (settings.useSurvivalAvgFitnessFactorLimit && 
                            population.get(index).fitness() > 
                            settings.survivalAvgFitnessFactorLimit * avgFitness)
                            || 
                            (settings.useSurvivalFitnessLimit && 
                            population.get(index).fitness() > 
                            settings.survivalFitnessLimit)
                            ||
                            (settings.useSurvivalPercentile && 
                            (double)index / population.size() < 
                            settings.survivalPercentile);
                    break;
                case ALL:
                    stop = (settings.useSurvivalAvgFitnessFactorLimit && 
                            population.get(index).fitness() > 
                            settings.survivalAvgFitnessFactorLimit * avgFitness)
                            && 
                            (settings.useSurvivalFitnessLimit && 
                            population.get(index).fitness() > 
                            settings.survivalFitnessLimit)
                            &&
                            (settings.useSurvivalPercentile && 
                            (double)index / population.size() < 
                            settings.survivalPercentile);
                    break;
                default:
                    // Should not happen... :)
                    
            }
            if (!stop)
                population.remove(index);
            index--;
        }
    }
    
    
    // Create new individuals
    private void repopulate(Population fertilePopulation) {
        // Ensure that the fertile population is big enough to accomodate crossover
        while (fertilePopulation.size() < 2) {
            fertilePopulation.add(factory.create());
        }
        // Repopulate the population until it reaches it's right size
        while (population.size() < settings.populationSize) {
            double rndPropagationType = Math.random();
            double rndParent = Math.random();
            AbstractChromosome chromosome = 
                        fertilePopulation.get(rndParent).getChromosome();
            AbstractChromosome childChromosome;
            if (rndPropagationType < settings.pCrossover()) {
                double rndParent2 = Math.random();
                /* We are not checking whether parent 2 is the same as parent 1, 
                which means parent 1 can have a child with itself. This is 
                basically the same as a clone. A check could be implemented, 
                but would eat resources. For large populations the effect is
                negligable
                */
                AbstractChromosome chromosome2 = 
                        fertilePopulation.get(rndParent2).getChromosome();
                childChromosome = chromosome.recombine(chromosome2, settings.pCrossoverIsRandom, settings.randomCrossoverSD);
                
            }
            else if (rndPropagationType < settings.pCrossover() + settings.pClone()) {
                // Cllone ... 
                childChromosome = chromosome.clone();
            }
            else {
                // Mutate ...
                childChromosome = chromosome.mutate(settings.specificMutationRate, 
                        settings.specificMutationSD, 
                        settings.specificMutationPStructureChange);
            }
            // Mutate lightly
            childChromosome.mutate(settings.generalMutationRate, 
                    settings.generalMutationSD, 
                    settings.generalMutationPStructureChange);
            // Make a new individual based on the child chromosome
            AbstractIndividual child = factory.create(childChromosome);
            // Add the child to the population
            population.add(child);
        }
        
        population.sort();
    }
}
