package com.hobbyloopapp.feature.home

import androidx.lifecycle.ViewModel
import com.hobbyloopapp.core.ui.facilities.createDummyFacilities
import javax.inject.Inject

class HomeViewModel @Inject constructor() : ViewModel() {

    val facilities = createDummyFacilities()
}
