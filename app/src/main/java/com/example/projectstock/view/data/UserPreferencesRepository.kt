package com.example.projectstock.view.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    val isAutoDelete = dataStore.data.catch {
        if (it is IOException) {
            Log.e(TAG, "ошибка при чтении", it)
            emit(emptyPreferences())
        } else {
            throw it
        }
    }.map {
      it[IS_AUTO_DELETE] ?: false
    }

    private companion object{
        val IS_AUTO_DELETE = booleanPreferencesKey("is_auto_delete")
        const val TAG = "UserPreferences"
    }

    suspend fun saveIsAutoDelete(isAuto: Boolean) {
        dataStore.edit {
            it[IS_AUTO_DELETE] = isAuto
        }
    }
}