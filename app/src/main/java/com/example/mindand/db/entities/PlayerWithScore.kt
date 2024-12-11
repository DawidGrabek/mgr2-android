package com.example.mindand.db.entities

data class PlayerWithScore(
    val scoreId: Long,
    val playerId: Long,
    val playerName: String,
    val playerEmail: String,
    val score: Int,
)
