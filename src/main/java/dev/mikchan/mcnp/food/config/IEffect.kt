package dev.mikchan.mcnp.food.config

import org.bukkit.potion.PotionEffectType

internal interface IEffect {
    val effect: PotionEffectType
    val duration: IRandomIntRange
    val amplifier: IRandomIntRange
    val chance: Double
}
