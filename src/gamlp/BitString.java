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
public class BitString extends AbstractChromosome {
    String bitString;
    
    BitString() {
        this("");
    }
    
    BitString(String str) {
        bitString = str;
    } 
    
    // Add a new string at position, shifting remaining string forward
    public void add(String str, int pos) {
        String str1 = bitString.substring(0, pos);
        String str2 = bitString.substring(pos);
        bitString = str1 + str + str2;
    }
    
    // subtract 'len' number of digits from the string at 'pos'
    public void subtract(int pos, int len) {
        pos = Math.min(len(), pos);
        len = Math.min(len() - pos, len);
        String str1 = bitString.substring(0, pos);
        String str2 = bitString.substring(pos + len);
        bitString = str1 + str2;
    }
    
    // Insert a new string at 'pos', overwriting what is at that position
    public void insert(String str, int pos) {
        pos = Math.min(len(), pos);
        int len = Math.min(len() - pos, str.length());
        String str1 = bitString.substring(0, pos);
        String str2 = bitString.substring(pos + len);
        bitString = str1 + str + str2;
    }
    
    // Add a new string to the end of the current string
    public void add(String str) {
        add(str, len());
    }
    
    // Length of bitString 
    public int len() {
        return bitString.length();
    }
    
    @Override
    public String toString() {
        return bitString.intern();
    }
    
    // Converts the first 32 bits in bitString to an integer
    public int toInt() {
        int maxLen = Math.max(32, bitString.length());
        return toInt(bitString.substring(0, maxLen));
    }
    
    // === Static help methods =======================================
    
    // Make a combination of two strings
    public static String combine(String str1, String str2) {
        String str = "";
        int len1 = str1.length();
        int len2 = str2.length();
        for (int pos = 0; pos < Math.max(len1, len2); pos++) {
            String s;
            if (Math.random() < 0.5)
                s = (pos < len1) ? str1.substring(pos, pos + 1) : "";
            else
                s = (pos < len2) ? str2.substring(pos, pos + 1) : "";
            str += s;
        }
        return str;
    }
    
    // Convert a char to 1 or 0
    public static int charToBit(char chr) {
        return (chr - '0') % 2;
    }
    
    // Convert a string of bits to an integer
    public static int toInt(String str) {
        // If only sign bit return 0
        if (str.length() < 2)
            return 0;
        int sum = 0;
        int sign = charToBit(str.charAt(0));
        for (int i = 1; i < str.length(); i++)
            sum += charToBit(str.charAt(i)) * Math.pow(2, i);
        return (1 - 2 * sign) * sum;
    }
    
}
