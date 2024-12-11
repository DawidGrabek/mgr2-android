package com.example.mindand.view_models

import androidx.compose.runtime.mutableLongStateOf
import androidx.lifecycle.ViewModel
import com.example.mindand.db.entities.Score
import com.example.mindand.db.repositories.PlayersRepository
import com.example.mindand.db.repositories.ScoresRepository
import kotlin.math.absoluteValue

class GameViewModel (
    playersRepository: PlayersRepository,
    private val scoresRepository: ScoresRepository
) : ViewModel() {
    var playerId = playersRepository.getCurrentPlayerId()
    var score = mutableLongStateOf(0L)

    suspend fun savePlayerScore() {
        val playerIdValue = playerId.absoluteValue ?: throw IllegalStateException("PlayerId is null")
        val score = Score(playerId = playerIdValue, score = score.longValue)
        scoresRepository.insert(score)
    }
}