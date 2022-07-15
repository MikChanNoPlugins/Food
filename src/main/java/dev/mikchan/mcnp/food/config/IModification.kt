package dev.mikchan.mcnp.food.config

import org.bukkit.Material

internal interface IModification {
    val material: Material
    val foodLevel: IRandomIntRange?
    val effects: Set<IEffect>
    val heal: IDHData?
    val damage: IDHData?
}
