package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient 
{
	//1.Create GET method without headers
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException 
	{ 
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); // http get request
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);// hitting the get url
		
		return closeableHttpResponse;	
	}
	//Method Overloading
		//2.Create GET method with headers
	public CloseableHttpResponse get(String url, HashMap<String,String> headerMap) throws ClientProtocolException, IOException 
	{ 
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); // http get request
		for(Map.Entry<String, String> i: headerMap.entrySet()) {
			httpget.addHeader(i.getKey(), i.getValue());
		}
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);// hitting the get url
		return closeableHttpResponse;	
	}
	
	//2.Create POST method with headers
	public CloseableHttpResponse post(String url, String entitystring,  HashMap<String,String> headerMap) throws IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url); // http post request
		httppost.setEntity(new StringEntity(entitystring));// define your payload
		
		// For headers:
		for(Map.Entry<String, String> i: headerMap.entrySet()) 
		{
			httppost.addHeader(i.getKey(), i.getValue());
		
		}
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httppost);// hitting the get url
		return closeableHttpResponse;	
	}
}
