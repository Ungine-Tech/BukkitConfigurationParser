package net.livingsky.minecraft.plugin.bukkitconfigurationparser.manager;

import net.livingsky.minecraft.plugin.bukkitconfigurationparser.annotation.Configuration;
import net.livingsky.minecraft.plugin.bukkitconfigurationparser.model.AvailableType;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author mikoto
 * &#064;date 2023/11/7
 * Create for bukkitConfigurationParser
 */
public class ConfigurationManager {
    private static final ConfigurationManager INSTANCE = new ConfigurationManager();

    private static final Map<String, Object> CONFIGURATION_MAP = new HashMap<>();
    private static final Map<String, ConfigurationInfo> CONFIGURATION_INFO_MAP = new HashMap<>();
    private static final AvailableType[] AVAILABLE_TYPES = AvailableType.values();
    public void doScan(String scanPackage) {
        if (scanPackage == null) {
            throw new RuntimeException("The scanPackage should not be null");
        }
        Reflections reflections = new Reflections(scanPackage);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Configuration.class);
        for (Class<?> clazz :
                classes) {
            configurationCheckIn(clazz);
        }
    }

    public void configurationCheckIn(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(Configuration.class)) {
            return;
        }

        Map<String, Class<?>> typesMap = getTypesMap(clazz);

        ConfigurationInfo configurationInfo = new ConfigurationInfo() {
            @Override
            public Class<?> getConfigurationClass() {
                return clazz;
            }

            @Override
            public Map<String, Class<?>> getTypesMap() {
                return typesMap;
            }
        };

        CONFIGURATION_INFO_MAP.put(clazz.getSimpleName(), configurationInfo);
    }

    @NotNull
    private static Map<String, Class<?>> getTypesMap(Class<?> clazz) {
        Field[] fields = clazz.getFields();
        Map<String, Class<?>> typesMap = new HashMap<>();

        for (Field field : fields) {
            Class<?> typeClass = field.getType();
            boolean flag = false;
            for (AvailableType availableType : AVAILABLE_TYPES) {
                if (typeClass.equals(availableType.getTypeClass())) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                typeClass = AvailableType.MapList.getTypeClass();
            }

            typesMap.put(field.getName(), typeClass);
        }
        return typesMap;
    }


    public interface ConfigurationInfo {
        /**
         * Get the Configuration class
         * @return Configuration class
         */
        Class<?> getConfigurationClass();

        /**
         * Get the Configuration name-types map
         * @return Configuration name-types map
         */
        Map<String, Class<?>> getTypesMap();
    }

    public static ConfigurationManager getInstance() {
        return INSTANCE;
    }

    public static Map<String, ConfigurationInfo> getConfigurationInfoMap() {
        return CONFIGURATION_INFO_MAP;
    }

    public static Map<String, Object> getConfigurationMap() {
        return CONFIGURATION_MAP;
    }
}
