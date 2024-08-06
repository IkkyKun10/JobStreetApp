package com.riezki.jobstreetapp.core.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.riezki.jobstreetapp.core.local.JobsDatabase
import com.riezki.jobstreetapp.core.local.JobsEntity
import com.riezki.jobstreetapp.core.remote.service.ApiService
import retrofit2.HttpException
import java.io.IOException

/**
 * @author riezky maisyar
 */

@OptIn(ExperimentalPagingApi::class)
class JobsRemoteMediator(
    private val jobsDb: JobsDatabase,
    private val apiService: ApiService
) : RemoteMediator<Int, JobsEntity>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, JobsEntity>): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null) {
                        1
                    } else {
                        lastItem.idEntity?.let {
                            (it / state.config.pageSize) + 1
                        }
                    }
                }
            }

            val jobs = apiService.getJobsByPage(
                page = loadKey!!,
            )

            jobsDb.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    jobsDb.jobsDao.clearAll()
                }
                val jobsEntities = jobs?.map { it.toEntity() }
                if (jobsEntities != null) {
                    jobsDb.jobsDao.upsertAll(jobsEntities)
                }
            }

            MediatorResult.Success(
                endOfPaginationReached = jobs?.isEmpty() == true
            )
        } catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}