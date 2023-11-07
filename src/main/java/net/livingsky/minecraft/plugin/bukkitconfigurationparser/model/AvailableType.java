package net.livingsky.minecraft.plugin.bukkitconfigurationparser.model;

import lombok.Getter;
import org.bukkit.Color;

import java.util.List;

/**
 * @author mikoto
 * &#064;date 2023/11/7
 * Create for bukkitConfigurationParser
 */
@Getter
public enum AvailableType {
    /**
     * Types available for configurations
     */
    String(java.lang.String.class),
    StringList(List.class),
    Int(Integer.class),
    IntList(List.class),
    Short(java.lang.Short.class),
    ShortList(List.class),
    Long(java.lang.Long.class),
    LongList(List.class),
    Double(java.lang.Double.class),
    DoubleList(List.class),
    Float(java.lang.Float.class),
    FloatList(List.class),
    Boolean(java.lang.Boolean.class),
    MapList(List.class)
    ;

    final Class<?> typeClass;

    AvailableType(Class<?> typeClass) {
        this.typeClass = typeClass;
    }
}
