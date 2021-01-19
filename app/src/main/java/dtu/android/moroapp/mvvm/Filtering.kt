package dtu.android.moroapp.mvvm

import dtu.android.moroapp.models.event.Event
import kotlin.reflect.KClass

sealed class Filter {
    abstract val graphQLName: String
    abstract fun isMatching(event: Event): Boolean

    sealed class Exclusive : Filter() {
        sealed class Time : Exclusive() {
            data class GT(val start: Long) : Time() {
                override val graphQLName = "timestampGT"
                override fun isMatching(event: Event) = this.start <= event.time
                override fun toString() = "$graphQLName: $start"
            }

            data class LT(val end: Long) : Time() {
                override val graphQLName = "timestampLT"
                override fun isMatching(event: Event) = this.end >= event.time
                override fun toString() = "$graphQLName: $end"
            }
        }

        sealed class Price : Exclusive() {
            data class GT(val start: Long) : Price() {
                override val graphQLName = "priceGT"
                override fun isMatching(event: Event) = this.start <= event.price
                override fun toString() = "$graphQLName: $start"
            }

            data class LT(val end: Long) : Price() {
                override val graphQLName = "priceGT"
                override fun isMatching(event: Event) = this.end >= event.price
                override fun toString() = "$graphQLName: $end"
            }
        }

        data class Title(val string: String) : Exclusive() {
            override val graphQLName = "title"
            override fun isMatching(event: Event) = event.title.contains(this.string, true)
            override fun toString() = "$graphQLName: \"$string\" "
        }

    }
    sealed class Inclusive : Filter() {
        abstract val value: String
        data class Place(val place: String) : Inclusive() {
            override val graphQLName = "place"
            override fun isMatching(event: Event) = event.location.place.contains(place, true)
            override fun toString() = "$graphQLName: \"$place\" "
            override val value = "\"$place\" "
        }

        data class Area(val area: String) : Inclusive() {
            override val graphQLName = "area"
            override fun isMatching(event: Event) = event.location.area.contains(area, true)
            override fun toString() = "$graphQLName: \"$area\" "
            override val value = "\"$area\" "
        }

        data class Category(val category: String) : Inclusive() {
            override val graphQLName = "category"
            override fun isMatching(event: Event) = event.category.contains(category)
            override fun toString() = "$graphQLName: \"$category\" "
            override val value = "\"$category\" "
        }

        data class Genre(val genre: String) : Inclusive() {
            override val graphQLName = "genre"
            override fun isMatching(event: Event) = event.genre.contains(genre, true)
            override fun toString() = "$graphQLName: \"$genre\" "
            override val value = "\"$genre\" "
        }

    }
}



fun List<Event>.applyFilters(filters: List<Filter>) = filter { event -> filters.isMatching(event) }

fun List<Filter>.isMatching(event: Event): Boolean {
    return this
        .groupBy { it.javaClass.kotlin }
        .all { it.isMatching(event) }
}

fun Map.Entry<KClass<Filter>, List<Filter>>.isMatching(event: Event): Boolean {
    return when (this.value.first()) {
        is Filter.Inclusive -> this.value.any { it.isMatching(event) }
        is Filter.Exclusive -> this.value.first().isMatching(event)
    }
}

fun List<Filter>.stringify(): String {
    return this
        .groupBy { it.javaClass.kotlin }.also { println(it) }
        .map { it.stringify() }
        .joinToString(", ")
        .dropLast(1)
}

fun Map.Entry<KClass<Filter>, List<Filter>>.stringify(): String {
    return when (this.value.first()) {
        is Filter.Inclusive -> "${this.value.first().graphQLName}: ${this.value.map { (it as Filter.Inclusive).value }}"
        is Filter.Exclusive -> this.value[0].toString()
    }
}
