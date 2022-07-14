package dev.mikchan.mcnp.food.config

import org.bukkit.potion.PotionEffectType
import java.util.logging.Logger
import kotlin.math.max
import kotlin.math.min

internal class Effect(
    override val effect: PotionEffectType,
    override val duration: IRandomIntRange,
    override val amplifier: IRandomIntRange,
    override val chance: Double
) : IEffect {
    companion object {
        fun parseEffect(data: Any?, logger: Logger? = null): Effect? {
            @Suppress("UNCHECKED_CAST") val mapData = data as? Map<String, *> ?: return null

            val strEffect = mapData["effect"] as? String
            if (strEffect == null) {
                logger?.warning("Effect is not specified.")
                return null
            }

            val effect = PotionEffectType.getByName(strEffect)
            if (effect == null) {
                logger?.warning("Unknown effect '$strEffect'.")
                return null
            }

            val processDuration = { key: String ->
                (mapData[key] as? Double ?: (mapData[key] as? Int)?.toDouble())?.let {
                    max(
                        0.0, it * 20
                    )
                }
            }

            val duration = RandomIntRange.buildIntRange(
                processDuration("duration_from"), processDuration("duration_to"), processDuration("duration")
            )

            if (duration == null) {
                logger?.warning("Invalid duration for effect '${effect.name}'.")
                return null
            }

            val amplifier = RandomIntRange.buildIntRange(
                mapData["amplifier_from"] as? Int, mapData["amplifier_to"] as? Int, mapData["amplifier"] as? Int ?: 0
            ) ?: return null

            val chance = mapData["chance"] as? Double ?: (mapData["chance"] as? Int)?.toDouble() ?: 100.0
            val normalizedChance = max(0.0, min(chance, 100.0)) / 100.0

            return Effect(effect, duration, amplifier, normalizedChance)
        }
    }
}
