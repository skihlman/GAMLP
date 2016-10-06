/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forecaster;

import helpFunction.SaveObject;
import GA.AbstractIndividual;

/**
 *
 * @author sebastian
 */
public abstract class AbstractIndividualFactory {
    public abstract AbstractIndividual create();
    public abstract AbstractIndividual create(SaveObject saveObject);
}
