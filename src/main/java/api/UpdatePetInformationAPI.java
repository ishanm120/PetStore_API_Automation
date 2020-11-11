package api;


import org.json.simple.JSONObject;

import baseUtils.BaseAPI;
import commonUtils.CommonnConstants;
import io.restassured.response.Response;

public class UpdatePetInformationAPI extends BaseAPI{
	
	
	public UpdatePetInformationAPI() {
		super();
		setResourceURL("/v2/pet/");
	}
	
	public Response updatePetInformation(JSONObject jsonObject) {
		logger.info("Update the Pet with ID: "+ jsonObject.get(CommonnConstants.PET_ID).toString() + " with status: "+ jsonObject.get(CommonnConstants.PET_STATUS).toString());
		pet.setPetStatus(jsonObject.get(CommonnConstants.PET_STATUS).toString());
		return PUT(getResourceURL(), jsonObject.toString());
	}

}  
