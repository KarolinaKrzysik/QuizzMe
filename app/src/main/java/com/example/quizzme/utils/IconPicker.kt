package com.example.quizzme.utils

import com.example.quizzme.R

object IconPicker{
    val icons = arrayOf(

        R.drawable.ic_iconmaths,
        R.drawable.ic_icongeneralknowledge,
        R.drawable.ic_geography,
        R.drawable.ic_iconspirituality,
        R.drawable.ic_iconmusic,
        R.drawable.ic_iconsport,

    )
    var currentIcon = -1

    fun getIcon(): Int{
        currentIcon = currentIcon + 1
        return icons[currentIcon]
    }
}