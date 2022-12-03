package com.example.classreminder.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.classreminder.data.Data

@Dao
interface MonitorDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun upsert(data: Data)

    @Delete
    suspend fun delete(data: Data)

    @Query("SELECT*FROM class_info")
    fun getAllSchedule(): LiveData<List<Data>>
}