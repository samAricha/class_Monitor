package com.example.classreminder.viewmodels

import androidx.lifecycle.ViewModel
import com.example.classreminder.data.Data
import com.example.classreminder.repository.ClassReminderRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClassReminderViewModel(val repository: ClassReminderRepository): ViewModel() {
    fun upsert(item: Data) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }

    fun delete(item: Data) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    fun getAllSchedules() = repository.getAllSchedule()
}