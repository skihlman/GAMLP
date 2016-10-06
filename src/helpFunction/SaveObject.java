/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpFunction;

import java.util.ArrayList;

/**
 *
 * @author sebastian
 */
public class SaveObject extends ArrayList<String> {
    private SaveInfo saveInfo;
    
    public SaveObject(SaveInfo info) {
        set(info);
    }
    
    private void set(SaveInfo info) {
        saveInfo = info;
    }
    
    public void setSaveInfo(SaveInfo info) {
        set(info);
    }
    
    public SaveInfo getSaveInfo() {
        return saveInfo;
    }
}
