/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OldUselessCode;

import GA.AbstractChromosome;
import java.util.ArrayList;

/**
 *
 * @author Sebastian Kihlman
 */
public class Chromosome extends BitString {
    public static final double SCALE = 1 / 65536;
    
    public static final String NEXT_SEQUENCE = "1111";
    
    public static final int BYTE_LENGTH = 8;
    public static final int GENE_SEPARATOR_LENGTH = NEXT_SEQUENCE.length();
    
    private static int position;
    
    
    public static final int DEFAULT_GENE_LENGTH = 16;
    
    public static final double P_MUTATE_SHRINK = 0.2;
    public static final double P_MUTATE_GROW = 0.2;
    public static final double P_MUTATE_CHANGE = 0.6;
    
    // construct the chromosome with an empty string
    Chromosome() {
        super();
        position = 0;
    }
    
    // Construct the chromosome with a string
    Chromosome(String str) {
        super(str);
        position = 0;
    }
 
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
    
    // Creates and returns a new instance of the chromosome given as argument
    public Chromosome clone(Chromosome parent) {
        return new Chromosome(parent.toString());
    }
    
    // Take a chromosome, mutate it, and return the mutated chromosome
    public Chromosome mutate(Chromosome orig) {
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
    public Chromosome recombine(Chromosome parent1, Chromosome parent2) {
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

    
    @Override
    public AbstractChromosome clone() {
        return this.clone((Chromosome)this);
    }

    @Override
    public AbstractChromosome mutate() {
        return this.mutate((Chromosome)this);
    }

    @Override
    public AbstractChromosome recombine(AbstractChromosome partner) {
        return this.recombine((Chromosome)this, (Chromosome)partner);
    }
    
    // push reading position forward 'steps'-number of steps
    private void push(int steps) {
        position += Math.min(steps, len() - position);
    }
    
    // Push reading position forward one step
    private void push() {
        push(1);
    }
    
    // Push reading position forward according to the length of 'gene separator'
    public void shift() {
        push(GENE_SEPARATOR_LENGTH);
    }
    
    // Check if the next bits equals the sequence shift operator
    public boolean sequenceShift() {
        if (len() - position < GENE_SEPARATOR_LENGTH)
            return false;
        String str = bitString.substring(position, position + GENE_SEPARATOR_LENGTH);
        return str.equals(NEXT_SEQUENCE);
    }
    
    // are there still genes (or shifts) to process?
    public boolean endOfChromosome() {
        return position < len();
    }
    
    // Get the next gene as a double
    public double nextGene() {
        return toDouble(nextGeneStr());
    }
    
    // Get the next gene as a string
    public String nextGeneStr()  {
        String str = "";
        while (!endOfChromosome() && !sequenceShift()) {
            str += bitString.substring(position, position + 1);
            push();
        }
        // Shift only if we actually got a new gene. 
        // Otherwise shifting must be done manually
        if (str.length() > 0)
            shift();
        return str;
    }
    
    // get the current position of the taperhead 'position'
    public int getPos() {
        return position;
    }
    
    // restore the reading position to zero
    public void restore() {
        position = 0;
    }
        
    // Convert a string to double scaled with SCALE
    public static double toDouble(String str) {
        int iVal = toInt(str);
        return (double)iVal * SCALE;        
    }
}
