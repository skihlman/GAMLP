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
public class GASettings {
    // Private vars
    private double pCrossover;
    private double pClone;
    private double pMutation;
    // population size
    public int populationSize;
    /*
    // Type of Crossover gene selction and the crossover sd used if random
    
    Non-random crossover sets a specific gene in the child as either of the
    parents respective genes. Random crossover sets the childs gene as a random 
    normally distributed value with mean at the average between the parents 
    genes and an standard deviation of 
    [randomCrossoverSD * (parent1_gene - parent2_gene)].
    A value between 0.1 and 1 is recommended for randomCrossoverSD. Higher
    values might lead to instable crossover with less convergence. Lower values
    will work but will deplete the genetic diversity
    */
    public double pCrossoverIsRandom; 
    public double randomCrossoverSD;
    // Mutation rates (general applys to all, specific only to mutated individuals)
    public double generalMutationRate;
    public double generalMutationSD;
    public double generalMutationPStructureChange;
    public double specificMutationRate;
    public double specificMutationSD;
    public double specificMutationPStructureChange;
    // Conditional limits for algorithm loop
    public int generationLimit;
    public int fitnessDecreaseGenerationLimit;
    public double maxFitnessGoal;
    public double avgFitnessGoal;
    // Conditional limits for surviving
    public double survivalFitnessLimit;
    public double survivalAvgFitnessFactorLimit;
    public double survivalPercentile;
    // Conditional limit for spreading genes (fertility)
    public double fertileFitnessLimit;
    public double fertileAvgFitnessFactorLimit;
    public double fertilePercentile;
    // State variables for conditions
    private int loopCondition;
    private int survivalCondition;
    private int fertilityCondition;
    public boolean useLoopGenerationLimit;
    public boolean useLoopFitnessDecreaseGenerationLimit;
    public boolean useLoopMaxFitnessGoal;
    public boolean useLoopAvgFitnessGoal;
    public boolean useSurvivalFitnessLimit;
    public boolean useSurvivalAvgFitnessFactorLimit;
    public boolean useSurvivalPercentile;
    public boolean useFertileFitnessLimit;
    public boolean useFertileAvgFitnessFactorLimit;
    public boolean useFertilePercentile;
    
    // Constants - Default values
    public static final double DEFAULT_P_CROSSOVER = 0.8;
    public static final double DEFAULT_P_CLONE = 0.18;    
    public static final double DEFAULT_P_MUTATION = 0.02;
    
    public static final int DEFAULT_POPULATION_SIZE = 100;
    
    public static final double DEFAULT_P_RANDOM_CROSSOVER = 0.67;
    public static final double DEFAULT_CROSSOVER_SD = 0.4;
    
    public static final double DEFAULT_GENERAL_MUTATION_RATE = 0.01;
    public static final double DEFAULT_GENERAL_MUTATION_SD = 0.01;
    public static final double DEFAULT_GENERAL_MUTATION_P_STRUCTURE_CHANGE = 0.001;
    
    public static final double DEFAULT_SPECIFIC_MUTATION_RATE = 0.04;
    public static final double DEFAULT_SPECIFIC_MUTATION_SD = 0.02;
    public static final double DEFAULT_SPECIFIC_MUTATION_P_STRUCTURE_CHANGE = 0.06;
    
    public static final int DEFAULT_GENERATION_LIMIT = 1000;
    public static final int DEFAULT_FITNESS_DECREASE_GENERATION_LIMIT = 10;
    public static final double DEFAULT_MAX_FITNESS_GOAL = 0;
    public static final double DEFAULT_AVG_FITNESS_GOAL = 0;
    
    public static final double DEFAULT_SURVIVAL_FITNESS_LIMIT = 0;
    public static final double DEFAULT_SURVIVAL_AVG_FITNESS_FACTOR_LIMIT = 0;
    public static final double DEFAULT_SURVIVAL_PERCENTILE = 0.5;
    
    public static final double DEFAULT_FERTILE_FITNESS_LIMIT = 0;
    public static final double DEFAULT_FERTILE_AVG_FITNESS_FACTOR_LIMIT = 0;
    public static final double DEFAULT_FERTILE_PERCENTILE = 0.5;
    
    // Enum constants
    public static final int ANY = 0;
    public static final int ALL = 1;
    
