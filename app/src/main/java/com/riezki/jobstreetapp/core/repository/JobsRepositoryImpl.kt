package com.riezki.jobstreetapp.core.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.riezki.jobstreetapp.core.local.JobsDatabase
import com.riezki.jobstreetapp.core.local.JobsEntity
import com.riezki.jobstreetapp.core.paging.JobsRemoteMediator
import com.riezki.jobstreetapp.core.remote.service.ApiService
import com.riezki.jobstreetapp.domain.models.JobsItem
import com.riezki.jobstreetapp.domain.repository.JobsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author riezky maisyar
 */

class JobsRepositoryImpl(
    private val apiService: ApiService,
    private val db: JobsDatabase,
) : JobsRepository {
    override fun getFilteredJobs(description: String, location: String, fullTime: Boolean): Flow<List<JobsItem>> {
        return flow {
            try {
                val response = apiService.getFilteredJobs(description, location, fullTime)
                val domain = response.map { it.toEntity().toDomain() }
                emit(domain)
            } catch (e: Exception) {
                e.printStackTrace()
                emit(emptyList())
            }
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getJobsByPage(): Pager<Int, JobsEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
            ),
            remoteMediator = JobsRemoteMediator(
                apiService = apiService,
                jobsDb = db
            ),
            pagingSourceFactory = {
                db.jobsDao.pagingSource()
            }
        )
    }

    override fun getJobById(id: String): Flow<JobsItem> {
        return flow {
            try {
                val response = apiService.getJobDetail(id)
                val domain = response.toEntity().toDomain()
                emit(domain)
            } catch (e: Exception) {
                e.printStackTrace()
                emit(
                    JobsItem(
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                    )
                )
            }
        }
    }
}