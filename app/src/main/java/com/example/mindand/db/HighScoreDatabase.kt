package com.example.mindand.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mindand.db.dao.PlayerDao
import com.example.mindand.db.dao.PlayerScoreDao
import com.example.mindand.db.dao.ScoreDao
import com.example.mindand.db.entities.Player
import com.example.mindand.db.entities.Score

@Database(entities = [Player::class, Score::class], version = 1, exportSchema = false)
abstract class HighScoreDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
    abstract fun playerScoreDao(): PlayerScoreDao
    abstract fun scoreDao(): ScoreDao

    companion object {
        @Volatile
        private var Instance: HighScoreDatabase? = null

        fun getDatabase(context: Context): HighScoreDatabase {
            return Room.databaseBuilder(
                context,
                HighScoreDatabase::class.java,
                "highscore_database"
            ).build().also { Instance = it }
        }
    }
}

