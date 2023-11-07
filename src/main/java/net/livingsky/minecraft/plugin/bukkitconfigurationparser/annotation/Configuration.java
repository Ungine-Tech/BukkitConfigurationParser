package net.livingsky.minecraft.plugin.bukkitconfigurationparser.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author mikoto
 * &#064;date 2023/11/7
 * Create for bukkitConfigurationParser
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Configuration {
}
