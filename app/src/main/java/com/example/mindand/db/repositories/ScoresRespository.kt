package com.example.mindand.db.repositories

import com.example.mindand.db.dao.ScoreDao
import com.example.mindand.db.entities.Score

interface ScoresRepository {
    suspend fun insert(score: Score): Long
}

class ScoresRepositoryImpl (private val scoreDao: ScoreDao) : ScoresRepository {
    override suspend fun insert(score: Score): Long = scoreDao.insert(score)
}