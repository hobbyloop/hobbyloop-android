package com.hobbyloopapp.feature.facility_card.card

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hobbyloopapp.core.ui.facilities.createDummyFacilities
import com.hobbyloopapp.feature.facility_card.FacilityCardArgs
import javax.inject.Inject

class CardViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args = FacilityCardArgs(savedStateHandle)

    val facility = createDummyFacilities().find { it.id == args.facilityId }
}
