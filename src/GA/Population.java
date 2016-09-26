/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GA;

import java.util.ArrayList;

/**
 *
 * @author sebastian
 */
public class Population extends ArrayList<AbstractIndividual> {
    // Sum of all individual's fitness
    private double fitnessSum;
    
    // 
    public void sort(){
        for (int i = 1; i < this.size(); i++) {
            int moveToIndex = i;
            while (this.get(moveToIndex).fitness() 
                    > this.get(moveToIndex - 1).fitness()) {
                moveToIndex--;
                
            }
            this.add(moveToIndex, this.remove(i));
        }
    }

    @Override
    public boolean add(AbstractIndividual e) {
        boolean success = super.add(e);
        addToFitnessSum(e.fitness());
        return success;
    }
    
    @Override
    public void add(int index, AbstractIndividual e) {
        super.add(index, e);
        addToFitnessSum(e.fitness());
    }
    
    @Override
    public AbstractIndividual remove(int index) {
        AbstractIndividual ind = super.remove(index);
        addToFitnessSum(-ind.fitness());
        return ind;
    }
    
    @Override
    public boolean remove(Object e) {
        boolean retVal = super.remove(e);
        updateFitnessSum();
        return retVal;
    }
    
    // Update the sum of all individual's fitness
    private void updateFitnessSum() {
        for (AbstractIndividual ind : this)
            fitnessSum += ind.fitness();
    }
    
    private void addToFitnessSum(double fitness) {
        fitnessSum += fitness;
    }
    
    // Return an individual based on p from a cumulative distribution of fitness
    private AbstractIndividual get(double p) {
        if (p < 0)
            return null;
        double fSum = 0;
        for (AbstractIndividual ind : this) {
            fSum += ind.fitness();
            if (p <= fSum / fitnessSum)
                return ind;
        }
        return null;
    }
}
