package com.example.mindand

import android.app.Application


class MasterAndApplication : Application() {
    lateinit var playerRepository: PlayerRepository
    lateinit var scoreRepository: ScoreRepository

    override fun onCreate() {
        super.onCreate()
        val database = MasterAndDatabase.getDatabase(this)
        playerRepository = PlayerRepository(database.playerDao())
        scoreRepository = ScoreRepository(database.scoreDao())
    }
}
