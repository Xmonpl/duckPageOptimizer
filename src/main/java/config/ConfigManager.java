package config;

import utils.JsonUtil;

import java.io.File;

public class ConfigManager {
    private static Config botConfig;

    public ConfigManager() {
        File f = new File("config.json");
        try {
            botConfig = JsonUtil.readConfiguration(Config.class, f);
        } catch (Exception ex) {
            botConfig = null;
        }
    }

    public static Config getConfig() {
        return ConfigManager.botConfig;
    }
}
