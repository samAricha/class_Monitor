package com.example.classreminder.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.classreminder.data.Data

@Database(entities = [Data::class], version = 1, exportSchema = false)
abstract class MonitorDatabase : RoomDatabase() {
    abstract fun getDao(): MonitorDao

    companion object {
        @Volatile
        private var INSTANCE: MonitorDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE?: synchronized(LOCK){
            INSTANCE?: createDatabase(context).also { INSTANCE = it }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            MonitorDatabase::class.java,
            "monitor.db"
        ).build()

    }
}