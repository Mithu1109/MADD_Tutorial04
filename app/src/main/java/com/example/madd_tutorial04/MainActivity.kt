package com.example.madd_tutorial04

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var btnAdd: Button
    lateinit var btnView: Button
    private lateinit var viewModel: MainActivityData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainActivityData::class.java]

        btnAdd = findViewById(R.id.btnAdd)
        btnView = findViewById(R.id.btnView)

        // Load insert fragment by default
        if (savedInstanceState == null) {
            loadInsertFragment()
        }

        btnAdd.setOnClickListener {
            viewModel.selectedItem.value = null // reset update state for blank form
            loadInsertFragment()
        }

        btnView.setOnClickListener {
            loadViewFragment()
        }
    }

    fun loadInsertFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, InsertRecordFragment())
            .commit()
    }

    fun loadViewFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ViewRecordsFragment())
            .commit()
    }
}
