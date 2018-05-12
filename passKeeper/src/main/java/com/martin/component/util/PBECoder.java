package com.martin.component.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;


public class PBECoder {

    public static final String ALGORITHM="PBEWithSHA1AndDESede";
    public static final int ITERATION_COUNT=100;
 
    public static byte[] getSalt(String salt) throws Exception{
    	String tempsalt=salt;
    	while(tempsalt.length()<=8)
    	{
    		tempsalt+=tempsalt;
    	}
    	return Base64.encodeBase64URLSafe(tempsalt.getBytes("UTF-8"));
    }
    

    private static Key toKey(String password) throws Exception{
        PBEKeySpec keySpec=new PBEKeySpec(password.toCharArray());
        SecretKeyFactory keyFactory=SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey=keyFactory.generateSecret(keySpec);
        return secretKey;
    }

    public static byte[] encrypt(byte[] data,String password,byte[] salt) throws Exception{

        Key key=toKey(password);
        PBEParameterSpec paramSpec=new PBEParameterSpec(salt,ITERATION_COUNT);
        Cipher cipher=Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key,paramSpec);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data,String password,byte[] salt) throws Exception{

        Key key=toKey(password);
        PBEParameterSpec paramSpec=new PBEParameterSpec(salt,ITERATION_COUNT);
        Cipher cipher=Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key,paramSpec);
        return cipher.doFinal(data);
    }
    
    public static String encrypt(String data,String token,String salt) throws Exception{
        Key key=toKey(token);
        byte[] buf=getSalt(salt);
        PBEParameterSpec paramSpec=new PBEParameterSpec(buf,ITERATION_COUNT);
        Cipher cipher=Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key,paramSpec);
        buf=  cipher.doFinal(data.getBytes());
        return Base64.encodeBase64String(buf);
    }

    public static String decrypt(String data,String token,String salt) throws Exception{

        Key key=toKey(token);
        byte[] buf=getSalt(salt);
        PBEParameterSpec paramSpec=new PBEParameterSpec(buf,ITERATION_COUNT);
        Cipher cipher=Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key,paramSpec);
        byte[] decr= Base64.decodeBase64(data);
        buf= cipher.doFinal(decr);
        return new String(buf);
    }

}


