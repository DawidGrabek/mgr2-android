package com.example.mindand

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Player::class, Score::class], version = 1, exportSchema = false)
abstract class MasterAndDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
    abstract fun scoreDao(): ScoreDao

    companion object {
        @Volatile
        private var INSTANCE: MasterAndDatabase? = null

        fun getDatabase(context: Context): MasterAndDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MasterAndDatabase::class.java,
                    "masterand_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
