package com.martin.component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.martin.component.model.User;
import com.martin.component.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class PassKeeperController {
	private static Logger logger = LoggerFactory.getLogger(PassKeeperController.class);
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET, value = "/getAll", produces = "application/json")
	public List<User> GetAllUsers() {
		logger.info("receive GetAllUsers");
		return userService.GetAll();
	}

	@PostMapping("/insert")
	@ResponseBody
	public String InsertUser(@RequestBody User user) {
		logger.info("receive InsertUser");
		userService.Insert(user);
		return "{\"result\":  \"success\"}";
	}

	@PostMapping("/update")
	@ResponseBody
	public String UpdateUser(@RequestBody User user) {
		logger.info("receive UpdateUser");
		userService.Insert(user);
		return "{\"result\":  \"success\"}";
	}

	@PostMapping("/deleteTable")
	@ResponseBody
	public String DeleteUser(@RequestBody int tid) {
		logger.info("receive DeleteUser");
		userService.Delete(tid);
		return "{\"result\":  \"success\"}";
	}

	@GetMapping("/get_open_id")
	public String GetOpenID(String code) throws Exception {
		logger.info("receive GetOpenID:" + code);
		if (code == null || code == "") {
			return null;
		}
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + App_ID + "&secret=" + Secret + "&js_code="
				+ code + "&grant_type=authorization_code";

//		SSLContext sc = SSLContext.getInstance("SSL");
//		sc.init(null, new TrustManager[] { new X509TrustManager() {
//			public X509Certificate[] getAcceptedIssuers() {
//				return null;
//			}
//			public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
//			}
//
//			public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
//			}
//		} }, new java.security.SecureRandom());
//		Client client = ClientBuilder.newBuilder().sslContext(sc)
//				.hostnameVerifier(new HostnameVerifier() {
//					public boolean verify(String s, SSLSession sslSession) {
//						return true;
//					}
//				}).register(JacksonJsonProvider.class).build();
//		Response response = client.target(url).request().accept("application/json").get();
//		return response;
		
		HttpsURLConnection connection=setupConnection(url);
		BufferedReader rd = new BufferedReader(
	            new InputStreamReader(connection.getErrorStream()));
	    StringBuilder sb = new StringBuilder();
	    String line;
	    while ((line = rd.readLine()) != null) {
	        sb.append(line);
	    }
	    rd.close();
	    return sb.toString();
				
	}

	
	HttpsURLConnection setupConnection(String urlString){
		  SSLContext ctx=null;
		  try {
		    ctx=SSLContext.getInstance("TLS");
		  }
		 catch (  NoSuchAlgorithmException e1) {
		    e1.printStackTrace();
		  }
		  try {
		    ctx.init(new KeyManager[0],new TrustManager[] { new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				}
			} },new SecureRandom());
		  }
		 catch (  KeyManagementException e) {
		    e.printStackTrace();
		  }
		  SSLContext.setDefault(ctx);
		  URL url=null;
		  try {
		    url=new URL(urlString);
		  }
		 catch (  MalformedURLException e) {
		    e.printStackTrace();
		  }
		  HttpsURLConnection conn=null;
		  try {
		    conn=(HttpsURLConnection)url.openConnection();
		  }
		 catch (  IOException e1) {
		    e1.printStackTrace();
		  }
		  conn.setDoOutput(true);
		  conn.setDoInput(true);
		  conn.setHostnameVerifier(new HostnameVerifier(){
		    @Override public boolean verify(    String arg0,    SSLSession arg1){
		      return true;
		    }
		  }
		);
		  return conn;
		}
	
	private static String App_ID = "123";
	private static String Secret = "456";
	private static String Grant_type = "authorization_code";
}
