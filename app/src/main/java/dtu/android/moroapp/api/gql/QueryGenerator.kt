package api.gql

import api.gql.models.GQLEvent
import dtu.android.moroapp.mvvm.Filter
import dtu.android.moroapp.mvvm.stringify

fun events(filters: List<Filter>, visit: GQLEvent.() -> Unit): GQLEvent {
    val events = GQLEvent(filters)
    events.visit()
    return events
}

@DslMarker //Domain Specific Language
annotation class GraphQLMarker

interface Element {
    fun render(builder: StringBuilder, indent: String)
}

@GraphQLMarker
abstract class Query(val name: String) : Element {
    val children = arrayListOf<Element>()
    protected fun <T : Element> visitEntity(entity: T, visit: T.() -> Unit): T {
        entity.visit()
        children.add(entity)
        return entity
    }

    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent$name")
        if (children.isNotEmpty()) {
            builder.append("{\n")
            for (c in children) {
                c.render(builder, "$indent  ")
            }
            builder.append("$indent}")
        }
        builder.append("\n")
    }

    override fun toString(): String {
        val builder = StringBuilder()
        render(builder, "")
        return builder.toString()
    }
}

@GraphQLMarker
abstract class EdgeCase(parent: Query, name: String) : Query(name) {
    init {
        parent.children.add(this)
    }
}

@GraphQLMarker
abstract class MotherCase(private val filters: List<Filter>, name: String) : Query(name) {
    override fun render(builder: StringBuilder, indent: String) {
        builder.append("{$name")
        if (filters.isNotEmpty()) {
            builder.append("(" + filters.stringify() + ")")
        }
        if (children.isNotEmpty()) {
            builder.append("{\n")
            for (c in children) {
                c.render(builder, "$indent  ")
            }
            builder.append("$indent}")
        }
        builder.append("\n}")
    }
}

