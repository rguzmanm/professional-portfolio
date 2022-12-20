package challenge.rpachallenge.app;

import com.workfusion.odf.core.App;
import com.workfusion.odf.core.Module;
import groovy.lang.Binding;

import java.util.List;

public class RPAChallengeApp extends App {

    protected RPAChallengeApp(Binding context, List<Module> additionalModules, List<Module> overrideModules, Object injectContext) {
        super(context, additionalModules, overrideModules, injectContext);
    }

    public static RPAChallengeAppBuilder init(Binding binding) {
        return new RPAChallengeAppBuilder(binding);
    }
    
}
