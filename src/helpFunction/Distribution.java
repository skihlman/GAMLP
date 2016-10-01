/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpFunction;

/**
 *
 * @author sebastian
 */
public class Distribution {
    
    // get an equally distributed random number between min and max
    public static double equal(int min, int max) {
        return Math.random() * (max - min) + min;
    }
    
    public static int equalInt(int min, int max) {
        return (int)equal(min, max + 1);
    }
    
    // get a standard normally distributed random number through Marsaglia polar method 
    public static double normal() {
        double rnd1;
        double rnd2;
        double S;
        do {
            rnd1 = 2 * Math.random() - 1;
            rnd2 = 2 * Math.random() - 1;
            S = Math.pow(rnd1,  2) + Math.pow(rnd2, 2);
        } while (S >= 1);
        double retVal = rnd1 * Math.sqrt(-2 * Math.log(S) / S);
        if (new Double(retVal).isNaN())
            return 0;
        return retVal;
    }
    
    // get a general normal distributed random number with mean and sd
    public static double normal(double mean, double sd) {
        return mean + sd * normal();
    }
    
}
