package dev.mikchan.mcnp.food.config

import kotlin.math.max
import kotlin.math.min

internal class DHData(override val amount: IRandomIntRange, override val chance: Double) : IDHData {
    companion object {
        fun parseDHData(data: Any?): DHData? {
            @Suppress("UNCHECKED_CAST") val mapData = data as? Map<String, *> ?: return null

            val amount = RandomIntRange.buildIntRange(
                mapData["amount_from"] as? Int, mapData["amount_to"] as? Int, mapData["amount"] as? Int ?: 0
            ) ?: return null

            val chance = mapData["chance"] as? Double ?: (mapData["chance"] as? Int)?.toDouble() ?: 100.0
            val normalizedChance = max(0.0, min(chance, 100.0)) / 100.0

            return DHData(amount, normalizedChance)
        }
    }
}
