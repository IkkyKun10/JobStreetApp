package com.riezki.jobstreetapp.core.remote.service

import com.riezki.jobstreetapp.core.remote.JobsItemDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author riezkymaisyar
 */

interface ApiService {

    @GET("positions.json?")
    suspend fun getJobs(
        @Query("description") description: String,
        @Query("location") location: String
    ): List<JobsItemDto>

    @GET("positions.json?")
    suspend fun getJobsByPage(
        @Query("page") page: Int
    ): List<JobsItemDto>

    @GET("positions/{id}")
    suspend fun getJobDetail(
        @Path("id") id: String
    ) : JobsItemDto

    companion object {
        const val BASE_URL = "https://dev6.dansmultipro.com/api/recruitment/"
    }

}