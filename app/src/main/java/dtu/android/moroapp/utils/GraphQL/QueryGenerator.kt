package dtu.android.moroapp.utils.GraphQL

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
abstract class EdgeCase(parent: Query, name: String) :Query(name) {
    init {
        parent.children.add(this)
    }
}

@GraphQLMarker
abstract class MotherCase(private val filter: Filter, name: String) :Query(name){
    override fun render(builder: StringBuilder, indent: String) {
        builder.append("{$name")
        if (filter.filters.isNotEmpty()) {
            builder.append("(" + filter.filters.map {
                if (it.value is Int || it.value is Long) {
                    it.key.str() + ":" + it.value + ","
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
interface FilterType {fun str() : String}

@GraphQLMarker
class Filter private constructor(val filters: MutableMap<FilterType, Any>) {
    class Builder {
        private val filters = mutableMapOf<FilterType, Any>()
        fun filters(key: FilterType, value: Any) = apply {
            this.filters[key] = value
        }

        fun build(): Filter {
            return Filter(filters)
        }
    }
}


