package com.example.mindand

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players")
data class Player(
    @PrimaryKey(autoGenerate = true) val playerId: Long = 0,
    val name: String,
    val email: String
)

@Entity(tableName = "scores")
data class Score(
    @PrimaryKey(autoGenerate = true) val scoreId: Long = 0,
    val playerId: Long,
    val score: Int // Im mniejszy wynik, tym lepszy
)
