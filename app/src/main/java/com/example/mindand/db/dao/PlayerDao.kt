package com.example.mindand.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mindand.db.entities.Player
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(player: Player): Long

    @Query("SELECT * from players WHERE playerId = :playerId")
    suspend fun getPlayerById(playerId: Long): Player?

    @Query("SELECT * from players WHERE email = :email")
    suspend fun getPlayersByEmail(email: String): List<Player>

    @Query("SELECT * FROM players")
    fun getAllPlayersStream(): Flow<List<Player>>

    @Query("UPDATE players SET name = :name WHERE playerId = :playerId")
    suspend fun updatePlayerName(playerId: Long, name: String)
}