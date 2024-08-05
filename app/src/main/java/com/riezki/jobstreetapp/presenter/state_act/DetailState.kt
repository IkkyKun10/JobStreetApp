package com.riezki.jobstreetapp.presenter.state_act

import com.riezki.jobstreetapp.domain.models.JobsItem

/**
 * @author riezky maisyar
 */

sealed interface DetailState {
    data object Loading: HomeState
    data class Success(val data: JobsItem) : HomeState
    data class Error(val message: String) : HomeState
}