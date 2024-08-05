package com.riezki.jobstreetapp.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.riezki.jobstreetapp.domain.use_case.JobsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @author riezky maisyar
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: JobsUseCase
) : ViewModel() {

    val jobsPagingFlow = useCase.getJobsByPage()
        .flow
        .map { pagingData ->
            pagingData.map { it.toDomain() }
        }
        .cachedIn(viewModelScope)
}