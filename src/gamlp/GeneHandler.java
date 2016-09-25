/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamlp;

import static gamlp.Chromosome.NEXT_SEQUENCE;
import java.util.ArrayList;

/**
 *
 * @author sebastian
 */
public class GeneHandler {
    public static final int DEFAULT_GENE_LENGTH = 16;
    
    public static final double P_MUTATE_SHRINK = 0.2;
    public static final double P_MUTATE_GROW = 0.2;
    public static final double P_MUTATE_CHANGE = 0.6;
    
    // Generate a new NetChromosome based on a 'neurons per layer'-array
    public static Chromosome generate(int[] npl) {
        Chromosome newChromosome = new Chromosome();
        for (int l = 0; l < npl.length - 1; l++) {
            for (int n = 0; n <= npl[l]; n++) {
                for (int i = 0; i < npl[l+1]; i++) {
                    for (int b = 0; b < DEFAULT_GENE_LENGTH; b++)
                        newChromosome.add(zeroOrOne());
                    newChromosome.add(NEXT_SEQUENCE);
                }
                newChromosome.add(NEXT_SEQUENCE);
            }
            newChromosome.add(NEXT_SEQUENCE);
        }
        return newChromosome;
    }
    
    public static ArrayList<ArrayList<ArrayList<Double>>> getWeightArray(Chromosome chromosome) {
        ArrayList<ArrayList<ArrayList<Double>>> weights = new ArrayList<>();
        int layer = 0;
        int maxConnectionsInPrevLayer = 0;
        // Loop through layers
        while (!chromosome.sequenceShift()) {
            int neuronCount = 0;
            int maxConnectionsInThisLayer = 0;
            // Add layer
            weights.add(new ArrayList<ArrayList<Double>>());
            // Loopthrough neurons in layer
            while (!chromosome.sequenceShift()) {
                int connectionCount = 0;
                // Add new neurons only if there are connections to it from
                // previuos layer
                if (neuronCount < maxConnectionsInPrevLayer || layer == 0) {
                    // Add neuron
                    weights.get(layer).add(new ArrayList<Double>());
                    // Loop through connections from this neuron
                    while (!chromosome.sequenceShift()) {
                        weights.get(layer).get(neuronCount).add(chromosome.nextGene());
                        connectionCount++;
                    }
                }
                neuronCount++;
                // Keep track of the maximum number of connections in this layer
                if (connectionCount > maxConnectionsInThisLayer) 
                    maxConnectionsInThisLayer = connectionCount;
            }
            // Loop through previous layer and delete connections that don't 
            // connect to a neuron in this layer
            if (layer > 0) {
                for (ArrayList<Double> neuron : weights.get(layer - 1)) {
                    while (neuron.size() > neuronCount)
                        neuron.remove(neuronCount);                
                }
            }
            // Save the maximum number of connections in this layer before
            // moving to next layer
            maxConnectionsInPrevLayer = maxConnectionsInThisLayer;
            layer++;
        }
        return weights;
    }
    
    // Creates a new instance of the chromosome given as argument
    public static Chromosome clone(Chromosome parent) {
        return new Chromosome(parent.toString());
    }
    
    // Take a chromosome, mutate it, and return the mutated chromosome
    public static Chromosome mutate(Chromosome orig) {
        double p = Math.random();
        int index = (int)(Math.random() * orig.len());
        if (p < P_MUTATE_SHRINK) {
            // Shrink
            orig.subtract(index, 1);
        }
        else if (p < P_MUTATE_SHRINK + P_MUTATE_GROW) {
            // Grow
            orig.add(zeroOrOne(), index);
        }
        else {
            // Change a random bit
            orig.insert(zeroOrOne(), index);
        }
        
        return orig;
    }
    
    // Recombine two chromosomes
    public static Chromosome recombine(Chromosome parent1, Chromosome parent2) {
        String str = "";
        // Handle each gene
        while (!parent1.sequenceShift() || !parent2.sequenceShift()) {
            while (!parent1.sequenceShift() || !parent2.sequenceShift()) {
                while (!parent1.sequenceShift() || !parent2.sequenceShift()) {
                    String str1 = parent1.nextGeneStr();
                    String str2 = parent2.nextGeneStr();
                    str += BitString.combine(str1, str2);
                }
                if (parent1.sequenceShift() && parent2.sequenceShift()) {
                    parent1.shift();
                    parent2.shift();
                }
            }
            if (parent1.sequenceShift() && parent2.sequenceShift()) {
                parent1.shift();
                parent2.shift();
            }
        }
        parent1.restore();
        parent2.restore();
        return new Chromosome(str);
    }
    
    
    // Return '1' or '0' with equal probabilities
    public static String zeroOrOne() {
        if (Math.random() < 0.5)
            return "0";
        else
            return "1";
    }
}
