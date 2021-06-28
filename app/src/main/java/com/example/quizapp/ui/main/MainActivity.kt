package com.example.quizapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.ui.fragments.historyFragment.HistoryFragment
import com.example.quizapp.ui.menuAdapter.MenuBottomAdapter
import com.example.quizapp.ui.fragments.quizFragment.QuizFragment
import com.example.quizapp.ui.fragments.settingsFragment.SettingsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var ui: ActivityMainBinding
    private lateinit var previewMenuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)

        val menuAdapter = MenuBottomAdapter(
            initFragments(),
            supportFragmentManager
        )
        ui.viewPager.adapter = menuAdapter

        navigationView()
    }

    private fun navigationView() {
        ui.navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.quizMenu ->{
                    ui.viewPager.currentItem = 0
                }

                R.id.historyMenu ->{
                    ui.viewPager.currentItem = 1
                }

                R.id.settingsMenu ->{
                    ui.viewPager.currentItem = 2
                }
            }
            true
        }

        ui.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (::previewMenuItem.isInitialized){
                    previewMenuItem.isChecked = false
                } else {
                    ui.navigationView.menu.getItem(0).isChecked = false
                }
                ui.navigationView.menu.getItem(position).isChecked = true
                previewMenuItem = ui.navigationView.menu.getItem(position)
            }
        })
    }

    private fun initFragments():ArrayList<Fragment>{
        return arrayListOf(
            QuizFragment.newInstance(),
            HistoryFragment.newInstance(),
            SettingsFragment.newInstance())
    }


}