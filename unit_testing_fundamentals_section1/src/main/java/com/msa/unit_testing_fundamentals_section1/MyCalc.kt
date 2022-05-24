package com.msa.unit_testing_fundamentals_section1

class MyCalc : Calculations {

    private val pi = 3.14

    override fun calculateCircumference(radius: Double): Double {
        return 2 * pi * radius
    }

    override fun calculateArea(radius: Double): Double {
        return pi * radius * radius
    }
}