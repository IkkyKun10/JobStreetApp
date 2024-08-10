package com.riezki.jobstreetapp.presenter.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.riezki.jobstreetapp.domain.models.JobsItem
import com.riezki.jobstreetapp.domain.use_case.JobsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author riezky maisyar
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: JobsUseCase
) : ViewModel() {

    var textLoc by mutableStateOf("")
    var textDescFilter by  mutableStateOf("")
    var isSearching by mutableStateOf(false)

    //second state the text typed by the user
    private val _filteredJobs = MutableStateFlow<List<JobsItem>>(emptyList())
    val filteredJobs = _filteredJobs.asStateFlow()

    fun onToogleSearch(description: String, location: String, fullTime: Boolean) {
        viewModelScope.launch {
            useCase.getFilteredJobs(description, location, fullTime).collectLatest {
                _filteredJobs.value = it
            }
        }
    }

    val jobsPagingFlow = useCase.getJobsByPage()
        .flow
        .map { pagingData ->
            pagingData.map { it.toDomain() }
        }
        .cachedIn(viewModelScope)

}