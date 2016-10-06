/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author sebastian
 */
public class Observation {
    private double[] value;
    
    public Observation(double[] val) {
        value = val;
    }
    
    public double[] get() {
        return value;
    }
    
    public double get(int index) {
        if (index >= 0 && index < value.length)
            return value[index];
        return 0;
    }
}
