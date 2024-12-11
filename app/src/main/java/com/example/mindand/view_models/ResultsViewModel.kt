package com.example.mindand.view_models

import androidx.lifecycle.ViewModel
import com.example.mindand.db.entities.PlayerWithScore
import com.example.mindand.db.repositories.PlayerScoresRepository
import kotlinx.coroutines.flow.Flow

class ResultsViewModel (val playerScoresRepository: PlayerScoresRepository) :
    ViewModel() {

    fun loadPlayerScores(): Flow<List<PlayerWithScore>> {
        return playerScoresRepository.loadPlayersWithScores()
    }
}