package com.riezki.jobstreetapp.domain.use_case

import androidx.paging.Pager
import com.riezki.jobstreetapp.core.local.JobsEntity
import com.riezki.jobstreetapp.domain.models.JobsItem
import com.riezki.jobstreetapp.domain.repository.JobsRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author riezky maisyar
 */

class JobsUseCase(
    private val jobsRepository: JobsRepository
) {

    fun getFilteredJobs(
        description: String,
        location: String,
        fullTime: Boolean
    ) : Flow<List<JobsItem>> {
        return jobsRepository.getFilteredJobs(description, location, fullTime)
    }

    fun getJobsByPage() : Pager<Int, JobsEntity> {
        return jobsRepository.getJobsByPage()
    }

    fun getJobById(id: String) : Flow<JobsItem> {
        return jobsRepository.getJobById(id)
    }
}