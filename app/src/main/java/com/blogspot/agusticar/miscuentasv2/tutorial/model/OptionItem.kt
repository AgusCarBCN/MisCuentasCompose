package com.blogspot.agusticar.miscuentasv2.tutorial.model

data class OptionItem(private val resourceTitle: Int, private val resourceIcon: Int) {
    val resourceTitleItem: Int
        get() = resourceTitle

    val resourceIconItem: Int
        get() = resourceIcon
}