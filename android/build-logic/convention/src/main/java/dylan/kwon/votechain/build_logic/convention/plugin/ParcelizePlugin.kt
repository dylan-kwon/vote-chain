package dylan.kwon.votechain.build_logic.convention.plugin

import dylan.kwon.votechain.build_logic.convention.plugin.base.ProjectPlugin
import org.gradle.api.plugins.PluginManager

class ParcelizePlugin : ProjectPlugin() {

    override fun PluginManager.onPlugin() {
        apply("kotlin-parcelize")
    }

}