package com.kigya.unique.ui

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kigya.unique.R
import com.kigya.unique.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewBinding by viewBinding(ActivityMainBinding::bind)
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(viewBinding) {
            setupToolbarWithNavController()
        }
    }

    private fun ActivityMainBinding.setupToolbarWithNavController() {
        navController = getNavController()
        val config = AppBarConfiguration(navController.graph)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        toolbar.setupWithNavController(navController, config)
        toolbar.navigationIcon = null
        actionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun getNavController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        return navHostFragment.navController
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_navigation_menu, menu)
        viewBinding.bottomBar.setupWithNavController(menu!!, navController)
        return false
    }

}