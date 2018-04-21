package com.martin.component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HttpClientDemo {

	Logger logger = LoggerFactory.getLogger(HttpClientDemo.class);
	RequestConfig config=RequestConfig.custom().setConnectTimeout(1000).setSocketTimeout(3000).build();
	public void testGet() {
		try {
			CloseableHttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
			HttpGet request = new HttpGet("http://10.222.41.247:8080");
			HttpResponse response = httpclient.execute(request);
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer stringbuild = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				stringbuild.append(line);
			}
			logger.info(stringbuild.toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}
	

}
