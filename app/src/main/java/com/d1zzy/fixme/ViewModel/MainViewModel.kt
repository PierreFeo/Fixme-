package com.d1zzy.fixme.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.d1zzy.fixme.data.Habit
import com.d1zzy.fixme.repository.HabitRepository

class MainViewModel : ViewModel() {
    private val repository = HabitRepository()
    private val _habits = MutableLiveData<List<Habit>>()
    val habits: LiveData<List<Habit>> = _habits

    fun loadHabits() {
        val userId = repository.getCurrentUserId() ?: return
        repository.getHabits(userId) { habits ->
            _habits.postValue(habits)
        }
    }
}