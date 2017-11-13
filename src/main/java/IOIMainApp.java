import bot.IOIBot;
import config.IOIBotConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.symphonyoss.client.SymphonyClient;
import org.yaml.snakeyaml.Yaml;
import utils.SymphonyAuth;

import java.io.IOException;
import java.io.InputStream;

public class IOIMainApp {

    private IOIBotConfig config;
    private SymphonyClient symClient;
    private final Logger LOG = LoggerFactory.getLogger(IOIMainApp.class);
    private IOIBot ioiBot;

    public static void main(String [] args) {
        IOIMainApp app = new IOIMainApp();
    }

    public IOIMainApp() {
        Yaml yaml = new Yaml();
        try (InputStream in = IOIBotConfig.class
                .getResourceAsStream("/config.yml")) {
            config = yaml.loadAs(in, IOIBotConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            symClient = new SymphonyAuth().init(config);
            ioiBot = IOIBot.getInstance(symClient, config);
        } catch (Exception e) {
            LOG.error("error", e);
        }
    }
}
