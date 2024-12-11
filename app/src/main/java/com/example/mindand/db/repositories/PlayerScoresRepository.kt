package com.example.mindand.db.repositories

import com.example.mindand.db.dao.PlayerScoreDao
import com.example.mindand.db.entities.PlayerWithScore
import kotlinx.coroutines.flow.Flow

interface PlayerScoresRepository {
    fun loadPlayersWithScores(): Flow<List<PlayerWithScore>>
}

class PlayerScoresRepositoryImpl(private val playerScoreDao: PlayerScoreDao) :
    PlayerScoresRepository {
    override fun loadPlayersWithScores(): Flow<List<PlayerWithScore>> = playerScoreDao.loadPlayersWithScores()
}