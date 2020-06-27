package com.ibm.humana.server.service.wa;

public class IntentConfidence {
	
	private final String name;
	private final double confidence; // in range [0,1]
	
	public IntentConfidence(String name, double confidence) {
		this.name = name;
		this.confidence = confidence;
	}

	public String getName() {
		return name;
	}

	public double getConfidence() {
		return confidence;
	}

}
