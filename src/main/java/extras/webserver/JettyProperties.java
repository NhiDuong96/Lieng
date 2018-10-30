package extras.webserver;

import bitzero.framework.config.PropertiesLoader;

/**
 * Created by niennd on 1/13/2016.
 */
public class JettyProperties extends PropertiesLoader {
    static JettyProperties instance = new JettyProperties("config/jetty/jetty.properties");

    public JettyProperties(String fileDir) {
        super(fileDir);
    }

    public static JettyProperties getInstance() {
        return instance;
    }
}
