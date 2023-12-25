package data.local

import kotlinx.serialization.Serializable


@Serializable
data class Topic(
    val title: String,
    val imageURL: String
)