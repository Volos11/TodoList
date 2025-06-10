package com.tetron.presentaton.interfaces.activity

import androidx.fragment.app.Fragment

interface Navigation {
    fun moveOnAllTasks()
    fun moveOnAllTags()
    fun moveOnCreateNewTask()
    fun main_addfragment(fragment: Fragment)
    fun show_bar()
}