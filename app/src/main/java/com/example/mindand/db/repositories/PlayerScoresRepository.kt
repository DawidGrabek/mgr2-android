package com.example.mindand.db.repositories

import com.example.mindand.db.dao.PlayerScoreDao
import com.example.mindand.db.entities.PlayerWithScore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface PlayerScoresRepository {
    fun loadPlayersWithScores(): Flow<List<PlayerWithScore>>
}

@Singleton
class PlayerScoresRepositoryImpl @Inject constructor(private val playerScoreDao: PlayerScoreDao) :
    PlayerScoresRepository {
    override fun loadPlayersWithScores(): Flow<List<PlayerWithScore>> = playerScoreDao.loadPlayersWithScores()
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class PlayerScoresModule {
    @Binds
    abstract fun bindPlayerScoresRepository(playerScoresRepositoryImpl: PlayerScoresRepositoryImpl): PlayerScoresRepository
}