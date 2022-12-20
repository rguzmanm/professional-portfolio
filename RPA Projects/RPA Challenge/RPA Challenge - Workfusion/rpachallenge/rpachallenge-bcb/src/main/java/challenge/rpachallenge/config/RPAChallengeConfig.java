package challenge.rpachallenge.config;

import java.util.Map;


public class RPAChallengeConfig{
		
	private Map<String, String> configData;

	public RPAChallengeConfig(Map<String, String> configData) {
		this.configData = configData;
	}

	public String getProperty(String property) {
		return configData.get(property);
	}
}
