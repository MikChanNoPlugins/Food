package dev.mikchan.mcnp.food.listeners

import dev.mikchan.mcnp.food.config.IConfig
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.potion.PotionEffect
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

internal class DefaultListener(private val config: IConfig) : Listener {
    @EventHandler
    fun onPlayerItemConsume(event: PlayerItemConsumeEvent) {
        if (!config.enabled) return

        val modification = config.modifications.find { it.material == event.item.type } ?: return

        for (effect in modification.effects) {
            if (Random.nextDouble() > effect.chance) continue
            event.player.addPotionEffect(
                PotionEffect(
                    effect.effect, effect.duration.getRandom(), effect.amplifier.getRandom()
                )
            )
        }

        val heal = modification.heal
        if (heal != null && Random.nextDouble() <= heal.chance) {
            event.player.health = max(0.0, min(event.player.health + heal.amount.getRandom(), 20.0))
        }

        val damage = modification.damage
        if (damage != null && Random.nextDouble() <= damage.chance) {
            event.player.health = max(0.0, min(event.player.health - damage.amount.getRandom(), 20.0))
        }
    }

    @EventHandler
    fun onFoodLevelChange(event: FoodLevelChangeEvent) {
        if (!config.enabled) return

        val modification = config.modifications.find { it.material == event.item?.type } ?: return
        val foodLevel = modification.foodLevel ?: return

        event.foodLevel = max(0, event.entity.foodLevel + foodLevel.getRandom())
    }
}
