package com.dirzaaulia.multimodulesample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.dirzaaulia.multimodulesample.domain.usecase.ServiceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  private val useCase: ServiceUseCase
): ViewModel() {

  val movieList = useCase.discoverMovie().cachedIn(viewModelScope)
}