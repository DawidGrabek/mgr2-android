package com.example.mindand.db.repositories

import com.example.mindand.db.dao.ScoreDao
import com.example.mindand.db.entities.Score
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject
import javax.inject.Singleton

interface ScoresRepository {
    suspend fun insert(score: Score): Long
}

@Singleton
class ScoresRepositoryImpl @Inject constructor(private val scoreDao: ScoreDao) : ScoresRepository {
    override suspend fun insert(score: Score): Long = scoreDao.insert(score)
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class ScoresModule {
    @Binds
    abstract fun bindScoresRepository(scoresRepositoryImpl: ScoresRepositoryImpl): ScoresRepository
}