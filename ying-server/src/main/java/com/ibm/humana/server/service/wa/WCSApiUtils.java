package com.ibm.humana.server.service.wa;

import java.util.Optional;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class WCSApiUtils {
	
	public static final String INPUT_FIELD = "input";
	public static final String CONTEXT_FIELD = "context";
	public static final String INTENTS_FIELD = "intents";
	public static final String INTENTS_INTENT_FIELD = "intent";
	public static final String INTENTS_CONFIDENCE_FIELD = "confidence";
	public static final String INPUT_TEXT_FIELD = "text";
	
	/**
	 * Extract from wcsResponse the top intent with its confidence. 
	 * @param wcsResponse
	 * @return
	 */
	public static Optional<IntentConfidence> getTopIntent(JsonObject wcsResponse) {
		JsonElement intentsJsonElement = wcsResponse.get(INTENTS_FIELD);
		if (intentsJsonElement == null || intentsJsonElement.isJsonNull() || (!intentsJsonElement.isJsonArray()))
			return Optional.empty();
		JsonArray intentsJsonArray = intentsJsonElement.getAsJsonArray();
		if (intentsJsonArray.size() == 0)
			return Optional.empty();
		// top intent is the first intent in the list. List is ordered by descending confidence.
		JsonObject topIntentJsonObject = intentsJsonArray.get(0).getAsJsonObject();
		if ((!topIntentJsonObject.has(INTENTS_INTENT_FIELD)) || 
			(!topIntentJsonObject.has(INTENTS_CONFIDENCE_FIELD)))
			return Optional.empty();
		String intentName = topIntentJsonObject.get(INTENTS_INTENT_FIELD).getAsString();
		float intentConfidence = topIntentJsonObject.get(INTENTS_CONFIDENCE_FIELD).getAsFloat();
		IntentConfidence topIntent = new IntentConfidence(intentName, intentConfidence);
		return Optional.of(topIntent);
	}

	public static Optional<String> getInputText(JsonObject wcsResponse) {
		JsonElement inputText = wcsResponse.getAsJsonObject(INPUT_FIELD).get(INPUT_TEXT_FIELD);
		if (inputText == null ||inputText.isJsonNull())
			return Optional.empty();
		
		return Optional.of(inputText.getAsString());
	}

	public static Optional<String> getTextInput(JsonObject wcsResponse) {
		JsonElement resultJsonElement = wcsResponse.get(INPUT_FIELD);
		if(resultJsonElement != null && resultJsonElement.isJsonObject()) {
			JsonObject inputJsonObject = resultJsonElement.getAsJsonObject();
			resultJsonElement = inputJsonObject.get("text");
		}
		return Optional.ofNullable(resultJsonElement).map(JsonElement::getAsString);
	}
}
