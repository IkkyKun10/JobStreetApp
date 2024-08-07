package com.riezki.jobstreetapp.core.local

import androidx.annotation.Nullable
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

/**
 * @author riezky maisyar
 */

@Dao
interface JobsDao {

    @Upsert
    suspend fun upsertAll(jobs: List<JobsEntity>)

    @Query("SELECT * FROM jobsentity")
    fun pagingSource(): PagingSource<Int, JobsEntity>

    @Query("DELETE FROM jobsentity")
    suspend fun clearAll()
}