package com.rezaie.components.extenssions

fun String.getIdFromUrl(): String {
    return Regex(".*/(\\d+)/").find(this)?.groupValues?.get(1).orEmpty()
}

fun String.replaceNewlinesWithSpace(): String {
    return this.replace("\r\n", " ")
}