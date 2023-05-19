package com.mmm.income

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mmm.income.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragFrame,AddFragment())
            .commit()

        binding.bottom.setOnItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {

                when(item.itemId) {
                    R.id.add->{
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragFrame,AddFragment())
                            .commit()
                    }
                    R.id.trans->{
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragFrame,TransFragment())
                            .commit()
                    }
                }

                return true
            }

        })

    }
}