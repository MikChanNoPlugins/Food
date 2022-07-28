package dev.mikchan.mcnp.food

import be.seeseemelk.mockbukkit.MockBukkit
import be.seeseemelk.mockbukkit.ServerMock
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class FoodPluginTest {
    lateinit var server: ServerMock
    lateinit var plugin: FoodPlugin

    @BeforeTest
    fun setUp() {
        server = MockBukkit.mock()
        plugin = MockBukkit.load(FoodPlugin::class.java)
    }

    @AfterTest
    fun tearDown() {
        MockBukkit.unmock()
    }

    @Test
    fun testDefaultConfigValues() {
        assertEquals(false, plugin.config.enabled, "The plugin should be disabled by default.")
    }
}
