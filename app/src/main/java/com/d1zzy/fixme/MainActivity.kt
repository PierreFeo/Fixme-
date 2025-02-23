package com.d1zzy.fixme

import HabitAdapter
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.d1zzy.fixme.ViewModel.MainViewModel
import com.d1zzy.fixme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // Binding для activity_main.xml
    private lateinit var viewModel: MainViewModel
    private val adapter = HabitAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // Установите корневой view

        // Настройка RecyclerView
        binding.rvHabits.adapter = adapter
        binding.rvHabits.layoutManager = LinearLayoutManager(this)

        // ViewModel
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.habits.observe(this) { habits ->
            adapter.submitList(habits)
            binding.tvEmpty.visibility = if (habits.isEmpty()) View.VISIBLE else View.GONE
        }

        // Загрузить данные
        viewModel.loadHabits()
    }
}