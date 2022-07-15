package dev.mikchan.mcnp.food.config

internal interface IConfig {
    val enabled: Boolean
    val modifications: Set<IModification>

    fun reload()
}
