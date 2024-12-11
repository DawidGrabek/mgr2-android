package com.example.mindand.db.entities

data class PlayerWithScore(
    val scoreId: Long,
    val playerId: Long,
    val playerName: String,
    val email: String,
    val score: Int,
)
