package com.example.mindand

import kotlinx.coroutines.flow.Flow

class PlayerRepository(private val playerDao: PlayerDao) {
    suspend fun insertPlayer(player: Player): Long = playerDao.insertPlayer(player)
    suspend fun getPlayerByEmail(email: String): Player? = playerDao.getPlayerByEmail(email)
}

class ScoreRepository(private val scoreDao: ScoreDao) {
    suspend fun insertScore(score: Score) = scoreDao.insertScore(score)
    fun getScoresSorted(): Flow<List<PlayerScore>> = scoreDao.getScoresSorted()
}
