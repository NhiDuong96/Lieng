package config;

import bitzero.framework.config.PropertiesLoader;

/**
 * Created by pc1 on 12/17/2018.
 */
public class GameProperties extends PropertiesLoader{
    static GameProperties instance = new GameProperties("res/game.properties");

    public GameProperties(String fileDir) {
        super(fileDir);
    }

    public static GameProperties getInstance() {
        return instance;
    }
}
