package dev.mikchan.mcnp.food

import dev.mikchan.mcnp.food.commands.DefaultCommandHandler
import dev.mikchan.mcnp.food.config.Config
import dev.mikchan.mcnp.food.config.IConfig
import dev.mikchan.mcnp.food.listeners.DefaultListener
import org.bstats.bukkit.Metrics
import org.bukkit.event.HandlerList
import org.bukkit.plugin.PluginDescriptionFile
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.JavaPluginLoader
import java.io.File

/**
 * The main plugin class.
 */
@Suppress("unused")
class FoodPlugin : JavaPlugin {
    companion object {
        private const val bStatsId = 15822
    }

    private val ignoreBStats: Boolean

    constructor() : super() {
        ignoreBStats = false
    }

    internal constructor(
        loader: JavaPluginLoader, description: PluginDescriptionFile, dataFolder: File, file: File
    ) : super(loader, description, dataFolder, file) {
        ignoreBStats = true
    }

    internal val config: IConfig = Config(this)

    override fun onEnable() {
        server.pluginManager.registerEvents(DefaultListener(config), this)

        val serverCommand = server.getPluginCommand("mcn_food")
        if (serverCommand != null) {
            val command = DefaultCommandHandler(config)

            serverCommand.setExecutor(command)
            serverCommand.tabCompleter = command
        }

        if (!ignoreBStats) {
            Metrics(this, bStatsId)
        }
    }

    override fun onDisable() {
        HandlerList.unregisterAll(this)

        val serverCommand = server.getPluginCommand("mcn_food")
        if (serverCommand != null) {
            serverCommand.setExecutor(null)
            serverCommand.tabCompleter = null
        }
    }
}
