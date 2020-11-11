package baseUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;

import commonUtils.CommonUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import objects.Pet;

public class BaseAPI extends CommonUtils{


	public Properties prop;
	protected static String BASE_URL;
	private String resourceURL;
	private int statusCode;
	public static Pet pet = new Pet();
	
	
	public String getResourceURL() {
		return resourceURL;
	}

	public void setResourceURL(String resourceURL) {
		this.resourceURL = resourceURL;
	}
	
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

    
	public BaseAPI() {
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/config.properties");
			try {
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		BASE_URL = prop.getProperty("BaseUrl");
		RestAssured.baseURI = BASE_URL;
	}

	
	public Response POST(String resporceUrl, String payLoad) {
		logger.info("Payload is "+ payLoad);
		Response response = RestAssured.given().header("Content-Type", "application/json").body(payLoad).when()
				.post(resporceUrl);
		logger.info("Response of the request is : "+response.getBody().asString());
		setStatusCode(response.getStatusCode());
		return response;
	}
	
	public Response GET(String resporceUrl) {
		logger.info("Resource Url is "+ resporceUrl);
		Response response = RestAssured.given().header("Content-Type", "application/json").when()
				.get(resporceUrl);
		logger.info("Response of the request is : "+response.getBody().asString());
		return response;
	}
	
	public Response DELETE(String resporceUrl) {
		logger.info("Resource Url is "+ resporceUrl);
		Response response = RestAssured.given().header("Content-Type", "application/json").when()
				.delete(resporceUrl);
		logger.info("Response of the request is : "+response.getBody().asString());
		return response;
	}
	
	public Response PUT(String resporceUrl, String payLoad) {
		logger.info("Payload for put request is "+ payLoad);
		Response response = RestAssured.given().header("Content-Type", "application/json").body(payLoad).when()
				.put(resporceUrl);
		logger.info("Response of the request is : "+response.getBody().asString());
		setStatusCode(response.getStatusCode());
		return response;
	}
	
	public int getStatusCode(Response response) {
		return response.getStatusCode();
	}
	
	public void verifyStatusCode(Response response, int expectedStatusCode) {
		 Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Verify Status code of Response");
	}
}
