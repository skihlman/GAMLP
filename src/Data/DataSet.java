/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;


import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author sebastian
 */
public class DataSet extends ArrayList<TrainingObservation> {
    private String[] varNames;
    private int nextID;
    private int observationLen;
    private int teachingLen;
    
    public DataSet(String[] varNames, int tLen) throws Exception{
        if (tLen >= varNames.length) {
            Exception e = new Exception("Length of teaching array exceeds number of variables in dataset");
            throw e;
        }
        this.varNames = varNames;
        observationLen = varNames.length;
        teachingLen = tLen;
    }

    @Override
    public void add(int index, TrainingObservation element) {
        this.add(element);
    }

    @Override
    public boolean add(TrainingObservation e) {
        if (isAdequate(e)) {
            boolean retVal = super.add(e);
            nextID = Math.max(e.getID() + 1, nextID);
            sort();
            return retVal;
        }
        return false;
    }
    
    public boolean add(double[] val, int id) {
        TrainingObservation obs = new TrainingObservation(val, teachingLen, id);
        return add(obs);
    }
    
    public boolean add(double[] val) {
        int id;
        if (val.length == observationLen + 1) { // Use first number as id
            id = (int)val[0];
            val = Arrays.copyOfRange(val, 1, val.length - 1);
        }
        else // Use internal next id
            id = nextID();
        TrainingObservation obs = new TrainingObservation(val, teachingLen, id);
        return add(obs);
    }
        
    private int nextID() {
        int retVal = this.nextID;
        this.nextID++;
        return retVal;
    }
        
    // Sort the data sample in descending order according to each observation's id
    public void sort() {
        for (int i = 1; i < this.size(); i++) {
            int moveToIndex = i;
            while (this.get(moveToIndex).getID()
                    < this.get(moveToIndex - 1).getID()) {
                moveToIndex--;
            }
            this.add(moveToIndex, this.remove(i));
        }
    }
    
    public int getNextUnusedID() {
        return nextID;
    }
    
    private boolean isAdequate(TrainingObservation obs) {
        int tLen = obs.getT().length;
        int len = obs.get().length + tLen;
        return len == observationLen && tLen == teachingLen;
    }
    
    public int length() {
        return observationLen;
    }
    
    public int inputLength() {
        return observationLen - teachingLen;
    }
    
    public int teachingLength() {
        return teachingLen;
    }
}
