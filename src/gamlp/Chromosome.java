/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamlp;

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
