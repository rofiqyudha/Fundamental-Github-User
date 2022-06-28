package com.dicoding.submission2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SettingFactory(private val pref: Setting) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainUser::class.java)) {
            return MainUser(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}