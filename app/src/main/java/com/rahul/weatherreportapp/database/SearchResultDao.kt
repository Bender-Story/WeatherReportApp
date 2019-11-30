package com.rahul.weatherreportapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.rahul.weatherreportapp.data.SelectedData

@Dao
interface SearchResultDao {
    @Query("SELECT * FROM selectedDatabase ORDER BY timestamp DESC LIMIT 10 ")
    fun getAll(): List<SelectedData>

    @Insert(onConflict=REPLACE)
    fun insertAll(vararg yearlyData: SelectedData?)

    @Delete
    fun delete(yearlyData: SelectedData)

    @Query("DELETE FROM selectedDatabase")
    fun deleteAll()
}