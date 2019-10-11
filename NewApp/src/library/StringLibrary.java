/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Minh_PC
 */
public class StringLibrary {

    
    
    
    public static boolean isPassword(String password) {
        String regex = "^[a-zZ-Z]([a-zA-Z0-9!@#$%^&]{5,29})";
        return password.matches(regex);
    }
    
    public static String MD5(String password) {
		MessageDigest mdDigest=null;
		try {
			//chon kieu ma hoa la md5
			mdDigest= MessageDigest.getInstance("MD5");
			// convert tu password sang 1 mang bbyte
			byte[]messages=mdDigest.digest(password.getBytes());
			// thuat toan md5 => convert hexa 16 tung ki tu
			BigInteger number=new BigInteger(1,messages); ///mang byte, 1: ma hoa tung ki tu thi ma hoa,
			/*
			 * vi du 123, 1->12345, 2->12345
			 * 
			 */
			
			return number.toString(16);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return password;
	}
}
