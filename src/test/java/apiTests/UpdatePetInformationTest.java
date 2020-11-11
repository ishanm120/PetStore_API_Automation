package apiTests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.AddNewPet;
import api.UpdatePetInformationAPI;
import baseUtils.BaseAPI;
import commonUtils.CommonnConstants;
import io.restassured.response.Response;

public class UpdatePetInformationTest extends BaseAPI{
	
	AddNewPet addNewPet= new AddNewPet();
	UpdatePetInformationAPI updatePetInformationAPI= new UpdatePetInformationAPI();
	String addPetFilePath="//JSONFiles//AddPet.json";
	String updatePetInfoFilePath="//JSONFiles//UpdatePetInformation.json";
	
	
	@Test
	public void verifyPetInformationUpdateViaAPI_JSON() throws FileNotFoundException, IOException, ParseException {
		Response postResponse = addNewPet.addNewPetViaAPI(getDataFromJson(addPetFilePath));
		verifyStatusCode(postResponse, CommonnConstants.STATUS_CODE_200);
		Response getResponse= updatePetInformationAPI.updatePetInformation(getDataFromJson(updatePetInfoFilePath));
		verifyStatusCode(getResponse, CommonnConstants.STATUS_CODE_200);
		Assert.assertEquals(getParameterValueFromJSON(getResponse, CommonnConstants.PET_STATUS),pet.getPetStatus(), "Veify that pet Status Updated");
	}
}
