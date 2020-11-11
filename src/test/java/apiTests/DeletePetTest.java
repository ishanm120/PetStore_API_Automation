package apiTests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import api.AddNewPet;
import api.DeletePetAPI;
import api.PetDetailAPI;
import baseUtils.BaseAPI;
import commonUtils.CommonnConstants;
import io.restassured.response.Response;
import junit.framework.Assert;

public class DeletePetTest extends BaseAPI{
	
	DeletePetAPI deletePetAPI= new DeletePetAPI();
	AddNewPet addNewPet= new AddNewPet();
	PetDetailAPI petDetailApi = new PetDetailAPI();
	String addPetFilePath="//JSONFiles//AddPet.json";
	
	
	@Test
	public void verifyPetsDeletedViaAPI() throws FileNotFoundException, IOException, ParseException {
		Response postResponse = addNewPet.addNewPetViaAPI(getDataFromJson(addPetFilePath));
		verifyStatusCode(postResponse, CommonnConstants.STATUS_CODE_200);
		Response deleteResponse = deletePetAPI.deletePetViaAPI(pet.getPetID());
		verifyStatusCode(deleteResponse, CommonnConstants.STATUS_CODE_200);
		Response getResponse= petDetailApi.getUserDetailViaAPI(pet.getPetID());
		verifyStatusCode(getResponse, CommonnConstants.STATUS_CODE_404);
		Assert.assertEquals("Verify Message for deleted Pet", CommonnConstants.ERROR_MESSAGE_FOR_DELETED_PET, getParameterValueFromJSON(getResponse, CommonnConstants.PET_ERROR_MESSAGE_KEY));
	}

}
