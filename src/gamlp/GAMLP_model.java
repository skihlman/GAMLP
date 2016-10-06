/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamlp;

import Forecaster.ForecastModel;
import helpFunction.SaveInfo;
import GA.*;

/**
 *
 * @author sebastian
 */
public class GAMLP_model extends ForecastModel {
    Population population;
    GA ga;

    public GAMLP_model(SaveInfo info) {
        super(info);
    }
    
    public void load(SaveInfo saveInfo) {
        
    }
    
    public void save(SaveInfo saveInfo) {
        
    }
    
    public void doGA() {
        
    }
    
    public void train() {
        
    }

    @Override
    public void save(String name, String URL) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
