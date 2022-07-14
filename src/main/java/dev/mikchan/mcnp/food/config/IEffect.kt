package dev.mikchan.mcnp.food.config

import org.bukkit.potion.PotionEffectType

interface IEffect {
    val effect: PotionEffectType
    val duration: IRandomIntRange
    val amplifier: IRandomIntRange
    val chance: Double
}
