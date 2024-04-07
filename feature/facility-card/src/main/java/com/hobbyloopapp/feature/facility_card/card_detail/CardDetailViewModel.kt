package com.hobbyloopapp.feature.facility_card.card_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hobbyloopapp.core.ui.facilities.createDummyFacilities
import javax.inject.Inject

class CardDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args = CardDetailArgs(savedStateHandle)

    val facility = createDummyFacilities().find { it.id == args.facilityId }
}
