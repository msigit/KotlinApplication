package com.mcc.kotlinapplication.database.helper

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mcc.kotlinapplication.database.dao.FavouritePostDao
import com.mcc.kotlinapplication.model.FavouritePostModel

/**
 * Created by Sahidul Islam on 9/26/2019.
 */

@Database(
    entities = [FavouritePostModel::class],
    version = 1,
    exportSchema = false
)


abstract class AppDatabase : RoomDatabase() {

    abstract fun FavouritePostDao(): FavouritePostDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()
        val DATABASE_NAME = "kotlin_app.db"

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java, DATABASE_NAME
        ).build()
    }
}