package com.ibm.humana.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.ibm.humana.server.service.wa.WatsonAssistantAPICallService;

@RestController
@RequestMapping("/watsonassistant")
public class WatsonAssistantController {
	
	@Autowired
	private WatsonAssistantAPICallService service;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(path = "/message")
	@ResponseBody
	public JsonObject watsonAssistantMessage(@RequestBody JsonObject lastResponse) {
		return service.post(lastResponse);
	}

}
