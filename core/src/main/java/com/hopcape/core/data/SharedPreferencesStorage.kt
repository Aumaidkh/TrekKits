package com.hopcape.core.data

import android.content.Context
import com.hopcape.core.data.SessionStorage.Companion.KEY_SESSION_ID
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class SharedPreferencesStorage @Inject constructor(
    @ApplicationContext private val context: Context
) : SessionStorage {
    private val sharedPrefs = context.getSharedPreferences(
        KEY_SESSION_ID,
        Context.MODE_PRIVATE
    )

    override fun save(
        key: String,
        value: String
    ) {
        sharedPrefs.edit().apply {
            putString(key, value)
            commit()
        }
    }

    override fun read(key: String): String? {
        return sharedPrefs.getString(key,null)
    }

    override fun delete(key: String) {
        sharedPrefs.edit().apply {
            remove(key)
            commit()
        }
    }

    override fun clear() {
        sharedPrefs
            .edit()
            .apply {
                clear()
                commit()
            }
    }
}