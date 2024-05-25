package com.hobbyloop.feature.center

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hobbyloop.domain.usecase.GetCentersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CenterViewModel @Inject constructor(getCentersUseCase: GetCentersUseCase) : ViewModel() {

    val centersFlow = getCentersUseCase().cachedIn(viewModelScope)

    private val _filterState = MutableStateFlow(FilterState())
    val filterState: StateFlow<FilterState> = _filterState.asStateFlow()

    fun updateFilterState(newFilterState: FilterState) {
        _filterState.value = newFilterState
    }
}

data class FilterState(
    val sort: SortingOption? = null,
    val location: List<SelectedRegion> = emptyList(),
    val refundable: Boolean = false,
    val score: ScoreOption? = null,
)