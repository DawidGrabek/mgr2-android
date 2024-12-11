package com.example.mindand.containers

import com.example.mindand.db.repositories.PlayerScoresRepository
import com.example.mindand.db.repositories.PlayersRepository
import com.example.mindand.db.repositories.ScoresRepository

interface AppContainer {
    val playersRepository: PlayersRepository
    val scoresRepository: ScoresRepository
    val playerScoresRepository: PlayerScoresRepository
}