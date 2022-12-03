package com.example.classreminder.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.classreminder.repository.ClassReminderRepository

class ClassReminderViewModelFactory(val repository: ClassReminderRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ClassReminderViewModel(repository) as T
    }
}