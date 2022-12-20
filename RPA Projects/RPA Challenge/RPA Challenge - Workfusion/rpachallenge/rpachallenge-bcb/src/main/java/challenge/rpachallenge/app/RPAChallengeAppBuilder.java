package challenge.rpachallenge.app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.workfusion.odf.core.Module;
import groovy.lang.Binding;
import challenge.rpachallenge.module.ConfigurationModule;
import challenge.rpachallenge.module.SecretsVaultModule;

public class RPAChallengeAppBuilder {
    private Binding context;
    private Map<String, String> params = new HashMap<>();
    private List<Module> overrideModules;
    private List<Module> additionalModules;
    private Object injectContext;

    public RPAChallengeAppBuilder(Binding context) {
        this.context = context;
    }

    public RPAChallengeAppBuilder params(Map<String, String> params) {
        this.setParams(params);
        return this;
    }

    public RPAChallengeAppBuilder override(Module... modules) {
        overrideModules = Arrays.asList(modules);
        return this;
    }

    public RPAChallengeAppBuilder injectFields(Object context) {
        this.injectContext = context;
        return this;
    }

    public RPAChallengeApp get() {
    	
    	Module configurationModule = new ConfigurationModule();
    	Module secretsVaultModule = new SecretsVaultModule();
    	additionalModules = Arrays.asList(configurationModule, secretsVaultModule);
    	    	
        return new RPAChallengeApp(context, additionalModules, overrideModules, injectContext);
    }

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
}
