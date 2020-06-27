package com.ibm.humana.server.service.wa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.gson.JsonObject;
import com.ibm.humana.server.service.wa.WatsonConversationClient;
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

@SpringBootTest
class WatsonConversationClientTests {
	
	@Autowired
	private WatsonConversationClient client;

	@Test
	void testSend() {
		// Setup preconditions
		MessageRequest messageRequest = this.buildMessageRequest();
		
		// Call business method
		MessageResponse messageResponse = client.send(messageRequest);
		
		// Verify results
		assertNotNull(messageResponse);
	}
	
	private MessageRequest buildMessageRequest() {
		MessageRequest messageRequest = new MessageRequest();
		messageRequest.setInput(new InputData.Builder("").build());
//		messageRequest.setContext(gson.fromJson(contextJsonObject, new TypeToken<Context>(){}.getType()));
		messageRequest.setOutput(null);
		return messageRequest;	
	}

}
