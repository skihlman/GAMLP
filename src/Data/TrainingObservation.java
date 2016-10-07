/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.Arrays;

/**
 *
 * @author sebastian
 */
public class TrainingObservation extends Observation {
    private final double[] teachingInput;
    private final int id;

    public TrainingObservation(double[] val, int teachingLength, int id) {
        super(Arrays.copyOf(val, val.length - Math.min(val.length - 1, Math.max(1, teachingLength))));
        int inpLen = super.get().length;
        int fullLen = val.length;
        teachingInput = Arrays.copyOfRange(val, inpLen, fullLen);
        this.id = id;
    }
    
    
    public double[] getT() {
        return teachingInput;
    }
    
    public double getT(int index) {
        if (index >= 0 && index < teachingInput.length)
            return teachingInput[index];
        return 0;
    }
    
    public int getID() {
        return id;
    }
}
