package com.example.classreminder.repository

import com.example.classreminder.data.Data
import com.example.classreminder.database.MonitorDatabase

class ClassReminderRepository(private val db: MonitorDatabase) {

    suspend fun upsert(item: Data) = db.getDao().upsert(item)
    suspend fun delete(item: Data) = db.getDao().delete(item)
    fun getAllSchedule() = db.getDao().getAllSchedule()

}