package com.riezki.jobstreetapp.core.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.riezki.jobstreetapp.core.local.JobsDatabase
import com.riezki.jobstreetapp.core.local.JobsEntity
import com.riezki.jobstreetapp.core.local.RemoteKeys
import com.riezki.jobstreetapp.core.remote.service.ApiService
import retrofit2.HttpException
import java.io.IOException
import java.util.UUID

/**
 * @author riezky maisyar
 */

@OptIn(ExperimentalPagingApi::class)
class JobsRemoteMediator(
    private val jobsDb: JobsDatabase,
    private val apiService: ApiService,
) : RemoteMediator<Int, JobsEntity>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, JobsEntity>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevKey = remoteKeys?.prevKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    prevKey
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextKey = remoteKeys?.nextKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    nextKey
                }
            }

            val jobs = apiService.getJobsByPage(
                page = page,
            )

            val endPaginationReached = jobs?.isEmpty() == true || jobs is HttpException

            jobsDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    jobsDb.remoteKeysDao.deleteRemoteKeys()
                    jobsDb.jobsDao.clearAll()
                }

                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (endPaginationReached) null else page + 1
                val keys = jobs?.map {
                    if (it == null) {
                        RemoteKeys(
                            id = UUID.randomUUID().toString(),
                            prevKey = prevKey,
                            nextKey = nextKey
                        )
                    } else {
                        RemoteKeys(
                            id = it.id ?: UUID.randomUUID().toString(),
                            prevKey = prevKey,
                            nextKey = nextKey
                        )
                    }

                }
                val jobsEntities = jobs?.map {
                    if (it == null) {
                        JobsEntity(
                            id = UUID.randomUUID().toString(),
                            title = "null",
                            company = "null",
                            companyLogo = "companyLogo",
                            companyUrl = "companyUrl",
                            description = "description",
                            location = "null",
                            url = "url",
                            createdAt = "createdAt",
                            howToApply = "howToApply",
                            type = "type"
                        )
                    } else {
                        it.toEntity()
                    }
                }
                keys?.let { jobsDb.remoteKeysDao.insertAll(it) }
                jobsEntities?.let { jobsEntity ->
                    jobsDb.jobsDao.upsertAll(jobsEntity)
                }
            }

            MediatorResult.Success(
                endOfPaginationReached = endPaginationReached
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, JobsEntity>): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { data ->
            jobsDb.remoteKeysDao.getRemoteKeysId(data.id)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, JobsEntity>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { data ->
            jobsDb.remoteKeysDao.getRemoteKeysId(data.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, JobsEntity>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                jobsDb.remoteKeysDao.getRemoteKeysId(id)
            }
        }
    }
}