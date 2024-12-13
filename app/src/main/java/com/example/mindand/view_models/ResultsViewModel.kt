package com.example.mindand.view_models

import androidx.lifecycle.ViewModel
import com.example.mindand.db.entities.PlayerWithScore
import com.example.mindand.db.repositories.PlayerScoresRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(val playerScoresRepository: PlayerScoresRepository) :
    ViewModel() {

    fun loadPlayerScores(): Flow<List<PlayerWithScore>> {
        return playerScoresRepository.loadPlayersWithScores()
    }
}