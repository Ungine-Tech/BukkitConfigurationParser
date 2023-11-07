package net.livingsky.minecraft.plugin.bukkitconfigurationparser.test;

import net.livingsky.minecraft.plugin.bukkitconfigurationparser.manager.ConfigurationManager;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @author mikoto
 * &#064;date 2023/11/7
 * Create for bukkitConfigurationParser
 */
public class ConfigurationManagerTest {
    @Test
    public void configTest() {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();

        configurationManager.doScan("net.livingsky.minecraft.plugin.bukkitconfigurationparser.model");

        Map<String, ConfigurationManager.ConfigurationInfo> classMap = ConfigurationManager.getConfigurationInfoMap();

        System.out.println(classMap);
    }
}
