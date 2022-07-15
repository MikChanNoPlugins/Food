package dev.mikchan.mcnp.food.config

import org.bukkit.Material
import java.util.logging.Logger

internal class Modification(
    override val material: Material,
    override val foodLevel: IRandomIntRange?,
    override val effects: Set<IEffect>,
    override val heal: IDHData?,
    override val damage: IDHData?
) : IModification {
    companion object {
        fun parseModification(data: Any?, logger: Logger? = null): Modification? {
            @Suppress("UNCHECKED_CAST") val mapData = data as? Map<String, *> ?: return null

            val materialStr = mapData["material"] as? String
            if (materialStr == null) {
                logger?.warning("material is not specified.")
                return null
            }

            val material = Material.getMaterial(materialStr)
            if (material == null) {
                logger?.warning("Unknown material '$materialStr'.")
                return null
            }

            if (!material.isEdible) {
                logger?.warning("Material '${material.name}' is not edible.")
                return null
            }

            val foodLevel = RandomIntRange.buildIntRange(
                mapData["food_level_from"] as? Int, mapData["food_level_to"] as? Int, mapData["food_level"] as? Int
            )

            val effects =
                (mapData["effects"] as? List<*>)?.mapNotNull { Effect.parseEffect(it, logger) }?.toSet<IEffect>()
                    ?: emptySet()

            val heal = DHData.parseDHData(mapData["heal"])
            val damage = DHData.parseDHData(mapData["damage"])

            return Modification(material, foodLevel, effects, heal, damage)
        }
    }
}
