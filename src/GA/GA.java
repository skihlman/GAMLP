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
public class GA {
    // The population on whitch to perform the genetic alghoritm
    private Population population;
    private GASettings settings;
    private int generation;
    
    public GA(Population pop) {
        this(pop, new GASettings());
    }
    
    // Constructor
    public GA(Population pop, GASettings set) {
        population = pop;
        settings = set;
        generation = 0;
    }
    
    public Population getPopulation() {
        return population;
    }
    
    public GASettings getSettings() {
        return settings;
    }
    
    public void setPopulation(Population pop) {
        this.population = pop;
        generation = 0;
    }
    
    public void set(GASettings set) {
        this.settings = set;
    }
}
