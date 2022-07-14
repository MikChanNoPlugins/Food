package dev.mikchan.mcnp.food.commands

import dev.mikchan.mcnp.food.config.IConfig
import net.md_5.bungee.api.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

internal class DefaultCommandHandler(private val config: IConfig) : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.size != 1) return false
        if (!args[0].equals("reload", true)) return false
        config.reload()
        sender.sendMessage("${ChatColor.DARK_GREEN}Successfully Reloaded!")
        return true
    }

    override fun onTabComplete(
        sender: CommandSender, command: Command, alias: String, args: Array<out String>
    ): MutableList<String> {
        if (args.size != 1) return mutableListOf()
        if (!"reload".startsWith(args[0], true)) return mutableListOf()
        return mutableListOf("reload")
    }
}
