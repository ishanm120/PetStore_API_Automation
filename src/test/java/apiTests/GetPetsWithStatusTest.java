package apiTests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import api.GetPetsWithStatus;
import baseUtils.BaseAPI;
import commonUtils.CommonnConstants;
import io.restassured.response.Response;
import junit.framework.Assert;

public class GetPetsWithStatusTest extends BaseAPI{
	
	GetPetsWithStatus getPetWithStatus= new GetPetsWithStatus();
	
	
	@Test
	public void verifyPetsWithStatusViaAPI() throws FileNotFoundException, IOException, ParseException {
		Response postResponse = getPetWithStatus.getPetsWithStatusViaAPI(CommonnConstants.PET_STATUS_AVAILABLE);
		List<Object> petStatusObjects = getListOfJsonArrayFromResponse(postResponse, CommonnConstants.PET_STATUS);
		verifyStatusCode(postResponse, CommonnConstants.STATUS_CODE_200);
		Assert.assertTrue("All pets have status 'CommonnConstants.PET_STATUS_AVAILABLE'", verifyList(petStatusObjects, CommonnConstants.PET_STATUS_AVAILABLE));
	}

}
