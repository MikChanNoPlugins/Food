package dev.mikchan.mcnp.food

import be.seeseemelk.mockbukkit.MockBukkit
import be.seeseemelk.mockbukkit.ServerMock
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import kotlin.test.*

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

    @Test
    fun testPlayerEatingSweetBerriesWhileConfigIsDisabled() {
        assertEquals(false, plugin.config.enabled, "The plugin should be disabled by default.")

        val player = server.addPlayer("Consoomer")
        player.foodLevel = 0
        player.saturation = 0f

        assertEquals(0, player.foodLevel, "${player.name} should be hungry.")

        val sweetBerries = ItemStack(Material.SWEET_BERRIES)
        sweetBerries.amount = 1
        player.simulateConsumeItem(sweetBerries)

        assertNotEquals(0, player.foodLevel, "${player.name} should not be hungry.")
    }
}
