package com.kigya.bsutools

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kigya.bsutools.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewBinding by viewBinding(ActivityMainBinding::bind)
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(viewBinding) {
            setupToolbarWithNavController()
        }
//        GlobalScope.launch(Dispatchers.IO) {
//            dataStoreManager.savetoDataStore(
//                course = 3,
//                group = 8
//            )
//        }

    }

    private fun ActivityMainBinding.setupToolbarWithNavController() {
        navController = getNavController()
        val config = AppBarConfiguration(navController.graph)
        setSupportActionBar(toolbar)
        //Your toolbar is now an action bar and you can use it like you always do, for example:
        supportActionBar?.setDisplayHomeAsUpEnabled(false);
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