package com.hopcape.core.data

interface SessionStorage {
    fun save(key: String, value: String)
    fun read(key: String): String?
    fun delete(key: String)
    fun clear()

    companion object {
        const val KEY_SESSION_ID = "session_id"
    }
}