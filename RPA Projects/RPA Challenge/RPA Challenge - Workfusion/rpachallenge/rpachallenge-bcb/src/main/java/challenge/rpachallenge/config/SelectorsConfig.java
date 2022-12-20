package challenge.rpachallenge.config;

import java.util.Map;

public class SelectorsConfig {
	
	private Map<String, String> configData;

	public SelectorsConfig(Map<String, String> configData) {
		this.configData = configData;
	}

	public String getProperty(String property) {
		return configData.get(property);
	}
		
}
