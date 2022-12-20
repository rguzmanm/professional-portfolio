package challenge.rpachallenge.module;

import org.codejargon.feather.Provides;

import com.workfusion.odf.core.Module;
import challenge.rpachallenge.utils.RPAChallengeConstants;
import challenge.rpachallenge.config.RPAChallengeConfig;
import challenge.rpachallenge.config.SelectorsConfig;
import challenge.rpachallenge.config.DSConfigManager;
import com.workfusion.rpa.core.plugin.datastore.DatastorePluginAdapter;

public class ConfigurationModule implements Module{

	@Provides
	public DSConfigManager configManager(DatastorePluginAdapter dsPluginManager) {
		return new DSConfigManager(dsPluginManager);
	}
	
	@Provides
	public RPAChallengeConfig createRPAChallenge(DSConfigManager configManager) {
		return configManager.getRPAChallengeConfig(RPAChallengeConstants.CONFIG_DATASTORE);
	}

	@Provides
	public SelectorsConfig createSelectorsConfig(DSConfigManager configManager) {
		return configManager.getSelectorsConfig(RPAChallengeConstants.SELECTORS_DATASTORE);
	}

}
