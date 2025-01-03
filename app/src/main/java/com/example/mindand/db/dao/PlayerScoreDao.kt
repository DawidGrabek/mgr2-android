package com.example.mindand.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.mindand.db.entities.PlayerWithScore
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerScoreDao {
    @Query(
        "SELECT " +
                "players.playerId AS playerId," +
                "players.email AS email," +
                "players.name AS playerName," +
                "scores.id AS scoreId, " +
                "scores.score AS score " +
                "FROM players, scores WHERE players.playerId = scores.playerId"
    )
    fun loadPlayersWithScores(): Flow<List<PlayerWithScore>>
}
