package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetApiTest extends TestBase 
{ 
	TestBase testbase;
	String serviceURL;
	String apiURL;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;


@BeforeMethod
	public void setUp() {
		testbase = new TestBase();
		serviceURL = prop.getProperty("URL");
		apiURL = prop.getProperty("serviceURL");
		
		url =serviceURL + apiURL;
	}
	@Test(priority = 1)
		public void get_api_Test_withoutheaders() throws ClientProtocolException, IOException
	{
			restClient = new RestClient();
			closeableHttpResponse = restClient.get(url);
			
			//1)Get Status Code
			int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
			System.out.println("Status Code =====> "+ statusCode);
			
			Assert.assertEquals(statusCode,Resopence_status_code_200, "Status Code is not 200");
			
			
			//2)Get JSON Response
			String responceString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
			
			JSONObject jsonObject = new JSONObject(responceString);
			System.out.println("Response JSON API : "+jsonObject);
			
			//Single value assertion
			
			String perPageValue = TestUtil.getValueByJPath(jsonObject, "/per_page");
			System.out.println("Value of perpage is "+perPageValue);
			
			Assert.assertEquals(Integer.parseInt(perPageValue), 6);
			
			String totalValue = TestUtil.getValueByJPath(jsonObject, "/total");
			System.out.println("Value of total is "+totalValue);
			
			Assert.assertEquals(Integer.parseInt(totalValue), 12);
			
			// Json Array assertion
			String last_name = TestUtil.getValueByJPath(jsonObject, "/data[0]/last_name");
			String id = TestUtil.getValueByJPath(jsonObject, "/data[0]/id");
			String avatar = TestUtil.getValueByJPath(jsonObject, "/data[0]/avatar");
			String first_name = TestUtil.getValueByJPath(jsonObject, "/data[0]/first_name");
			String email = TestUtil.getValueByJPath(jsonObject, "/data[0]/email");
			System.out.println(last_name);
			System.out.println(id);
			System.out.println(avatar);
			System.out.println(first_name);
			System.out.println(email);
			
			//3)Print the Headers
			Header [] headersArray = closeableHttpResponse.getAllHeaders();
			
			HashMap<String, String> allHeaders = new HashMap<String, String>();
			
			for(Header i: headersArray) {
				allHeaders.put(i.getName(), i.getValue());
			}
			System.out.println("Headers Array"+allHeaders);
			
			
	}
	@Test(priority = 2)
	public void get_api_Test_withheaders() throws ClientProtocolException, IOException
{
		restClient = new RestClient();
		
		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");
		
		closeableHttpResponse = restClient.get(url,headerMap);
		
		//1)Get Status Code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code =====> "+ statusCode);
		
		Assert.assertEquals(statusCode,Resopence_status_code_200, "Status Code is not 200");
		
		
		//2)Get JSON Response
		String responceString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		
		JSONObject jsonObject = new JSONObject(responceString);
		System.out.println("Response JSON API : "+jsonObject);
		
		//Single value assertion
		
		String perPageValue = TestUtil.getValueByJPath(jsonObject, "/per_page");
		System.out.println("Value of perpage is "+perPageValue);
		
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		
		String totalValue = TestUtil.getValueByJPath(jsonObject, "/total");
		System.out.println("Value of total is "+totalValue);
		
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		
		// Json Array assertion
		String last_name = TestUtil.getValueByJPath(jsonObject, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(jsonObject, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(jsonObject, "/data[0]/avatar");
		String first_name = TestUtil.getValueByJPath(jsonObject, "/data[0]/first_name");
		String email = TestUtil.getValueByJPath(jsonObject, "/data[0]/email");
		System.out.println(last_name);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(first_name);
		System.out.println(email);
		
		//3)Print the Headers
		Header [] headersArray = closeableHttpResponse.getAllHeaders();
		
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		
		for(Header i: headersArray) {
			allHeaders.put(i.getName(), i.getValue());
		}
		System.out.println("Headers Array"+allHeaders);
		
		
}
		
}
