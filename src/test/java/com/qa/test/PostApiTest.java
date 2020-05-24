package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostApiTest extends TestBase
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
@Test
public void postApiTest_withheaders() throws ClientProtocolException, IOException 
{
	restClient = new RestClient();
	HashMap<String,String> headerMap = new HashMap<String,String>();
	headerMap.put("Content-Type", "application/json");
	
	//Jackson API
	ObjectMapper mapper = new ObjectMapper();
	Users users = new Users("Shashi","Mass_Leader");
	
	//Object to json file conversion
	mapper.writeValue(new File("\\Users\\shash\\Documents\\Edureka_Workspace\\restapi\\src\\main\\java\\com\\qa\\data\\users.json"), users);
	
	//Object to json in string format
	String jsonUserString = mapper.writeValueAsString(users);
	System.out.println(jsonUserString);
	
	closeableHttpResponse = restClient.post(url, jsonUserString, headerMap);
	
	int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
	Assert.assertEquals(statusCode, testbase.Resopence_status_code_201);
	
	//2)Get JSON Response
			String responceString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
			
			JSONObject jsonObject = new JSONObject(responceString);
			System.out.println("Response JSON API : "+jsonObject);
			
			//json to java object
			Users userRestObj = mapper.readValue(responceString, Users.class);
			System.out.println(userRestObj);
			
			Assert.assertTrue(users.getName().equals(userRestObj.getName()));
			//Assert.assertTrue(users.getId().equals(userRestObj.getId()));
			Assert.assertTrue(users.getJob().equals(userRestObj.getJob()));
			//Assert.assertTrue(users.getCreatedAt().equals(userRestObj.getCreatedAt()));
			
			System.out.println(userRestObj.getId());
			System.out.println(userRestObj.getCreatedAt());			
			
			
			

}

}
