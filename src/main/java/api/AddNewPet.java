package api;

import org.json.simple.JSONObject;

import baseUtils.BaseAPI;
import commonUtils.CommonnConstants;
import io.restassured.response.Response;

public class AddNewPet extends BaseAPI{
	
	
	public AddNewPet() {
		super();
		setResourceURL("/v2/pet");
	}
	
	public Response addNewPetViaAPI(JSONObject jsonObject) {
		pet.setPetID(jsonObject.get(CommonnConstants.PET_ID).toString());
		pet.setPetName(jsonObject.get(CommonnConstants.PET_NAME).toString());
		pet.setPetStatus(jsonObject.get(CommonnConstants.PET_STATUS).toString());
		return POST(getResourceURL(), jsonObject.toString());
	}

}
