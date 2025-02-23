package com.d1zzy.fixme.repository

import com.d1zzy.fixme.data.Habit
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class HabitRepository {
    private val auth = Firebase.auth
    private val db = Firebase.firestore

    // Получить текущего пользователя
    fun getCurrentUserId(): String? = auth.currentUser?.uid

    // Получить все привычки пользователя
    fun getHabits(userId: String, onSuccess: (List<Habit>) -> Unit) {
        db.collection("users/$userId/habits")
            .addSnapshotListener { snapshot, error ->
                if (error != null) return@addSnapshotListener
                val habits = snapshot?.toObjects(Habit::class.java) ?: emptyList()
                onSuccess(habits)
            }
    }

    // Добавить новую привычку
    fun addHabit(userId: String, habit: Habit, onSuccess: () -> Unit) {
        db.collection("users/$userId/habits")
            .add(habit)
            .addOnSuccessListener { onSuccess() }
    }
}