    public GASettings() {
        // Private main agorithm vars
        setGAProbabilities(DEFAULT_P_CROSSOVER, DEFAULT_P_CLONE, DEFAULT_P_MUTATION);
        // set size of population
        populationSize = DEFAULT_POPULATION_SIZE;
        // The crossover parameters
        pCrossoverIsRandom = DEFAULT_P_RANDOM_CROSSOVER; 
        randomCrossoverSD = DEFAULT_CROSSOVER_SD;
        // Mutation rates (general applys to all, specific only to mutated individuals)
        generalMutationRate = DEFAULT_GENERAL_MUTATION_RATE;
        generalMutationSD = DEFAULT_GENERAL_MUTATION_SD;
        generalMutationPStructureChange = DEFAULT_GENERAL_MUTATION_P_STRUCTURE_CHANGE;
        specificMutationRate = DEFAULT_SPECIFIC_MUTATION_RATE;
        specificMutationSD = DEFAULT_SPECIFIC_MUTATION_SD;
        specificMutationPStructureChange = DEFAULT_SPECIFIC_MUTATION_P_STRUCTURE_CHANGE;
        // Conditional limits for algorithm loop
        generationLimit = DEFAULT_GENERATION_LIMIT;
        fitnessDecreaseGenerationLimit = DEFAULT_FITNESS_DECREASE_GENERATION_LIMIT;
        maxFitnessGoal = DEFAULT_MAX_FITNESS_GOAL;
        avgFitnessGoal = DEFAULT_AVG_FITNESS_GOAL;
        // Conditional limits for surviving
        survivalFitnessLimit = DEFAULT_SURVIVAL_FITNESS_LIMIT;
        survivalAvgFitnessFactorLimit = DEFAULT_SURVIVAL_AVG_FITNESS_FACTOR_LIMIT;
        survivalPercentile = DEFAULT_SURVIVAL_PERCENTILE;
        // Conditional limit for spreading genes (fertility)
        fertileFitnessLimit = DEFAULT_FERTILE_FITNESS_LIMIT;
        fertileAvgFitnessFactorLimit = DEFAULT_FERTILE_AVG_FITNESS_FACTOR_LIMIT;
        fertilePercentile = DEFAULT_FERTILE_PERCENTILE;
        // State variables for conditions
        setLoopCondition(ANY);
        setSurvivalCondition(ANY);
        setFertilityCondition(ANY);
        
        setLoopCondition(true, false, false, false);
        setSurvivalCondition(false, false, true);
        setFertilityCondition(false, false, true);
    }

    // GA probabilities are always set together, to make probs sum up to 1
    public void setGAProbabilities(double newPCrossover, double newPClone, double newPMutation) {
        // sum makes the code work even if probabilities doesn't sum up to 1
        double sum = newPCrossover + newPClone + newPMutation;
        pCrossover = newPCrossover / sum;
        pClone = newPClone / sum;
        pMutation = newPMutation / sum;
    }

    public void setLoopCondition(int condition) {
        if (condition < ANY || condition > ALL)
            loopCondition = ANY;
        else
            loopCondition = condition;
    }

    public void setSurvivalCondition(int condition) {
        if (condition < ANY || condition > ALL)
            survivalCondition = ANY;
        else
            survivalCondition = condition;
    }

    public void setFertilityCondition(int condition) {
        if (condition < ANY || condition > ALL)
            fertilityCondition = ANY;
        else
            fertilityCondition = condition;
    }
    
    public void setLoopCondition(boolean loopGenerationLimit, 
            boolean loopFitnessDecreaseGenerationLimit, 
            boolean loopMaxFitnessGoal,
            boolean loopAvgFitnessGoal) {
        useLoopGenerationLimit = loopGenerationLimit;
        useLoopFitnessDecreaseGenerationLimit = loopFitnessDecreaseGenerationLimit;
        useLoopMaxFitnessGoal = loopMaxFitnessGoal;
        useLoopAvgFitnessGoal = loopAvgFitnessGoal;
    }

    public void setSurvivalCondition(boolean survivalFitnessLimit,
            boolean survivalAvgFitnessFactorLimit, boolean survivalPercentile) {
        useSurvivalFitnessLimit = survivalFitnessLimit;
        useSurvivalAvgFitnessFactorLimit = survivalAvgFitnessFactorLimit;
        useSurvivalPercentile = survivalFitnessLimit;
    }

    public void setFertilityCondition(boolean fertileFitnessLimit,
            boolean fertileAvgFitnessFactorLimit, boolean fertilePercentile) {
        useFertileFitnessLimit = fertileFitnessLimit;
        useFertileAvgFitnessFactorLimit = fertileAvgFitnessFactorLimit;
        useFertilePercentile = fertilePercentile;
    }
    
    public double pCrossover() {
        return pCrossover;
    }
    
    public double pClone() {
        return pClone;
    }
    
    public double pMutation() {
        return pMutation;
    }
    
    public int loopCondition() {
        return loopCondition;
    }
    
    public int survivalCondition() {
        return survivalCondition;
    }
    
    public int fertilityCondition() {
        return fertilityCondition;
    }

}
