/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpFunction;

/**
 *
 * @author sebastian
 */
public class FileHandler {
    
    public static void save(SaveObject saveObject) {
        // Save the saveObject according to it's .saveInfo
        
        
    }
    
    public static SaveObject load(SaveInfo saveInfo) {
        SaveObject loadObject = new SaveObject(saveInfo);
        
        // Load the file defined by saveInfo, line by line and add to loadObject
        
        return loadObject;
    }
}
