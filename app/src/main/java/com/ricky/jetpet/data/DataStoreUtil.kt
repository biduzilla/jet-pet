package com.ricky.jetpet.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ricky.jetpet.common.Constants
import com.ricky.jetpet.common.Constants.USER_TOKEN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreUtil(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Constants.SETTINGS)

        val THEME_KEY = booleanPreferencesKey(Constants.IS_DARK_MODE)
        val TOKEN = stringPreferencesKey(USER_TOKEN)
    }

    suspend fun saveTheme(isDark: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDark
        }
    }

    fun getTheme(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }

    fun getToken(): Flow<String> {
        return context.dataStore.data.map { p -> p[TOKEN] ?: "" }
    }

    suspend fun saveToken(token: String?) {
        token?.let {
            context.dataStore.edit { p -> p[TOKEN] = token }
        }
    }
}