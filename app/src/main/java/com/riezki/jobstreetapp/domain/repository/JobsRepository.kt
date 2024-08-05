package com.riezki.jobstreetapp.domain.repository

import androidx.paging.Pager
import com.riezki.jobstreetapp.core.local.JobsEntity
import com.riezki.jobstreetapp.domain.models.JobsItem
import kotlinx.coroutines.flow.Flow

/**
 * @author riezky maisyar
 */

interface JobsRepository {

    fun getFilteredJobs(
        description: String,
        location: String,
        fullTime: Boolean
    ) : Flow<List<JobsItem>>

    fun getJobsByPage() : Pager<Int, JobsEntity>

    fun getJobById(id: String) : Flow<JobsItem>

}