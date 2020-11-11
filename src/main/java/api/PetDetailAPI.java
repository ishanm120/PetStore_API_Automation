package api;


import baseUtils.BaseAPI;
import io.restassured.response.Response;

public class PetDetailAPI extends BaseAPI{
	
	
	public PetDetailAPI() {
		super();
		setResourceURL("/v2/pet/");
	}
	
	//Send UserID as parameter
	public Response getUserDetailViaAPI(String petID) {
		return GET(getResourceURL() + petID);
	}
}  
