package com.martin.component;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.apache.commons.codec.binary.Base64;


public class PBECoder {
    
    /**
     * JAVA6支持以下任意一种算法
     * PBEWITHMD5ANDDES
     * PBEWITHMD5ANDTRIPLEDES
     * PBEWITHSHAANDDESEDE
     * PBEWITHSHA1ANDRC2_40
     * PBKDF2WITHHMACSHA1
     * */
    public static final String ALGORITHM="PBEWithSHA1AndDESede";
    

    public static final int ITERATION_COUNT=100;
    

    public static byte[] initSalt() throws Exception{
//        SecureRandom random=new SecureRandom();
//        return random.generateSeed(8);
    	
    	return Base64.encodeBase64URLSafe("12341234".getBytes("UTF-8"));
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

}


