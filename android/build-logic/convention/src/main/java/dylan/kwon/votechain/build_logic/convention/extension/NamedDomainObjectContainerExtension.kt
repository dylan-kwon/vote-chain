package dylan.kwon.votechain.build_logic.convention.extension

import org.gradle.api.NamedDomainObjectContainer

fun <T> NamedDomainObjectContainer<T>.getOrCreate(name: String): T {
    return findByName(name) ?: create(name)
}

fun <T> NamedDomainObjectContainer<T>.getOrCreate(
    name: String,
    configureAction: T.() -> Unit
): T {
    return findByName(name) ?: create(name, configureAction)
}