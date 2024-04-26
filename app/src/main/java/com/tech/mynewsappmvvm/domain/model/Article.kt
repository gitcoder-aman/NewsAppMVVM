package com.tech.mynewsappmvvm.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Entity
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    @PrimaryKey val url: String,
    val urlToImage: String
) {
    companion object{
        fun fromEncodedString(encoded: String): Article {
            return Json.decodeFromString(encoded)
        }
    }
}

fun Article.toEncodedString(): String {
    return Json.encodeToString(this)
}
