package dev.mikchan.mcnp.food

import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class FoodPlugin : JavaPlugin() {
    override fun onEnable() {
        logger.info("Enabled")
    }

    override fun onDisable() {
        logger.info("Disabled")
    }
}
