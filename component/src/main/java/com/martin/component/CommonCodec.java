package com.martin.component;

import java.io.IOException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class CommonCodec {
	public static String ENCODING="UTF-8";
	
	public static String base64Encode(String message) throws IOException {
		byte[] buff=Base64.encodeBase64(message.getBytes(ENCODING));
		return new String(buff,ENCODING);
	}
	
	public static String base64Decode(String message) throws IOException {
		byte[] buff=Base64.decodeBase64(message.getBytes(ENCODING));
		return new String(buff,ENCODING);
	}
	
	
	
}
