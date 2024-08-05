package com.riezki.jobstreetapp.core.di

import android.app.Application
import androidx.room.Room
import com.riezki.jobstreetapp.core.local.JobsDatabase
import com.riezki.jobstreetapp.core.remote.service.ApiService
import com.riezki.jobstreetapp.core.repository.JobsRepositoryImpl
import com.riezki.jobstreetapp.domain.repository.JobsRepository
import com.riezki.jobstreetapp.domain.use_case.JobsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesDatabase(app: Application): JobsDatabase {
        return Room.databaseBuilder(
            app,
            JobsDatabase::class.java,
            "jobs_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesUseCase(repository: JobsRepository) : JobsUseCase {
        return JobsUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesRepository(apiService: ApiService, db: JobsDatabase) : JobsRepository {
        return JobsRepositoryImpl(apiService, db)
    }

    @Provides
    @Singleton
    fun providesApiService(): ApiService {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

}