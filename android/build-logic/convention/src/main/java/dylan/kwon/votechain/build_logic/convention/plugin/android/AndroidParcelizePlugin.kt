package dylan.kwon.votechain.build_logic.convention.plugin.android

import dylan.kwon.votechain.build_logic.convention.plugin.base.ProjectPlugin
import org.gradle.api.plugins.PluginManager

class AndroidParcelizePlugin : ProjectPlugin() {

    override fun PluginManager.onPlugin() {
        apply("kotlin-parcelize")
    }

}