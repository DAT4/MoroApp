package dtu.android.moroapp.utils.GraphQL

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

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
abstract class MotherCase(private val filter: Filter, name: String) : Query(name) {
    override fun render(builder: StringBuilder, indent: String) {
        builder.append("{$name")
        if (filter.filters.isNotEmpty()) {
            builder.append("(" + filter.filters.map {
                if (it.value is Number) {
                    it.key.str() + ":" + it.value + ","
                } else if (it.value is Iterable<*>) {
                    it.key.str() + ":" + (it.value as Iterable<*>).joinToString(", ", "[", "]") + ","
                } else {
                    it.key.str() + ":\"" + it.value + "\","
                }
            }.joinToString(" ").dropLast(1) + ")")
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

@GraphQLMarker
interface FilterType {
    fun str(): String
}

@GraphQLMarker
@Parcelize
class Filter private constructor(val filters: @RawValue Map<FilterType, Any>):Parcelable {
    class Builder {
        private var filters: MutableMap<FilterType, Any> = mutableMapOf()
        fun filters(key: FilterType, value: Any) = apply {
            this.filters[key] = when (this.filters[key]) {
                is List<*> -> (this.filters[key] as List<*>) + listOf(value)
                is Comparable<*> -> listOf(this.filters[key], value)
                else -> value
            }
        }

        fun pop(key: FilterType, value: Any) = apply {
            if (this.filters[key] is List<*>) {
                this.filters[key] = (this.filters[key] as List<*>).filter { filter -> filter != value }
            } else {
                this.filters.remove(key)
            }
        }

        fun build(): Filter {
            return Filter(filters)
        }
    }
}


