package api;


import baseUtils.BaseAPI;
import io.restassured.response.Response;

public class GetPetsWithStatus extends BaseAPI{
	
	
	public GetPetsWithStatus() {
		super();
		setResourceURL("/v2/pet/findByStatus?status=");
	}
	
	
	public Response getPetsWithStatusViaAPI(String petStatus) {
		pet.setPetStatus(petStatus);
		return GET(getResourceURL() + petStatus);
	}

}
