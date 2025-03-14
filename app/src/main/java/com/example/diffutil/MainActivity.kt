package com.example.diffutil

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diffutil.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var adapter: PersonAdapter  // Step 1: Declare adapter instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        adapter = PersonAdapter()  // Step 2: Initialize adapter once
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = adapter  // Step 3: Set adapter

        val person1 = Person(1, "John", 25)
        val person2 = Person(2, "Jane", 30)
        val person3 = Person(3, "Bob", 35)

        val personList = mutableListOf(person1, person2, person3)
        adapter.setData(personList)  // Step 4: Set initial data

        binding.fab.setOnClickListener {
            val newAge = Random.nextInt(3, 31)
            val newPerson = Person(4, "Chaitnya", newAge)
            personList.add(newPerson)  // Step 5: Add new person to the list
            adapter.setData(personList)  // Step 6: Update adapter data
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
