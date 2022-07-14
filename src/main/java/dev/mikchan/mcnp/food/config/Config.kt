package dev.mikchan.mcnp.food.config

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.plugin.java.JavaPlugin

internal class Config(private val plugin: JavaPlugin) : IConfig {
    override var enabled: Boolean = false
    override var modifications: Set<IModification> = emptySet()

    init {
        plugin.saveDefaultConfig()
        load(plugin.config)
    }

    override fun reload() {
        plugin.reloadConfig()
        val config = plugin.config
        load(config)
    }

    private fun load(config: FileConfiguration) {
        enabled = config.getBoolean("enabled", false)
        modifications =
            config.getList("modifications")?.mapNotNull { Modification.parseModification(it, plugin.logger) }
                ?.toSet<IModification>() ?: emptySet()
    }
}

