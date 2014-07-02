/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author daniel
 */
public class SHA1 {

    public String transformSHA1(String input) throws NoSuchAlgorithmException {
        String name = clearInput(input);
        
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(name.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
    
    public String clearInput(String input){
        
        String[] temp;
        String delimiter = "/";
        temp = input.split(delimiter);
        
        return temp[temp.length-1];
    }
}
