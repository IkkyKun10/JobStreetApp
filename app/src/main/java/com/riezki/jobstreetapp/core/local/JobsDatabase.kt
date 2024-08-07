package com.riezki.jobstreetapp.core.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @author riezky maisyar
 */

@Database(entities = [JobsEntity::class, RemoteKeys::class], version = 1, exportSchema = false)
abstract class JobsDatabase : RoomDatabase() {
    abstract val jobsDao: JobsDao
    abstract val remoteKeysDao: RemoteKeysDao
}