/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamlp;

/**
 *
 * @author sebastian
 */
public class GAMLP_tester {
    
    public static void main(String[] args) {
        nonsenseTestCode();
    }
    
    // This method contains the current code to be tested. Change as you like
    public static void nonsenseTestCode() {
        String str = "110";
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int iVal = c - '0';
            sum += iVal * Math.pow(2, i);
        }
        System.out.println(sum);
    }
}
