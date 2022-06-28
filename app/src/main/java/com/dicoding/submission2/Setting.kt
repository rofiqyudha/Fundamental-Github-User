package com.dicoding.submission2

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Setting private constructor(private val dataStore: DataStore<Preferences>){
    private val ThemeKey = booleanPreferencesKey("theme_setting")
    fun getThemeSetting():Flow<Boolean>{
        return dataStore.data.map {
            preferences -> preferences[ThemeKey]?: false
        }
    }
    suspend fun saveThemeSetting(isDarkModeActive:Boolean){
        dataStore.edit {
            preferences -> preferences[ThemeKey] = isDarkModeActive
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: Setting? = null

        fun getInstance(dataStore: DataStore<Preferences>): Setting {
            return INSTANCE ?: synchronized(this) {
                val instance = Setting(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

}