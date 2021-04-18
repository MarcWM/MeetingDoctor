package com.android.meetingdoctors.presentation.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.android.meetingdoctors.data.model.Word
import com.android.meetingdoctors.data.model.WordOrder
import com.android.meetingdoctors.data.model.getAllWordOrders
import com.android.meetingdoctors.data.model.getWordOrder
import com.android.meetingdoctors.repository.WordCounterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WordCounterViewModel
@Inject
constructor(
    private val repository: WordCounterRepository
) : ViewModel() {

    val words: MutableState<List<Word>> = mutableStateOf(ArrayList())

    val query = mutableStateOf("")

    val selectedChip: MutableState<WordOrder?> = mutableStateOf(null)

    val loading = mutableStateOf(false)

    init {
        words.value = repository.getListOfWords()
    }

    fun newSearch() {
        //TODO Search for words in BBDD
    }

    fun onQueryChanged(query: String) {
        setQuery(query)
    }

    fun onSelectedChipChanged(order: String) {
        val newOrder = getWordOrder(order)
        setSelectedChip(newOrder)
    }

    private fun setSelectedChip(order: WordOrder?) {
        selectedChip.value = order
    }

    private fun setQuery(query: String) {
        this.query.value = query
    }
}