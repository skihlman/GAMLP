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
    private double[] teachingInput;
    private int id;

    public TrainingObservation(double[] val, int teachingLength, int id) {
        super(Arrays.copyOf(val, val.length - teachingLength));
        int inpLen = super.get().length;
        int fullLen = val.length;
        teachingInput = Arrays.copyOfRange(val, inpLen, fullLen);
        this.id = id;
    }
    
}
