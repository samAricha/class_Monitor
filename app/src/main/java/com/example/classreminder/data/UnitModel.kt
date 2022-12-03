package com.example.classreminder.data


data class UnitModel(
    val unitName:String? = null,
    val attendance:List<AttendanceData>? = null,
    var count :Int? = null
)
