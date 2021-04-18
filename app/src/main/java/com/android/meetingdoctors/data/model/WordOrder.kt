package com.android.meetingdoctors.data.model

enum class WordOrder(val value: String){
    POSITION("Position"),
    ALPHABETIC("Alphabetic"),
    APPEARANCES("Appearances")
}

fun getAllWordOrders(): List<WordOrder> {
    return listOf(WordOrder.POSITION, WordOrder.ALPHABETIC, WordOrder.APPEARANCES)
}

fun getWordOrder(value: String): WordOrder? {
    val map = WordOrder.values().associateBy(WordOrder::value)
    return map[value]
}