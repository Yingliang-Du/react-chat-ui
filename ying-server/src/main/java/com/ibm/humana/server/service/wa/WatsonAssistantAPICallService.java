package com.ibm.humana.server.service.wa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.ibm.watson.developer_cloud.conversation.v1.model.Context;
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;

@Service
public class WatsonAssistantAPICallService {
	
	@Autowired
	private WatsonConversationClient watsonConversationClient;
	
	private final Gson gson = new Gson();


	public JsonObject post(JsonObject currWcsResponseObject) {
		MessageRequest waMessage = buildMessageRequest(currWcsResponseObject);
		return gson.toJsonTree(watsonConversationClient.send(waMessage)).getAsJsonObject();
	}
	
	private MessageRequest buildMessageRequest(JsonObject currWcsResponseObject) {
		JsonObject contextJsonObject = currWcsResponseObject.getAsJsonObject(WCSApiUtils.CONTEXT_FIELD);
		MessageRequest messageRequest = new MessageRequest();
		messageRequest.setInput(new InputData.Builder(WCSApiUtils.getTextInput(currWcsResponseObject).orElse("")).build());
		messageRequest.setContext(gson.fromJson(contextJsonObject, new TypeToken<Context>(){}.getType()));
		messageRequest.setOutput(null);
		return messageRequest;	
	}
}
