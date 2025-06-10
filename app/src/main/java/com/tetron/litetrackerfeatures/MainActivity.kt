package com.tetron.litetrackerfeatures

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.notes.fragments.NoteListFragments
import com.tetron.all_task.fragment.AllTasksFragment
import com.tetron.create_task.fragment.CreateTaskFragment
import com.tetron.presentaton.databinding.ActivityMainBinding
import com.tetron.presentaton.R
import com.tetron.presentaton.interfaces.activity.Navigation
import com.tetron.tag.fragment.AllTagsFragment

class MainActivity : AppCompatActivity(), Navigation {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initBottomNav(savedInstanceState == null)
    }

    @Suppress("DEPRECATION")
    private fun initBottomNav(flagFirst: Boolean) {
        if (flagFirst) {
            binding.bottomNavigation.selectedItemId = R.id.item_1
           replaceFragment(AllTasksFragment())
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            if (binding.bottomNavigation.selectedItemId == item.itemId)
                return@setOnNavigationItemSelectedListener false
            when (item.itemId) {
                R.id.item_1 -> replaceFragment(AllTasksFragment())
                R.id.item_2 -> replaceFragment(CreateTaskFragment())
                R.id.item_3 -> replaceFragment(AllTagsFragment())
                R.id.item_4 -> replaceFragment(NoteListFragments())
            }
            true
        }
    }


    @SuppressLint("CommitTransaction")
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_bottom)
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }

    fun hide_bar(){
        binding.bottomNavigation.visibility=View.GONE
    }

    override fun show_bar(){
        binding.bottomNavigation.visibility=View.VISIBLE
    }


    override fun moveOnAllTasks() {
        binding.bottomNavigation.selectedItemId = R.id.item_1
    }

    override fun moveOnAllTags() {
        binding.bottomNavigation.selectedItemId = R.id.item_3
    }

    override fun moveOnCreateNewTask() {
        binding.bottomNavigation.selectedItemId = R.id.item_2
    }

    override fun main_addfragment(fragment:Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_bottom)
            .replace(binding.fragmentContainer.id, fragment)
            .addToBackStack(null)
            .commit()

        hide_bar()
    }
}