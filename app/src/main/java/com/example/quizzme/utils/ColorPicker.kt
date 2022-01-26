package com.example.quizzme.utils

object ColorPicker{
    val colors = arrayOf(
        "#3EB9DF",
        "#3685bc",
        "#D36280",
        "#E44F55",
        "#fa8056",
        "#818bca",
        "#7d659f",
        "#51bab3",
        "#4fb66c",
        "#e3ad17",
        "#627991",
        "#ef8ead",
        "#858fc6"

    )
    var currentColorIndex = 0

    fun getColor(): String{
        currentColorIndex = (currentColorIndex +1) % colors.size
        return colors[currentColorIndex]
    }
}