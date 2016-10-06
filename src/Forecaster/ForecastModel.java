/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forecaster;

import helpFunction.SaveInfo;

/**
 *
 * @author sebastian
 */
public abstract class ForecastModel {
    private SaveInfo saveInfo;
    
    public ForecastModel(SaveInfo info) {
        
    }
    
    public abstract void save(String name, String URL);
    
    public SaveInfo getSaveInfo() {
        return saveInfo;
    }
    
    public void setSaveInfo(SaveInfo newSaveInfo) {
        saveInfo = newSaveInfo;
    }
    
    
}
