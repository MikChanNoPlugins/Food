package dev.mikchan.mcnp.food.config

interface IConfig {
    val enabled: Boolean
    val modifications: Set<IModification>

    fun reload()
}
