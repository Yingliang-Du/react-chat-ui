package com.ibm.humana.server.service.wa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.gson.JsonObject;

@SpringBootTest
class WatsonAssistantAPICallServiceTests {
	
	@Autowired
	private WatsonAssistantAPICallService service;

	@Test
	void testPost_flow() {
		// step 1: calling wa api the first time
		// pre-conditions
		JsonObject initialRequest = new JsonObject();
		// call business method
		JsonObject response = service.post(initialRequest);
		// verify results
		assertNotNull(response);
		
		// step 2: calling wa api use current response - input text: "Normal Blood Test"
		// pre-condition: set input text
		System.out.println("Current WA Response:");
		// Set input text
		response.getAsJsonObject("input").addProperty("text", "Normal Blood Test");
		System.out.print(response);
		// calling business method
		response = service.post(response);
		// verify results
		assertNotNull(response);
		
		// step 3: calling wa api use current response - input text: "TNR"
		// pre-condition: set input text
		System.out.println("Current WA Response:");
		// Set input text
		response.getAsJsonObject("input").addProperty("text", "TNR");
		System.out.print(response);
		// calling business method
		response = service.post(response);
		// verify results
		assertNotNull(response);
		
	}

}
