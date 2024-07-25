package dylan.kwon.votechain.build_logic.convention.extension

import org.gradle.api.NamedDomainObjectContainer

fun <T> NamedDomainObjectContainer<T>.getOrCreate(
    name: String,
    configureAction: T.() -> Unit
) {
    when (val find = findByName(name)) {
        null -> create(name, configureAction)
        else -> find.configureAction()
    }
}