package com.ibm.humana.server.service.wa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.service.exception.NotFoundException;

@Component
public class WatsonConversationClient {
	private static final Logger logger = LoggerFactory.getLogger(WatsonConversationClient.class);
	
	private final String versionDate = "2018-07-10";
	private final String userName = "apikey";
//	private String password = "YVdgnFuYVvpfJKzLyNscMWtUqHy4ii4F3flSAicXL6Oh";	// humana hb workspace
//	private final String workspaceId = "5d5117fe-f3c9-4e16-9c0b-4daef28e24f8";
	private String password = "Z5PbcV16LhZ5s1WPIOsLnqxG3kLdmyP3P5lx6BKPuJM9";	// Jim demo workspace
	private final String workspaceId = "a8b89669-9c75-44ba-bb9d-8860748daa4e";	
	private final String endPoint = "https://gateway.watsonplatform.net/assistant/api";
	
	private final Conversation assistant;
	
	private static final int SEND_MESSAGE_RETRIES = 3;
	
	public WatsonConversationClient() {
		this.assistant = new Conversation(versionDate);
		
		assistant.setEndPoint(endPoint);
		assistant.setUsernameAndPassword(userName, password);
	}

	public MessageResponse send(MessageRequest messageRequest) {
		for (int tryNumber = 1; tryNumber <= SEND_MESSAGE_RETRIES; tryNumber++) {
			try {
				MessageOptions messageOptions = new MessageOptions.Builder(workspaceId)
						.input(messageRequest.getInput())
						.context(messageRequest.getContext())
						.build();
				MessageResponse messageResponse = assistant.message(messageOptions).execute();
				logger.info("messageResponse: {}", messageResponse);
				return messageResponse;
			} 
			catch (NotFoundException e ) {
				String errorMessage = String.format("Could not find WA workspace, using workspace id %s. Make sure this workspace id exists", workspaceId);
				logger.error(errorMessage);
				throw(e);
			} 
			catch (Exception e) {
				logger.warn("Watson assistance failed to respond on {} try", tryNumber);
				if (tryNumber >= SEND_MESSAGE_RETRIES) {
					logger.error("Maximum number of retries reached {}", SEND_MESSAGE_RETRIES);
					throw(e);
				}
			}
		}
		throw new IllegalStateException(); // should never reach this spot
	}
}
