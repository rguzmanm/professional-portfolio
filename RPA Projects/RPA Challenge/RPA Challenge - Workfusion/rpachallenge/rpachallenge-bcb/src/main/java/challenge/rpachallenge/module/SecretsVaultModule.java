package challenge.rpachallenge.module;

import org.codejargon.feather.Provides;

import com.workfusion.odf.core.Module;
import com.workfusion.rpa.core.plugin.PluginAdapterFactory;
import com.workfusion.rpa.core.plugin.security.SecretsVaultPluginAdapter;

public class SecretsVaultModule implements Module {

	@Provides
	public SecretsVaultPluginAdapter secretsVaultPluginAdapter(PluginAdapterFactory pluginAdapterFactory) {
		return pluginAdapterFactory.getPluginAdapter(PluginAdapterFactory.PluginsEnum.SECRETS_VAULT);
	}
}