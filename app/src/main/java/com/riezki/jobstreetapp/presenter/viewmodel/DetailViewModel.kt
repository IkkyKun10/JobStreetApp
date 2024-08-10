package com.riezki.jobstreetapp.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riezki.jobstreetapp.domain.models.JobsItem
import com.riezki.jobstreetapp.domain.use_case.JobsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * @author riezky maisyar
 */

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: JobsUseCase
) : ViewModel() {

    fun getJobsById(id: String) = useCase.getJobById(id)
}