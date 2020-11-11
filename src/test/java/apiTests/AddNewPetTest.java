package apiTests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.AddNewPet;
import api.PetDetailAPI;
import baseUtils.BaseAPI;
import commonUtils.CommonnConstants;
import io.restassured.response.Response;

public class AddNewPetTest extends BaseAPI{
	
	AddNewPet addNewPet= new AddNewPet();
	PetDetailAPI petDetailApi = new PetDetailAPI();
	String addPetFilePath="//JSONFiles//AddPet.json";
	
	
	@Test
	public void verifyPetIsAddedViaAPI() throws FileNotFoundException, IOException, ParseException {
		Response postResponse = addNewPet.addNewPetViaAPI(getDataFromJson(addPetFilePath));
		verifyStatusCode(postResponse, CommonnConstants.STATUS_CODE_200);
		Response getResponse= petDetailApi.getUserDetailViaAPI(pet.getPetID());
		verifyStatusCode(getResponse, CommonnConstants.STATUS_CODE_200);
		Assert.assertEquals(getParameterValueFromJSON(getResponse, CommonnConstants.PET_NAME),pet.getPetName(), "Veify that Newly added Pet exists");
	}
}
