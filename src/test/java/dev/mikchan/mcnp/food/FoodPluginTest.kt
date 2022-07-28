package dev.mikchan.mcnp.food

import kotlin.test.Test
import kotlin.test.assertEquals

internal class FoodPluginTest {
    @Test
    fun sanityTest() {
        val one = 1
        val anotherOne = 1

        assertEquals(2, one + anotherOne, "Should be equal 2")
    }
}
