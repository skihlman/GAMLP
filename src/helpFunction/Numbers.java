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
public class Numbers {
    public static boolean isNumeric(String str) {  
        try  {  
            double d = Double.parseDouble(str);  
        }  
        catch(NumberFormatException nfe) {  
            return false;  
        }  
        return true;  
    }
}
