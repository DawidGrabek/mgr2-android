package com.example.mindand.containers

//import android.content.Context
//import com.example.mindand.db.HighScoreDatabase
//import com.example.mindand.db.repositories.PlayerScoresRepository
//import com.example.mindand.db.repositories.PlayerScoresRepositoryImpl
//import com.example.mindand.db.repositories.PlayersRepository
//import com.example.mindand.db.repositories.PlayersRepositoryImpl
//import com.example.mindand.db.repositories.ScoresRepository
//import com.example.mindand.db.repositories.ScoresRepositoryImpl
//
//class AppDataContainer(private val context: Context) : AppContainer {
//    override val playersRepository: PlayersRepository by lazy {
//        PlayersRepositoryImpl(HighScoreDatabase.getDatabase(context).playerDao())
//    }
//    override val scoresRepository: ScoresRepository by lazy {
//        ScoresRepositoryImpl(HighScoreDatabase.getDatabase(context).scoreDao())
//    }
//    override val playerScoresRepository: PlayerScoresRepository by lazy {
//        PlayerScoresRepositoryImpl(HighScoreDatabase.getDatabase(context).playerScoreDao())
//    }
//}