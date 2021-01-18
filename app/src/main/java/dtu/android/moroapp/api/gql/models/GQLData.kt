package dtu.android.moroapp.api.gql.models

import api.gql.models.GQLError
import dtu.android.moroapp.models.Event

data class GQLData(val events: List<Event>, val errors: MutableList<GQLError>)