package com.liuliangjie.cardgame.configuration

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.backends.headless.HeadlessApplication
import com.liuliangjie.cardgame.configuration.configuration.UIConfiguration
import org.junit.AfterClass
import org.junit.Assert.*
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

class ConfigurationManagerTest {

    private lateinit var configurationManager: ConfigurationManager

    companion object {
        private var application: HeadlessApplication? = null

        @BeforeClass
        @JvmStatic
        fun setUpClass() {
            // 初始化Headless LibGDX应用程序用于测试
            application = HeadlessApplication(object : ApplicationAdapter() {})
        }

        @AfterClass
        @JvmStatic
        fun tearDownClass() {
            application?.exit()
        }
    }

    @Before
    fun setUp() {
        configurationManager = ConfigurationManager()
    }

    @Test
    fun testGetConfigurationWithType() {
        // 测试获取UI配置
        val uiConfig: UIConfiguration? = configurationManager.getConfiguration("ui") as UIConfiguration?
        assertNotNull("UI配置不应为空", uiConfig)
        assertEquals("UI皮肤路径应正确", "ui/uiskin.json", uiConfig?.uiSkinUrl)
    }

    @Test
    fun testReloadAll() {
        // 测试重新加载配置不会导致异常
        configurationManager.reloadAll()
        // 如果没有抛出异常，则测试通过
        assertTrue(true)
    }

    @Test
    fun testGetNonExistentConfiguration() {
        // 测试获取不存在的配置应返回null
        val nonExistentConfig = configurationManager.getConfiguration("non-existent")
        assertNull("不存在的配置应返回null", nonExistentConfig)
    }
}