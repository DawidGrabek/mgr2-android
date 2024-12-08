package com.example.mindand

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlayer(player: Player): Long

    @Query("SELECT * FROM players WHERE email = :email")
    suspend fun getPlayerByEmail(email: String): Player?
}

@Dao
interface ScoreDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertScore(score: Score)

    @Query("SELECT scores.*, players.name FROM scores INNER JOIN players ON scores.playerId = players.playerId ORDER BY score ASC")
    fun getScoresSorted(): Flow<List<PlayerScore>>
}

data class PlayerScore(
    val name: String,
    val score: Int
)
