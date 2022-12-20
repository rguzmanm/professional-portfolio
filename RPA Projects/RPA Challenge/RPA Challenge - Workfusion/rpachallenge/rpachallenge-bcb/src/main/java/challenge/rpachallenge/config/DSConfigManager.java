package challenge.rpachallenge.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import challenge.rpachallenge.utils.RPAChallengeConstants;
import org.webharvest.runtime.processors.plugins.DbRowVariable;
import com.workfusion.rpa.core.plugin.datastore.DatastorePluginAdapter;

/**
 * This class sets the methods to read the configuration files using the datastore plugin adapter. There is one method per data store needed
 */
public class DSConfigManager {

	private DatastorePluginAdapter dataStorePluginAdapter;

	public DSConfigManager(DatastorePluginAdapter dataStorePluginAdapter) {
		this.dataStorePluginAdapter = dataStorePluginAdapter;
	}

	private Map<String, String> getDsProperties(String DSName) {

		List<DbRowVariable> dbRowVariables = dataStorePluginAdapter.selectQuery(DSName,RPAChallengeConstants.SELECT_PROPERTIES_QUERY);

		Map<String, String> properties = new HashMap<>();
		for (DbRowVariable row : dbRowVariables) {
			String key = row.get(0).toString();
			String value = row.get(1).toString();
			properties.put(key, value);
		}

		return properties;
	}

	public RPAChallengeConfig getRPAChallengeConfig(String dsName) {
		Map<String, String> properties = getDsProperties(dsName);
		return new RPAChallengeConfig(properties);
	}
	
	public SelectorsConfig getSelectorsConfig(String dsName) {
		Map<String, String> properties = getDsProperties(dsName);
		return new SelectorsConfig(properties);
	}
}
