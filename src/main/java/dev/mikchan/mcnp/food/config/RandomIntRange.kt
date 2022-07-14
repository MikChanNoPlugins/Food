package dev.mikchan.mcnp.food.config

import kotlin.math.ceil
import kotlin.random.Random

internal class RandomIntRange(private val from: Int, private val to: Int) : IRandomIntRange {
    companion object {
        fun buildIntRange(from: Double?, to: Double?, default: Double?): RandomIntRange? {
            return buildIntRange(from?.let { ceil(it).toInt() },
                to?.let { ceil(it).toInt() },
                default?.let { ceil(it).toInt() })
        }

        fun buildIntRange(from: Int?, to: Int?, default: Int?): RandomIntRange? {
            if (from != null && to != null && from <= to) {
                return RandomIntRange(from.toInt(), to.toInt())
            }

            if (default != null) {
                return RandomIntRange(default, default)
            }

            return null
        }
    }

    override fun getRandom(): Int {
        if (from == to) return from
        return Random.nextInt(from, to + 1)
    }
}
