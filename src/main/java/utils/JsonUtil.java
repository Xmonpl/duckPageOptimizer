package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import config.Config;
import config.ConfigManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;

public class JsonUtil
{
    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    public static <T> T readConfiguration(final Class<T> configurationClass, final File file) throws Exception {
        if (!file.exists()) {
            file.createNewFile();
            Files.write(file.toPath(), JsonUtil.gson.toJson(configurationClass.newInstance()).getBytes(StandardCharsets.UTF_8), new OpenOption[0]);
            System.exit(-1);
        }
        return JsonUtil.gson.fromJson(new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8), configurationClass);
    }

    public static <T> T readObjectFromFile(final Class<T> object, final File file) throws Exception {
        if (!file.exists()) {
            return null;
        }
        final BufferedReader reader = new BufferedReader(new FileReader(file));
        return JsonUtil.gson.fromJson(reader, object);
    }

    public static void writeObjectToFile(final Object object, final File file) throws Exception {
        if (!file.exists()) {
            file.createNewFile();
        }
        final FileWriter writer = new FileWriter(file);
        writer.write(JsonUtil.gson.toJson(object));
        writer.close();
    }

    public static void save() throws Exception {
        Config c = ConfigManager.getConfig();
        JsonUtil.writeObjectToFile(c, new File("config.json"));
    }
}
