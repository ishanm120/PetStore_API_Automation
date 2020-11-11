package api;


import baseUtils.BaseAPI;
import io.restassured.response.Response;

public class DeletePetAPI extends BaseAPI{
	
	
	public DeletePetAPI() {
		super();
		setResourceURL("/v2/pet/");
	}
	
	public Response deletePetViaAPI(String petID) {
		pet.setPetID(petID);
		return DELETE(getResourceURL() + petID);
	}
}  
