package com.d1zzy.fixme.data

import java.util.concurrent.TimeUnit

data class Habit(
    val id: String = "", // Будет заполняться автоматически Firestore
    val name: String = "",
    val startDate: com.google.firebase.Timestamp = com.google.firebase.Timestamp.now(),
    val costPerUnit: Double = 0.0,
    val timePerUnit: Int = 0,
    val frequency: Int = 1 // Раз в день
) {
    // Функции для расчётов
    fun getDaysPassed(): Long {
        val diff = System.currentTimeMillis() - startDate.toDate().time
        return TimeUnit.MILLISECONDS.toDays(diff)
    }

    fun getMoneySaved(): Double = costPerUnit * frequency * getDaysPassed()

    fun getTimeSaved(): Long = timePerUnit.toLong() * frequency * getDaysPassed()
}