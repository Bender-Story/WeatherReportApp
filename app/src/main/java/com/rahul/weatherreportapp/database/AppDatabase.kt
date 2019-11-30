package com.rahul.weatherreportapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rahul.weatherreportapp.data.SelectedData

@Database(entities = [SelectedData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun searchResultDao(): SearchResultDao
}