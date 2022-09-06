package com.example.task5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityMainBinding
//    private lateinit var itemAdapter: CatAdapter
//    private val catViewModel by viewModels<CatViewModel>()
//    private lateinit var recycler: RecyclerView


    private val navController by lazy {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(navController)
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        itemAdapter = CatAdapter()
//        recycler = binding.recyclerView
//
//        recycler.apply {
//            adapter = itemAdapter
//            layoutManager = LinearLayoutManager(this@MainActivity)
//        }
//
//        catViewModel.items.observe(this, Observer {
//            it ?: return@Observer
//            itemAdapter.addItems(it)
//        })
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}