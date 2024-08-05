package com.riezki.jobstreetapp.presenter.viewmodel

import androidx.lifecycle.ViewModel
import com.riezki.jobstreetapp.domain.use_case.JobsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author riezky maisyar
 */

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: JobsUseCase
) : ViewModel() {

}