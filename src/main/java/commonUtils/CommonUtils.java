package commonUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CommonUtils {
	
	public static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getRootLogger();
	
	/**
	 * Read data from JSON file
	 * @param filePath
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public JSONObject getDataFromJson(String filePath) throws FileNotFoundException, IOException, ParseException {
		Object object = new JSONParser()
				.parse(new FileReader(System.getProperty("user.dir") + filePath));
		return (JSONObject) object;
	}
	
	/**
	 * Get parameter value from JSON file
	 * @param response
	 * @param parameterKey
	 * @return
	 */
	public String getParameterValueFromJSON(Response response, String parameterKey) {
		 JsonPath jsonPathEvaluator = response.jsonPath();
		 String parameterValue= jsonPathEvaluator.getString(parameterKey);
		 logger.info("Given parameterkay: '"+parameterKey+"' has value as '"+parameterValue+"'");
		 return parameterValue;
	}
	
	/**
	 * Get list of Array from JSON response
	 * @param response
	 * @param arrayName
	 * @return
	 */
	public List<Object> getListOfJsonArrayFromResponse(Response response,String arrayName){
		List<Object> jsonResponse=null;
		try {
			jsonResponse = response.jsonPath().getList(arrayName);
		}
		catch(Exception e) {
			Assert.fail(e.getClass().getSimpleName()+" getListofAttributeTypeFromResponse method failed");
		}
		return jsonResponse ;
	}
	
	/**
	 * Verify List data
	 * @param listObjects
	 * @param expectedValue
	 * @return
	 */
	public boolean verifyList(List<Object> listObjects, String expectedValue) {
		boolean isTrue= true;
		for (Object object : listObjects) {
			if(!object.toString().equalsIgnoreCase(expectedValue)) {
				isTrue= false;
				return isTrue;
			}
		}
		return isTrue;
	}

}
