package com.hobbyloopapp.core.ui.facilities

data class Facility(
    val id: String,
    val data: String
)

fun createDummyFacilities(): List<Facility> {
    return (1..100).map { id ->
        Facility(id.toString(), "Data for Facility $id")
    }
}
