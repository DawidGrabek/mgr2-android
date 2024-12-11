package com.example.mindand.db.repositories

import com.example.mindand.db.dao.PlayerDao
import com.example.mindand.db.entities.Player
import kotlinx.coroutines.flow.Flow

interface PlayersRepository {
    fun getAllPlayersStream(): Flow<List<Player>>
//    fun getPlayerStream(id: Int): Flow<Player?>
    suspend fun getPlayersByEmail(email: String): List<Player>
    suspend fun insertPlayer(player: Player): Long
    suspend fun getPlayerById(playerId: Long): Player?
    suspend fun update(player: Player)

    fun setCurrentPlayerId(playerId: Long)
    fun getCurrentPlayerId(): Long
}

class PlayersRepositoryImpl(private val playerDao: PlayerDao) : PlayersRepository {
    private var currentPlayerId: Long = -1 // Domyślna wartość, gdy brak zalogowanego gracza

    override fun getAllPlayersStream(): Flow<List<Player>> =
        playerDao.getAllPlayersStream()
//
//    override fun getPlayerStream(playerId: Int): Flow<Player?> =
//        playerDao.getPlayerStream(playerId)

    override suspend fun getPlayersByEmail(email: String): List<Player> =
        playerDao.getPlayersByEmail(email)

    override suspend fun insertPlayer(player: Player): Long =
        playerDao.insert(player)

    override suspend fun getPlayerById(playerId: Long): Player? =
        playerDao.getPlayerById(playerId)

    override suspend fun update(player: Player) {
        playerDao.updatePlayerName(player.playerId, player.name)
    }

    override fun setCurrentPlayerId(playerId: Long) {
        currentPlayerId = playerId
    }

    override fun getCurrentPlayerId(): Long = currentPlayerId
}