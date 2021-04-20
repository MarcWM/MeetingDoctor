package com.android.meetingdoctors.presentation.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.meetingdoctors.dataSource.model.Word
import com.android.meetingdoctors.dataSource.model.WordEntity
import com.android.meetingdoctors.dataSource.model.WordOrder
import com.android.meetingdoctors.dataSource.model.WordOrder.*
import com.android.meetingdoctors.dataSource.model.getWordOrder
import com.android.meetingdoctors.repository.WordCounterRepository
import com.android.meetingdoctors.repository.state.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordCounterViewModel
@Inject
constructor(
    private val repository: WordCounterRepository
) : ViewModel() {

    private var _words: List<Word> = listOf()
    val words: MutableState<List<Word>> = mutableStateOf(ArrayList())

    val query = mutableStateOf("")

    val selectedChip: MutableState<WordOrder?> = mutableStateOf(null)

    val loading = mutableStateOf(false)

    init {

        // Init Loading
        loading.value = true

        // Get List
        getWordsList()
    }

    fun setNewFile() {
        viewModelScope.launch {
            repository.insertWordsFromNewFile("alice29.txt").collect {
                when(it) {
                    is DataState.Error -> {
                        //TODO Snackybar to show error
                    }

                    is DataState.Loading -> { loading.value = true }
                    is DataState.Success -> {
                        loading.value = false
                        getWordsList()
                    }
                }
            }
        }
    }

    private fun getWordsList() {
        viewModelScope.launch {
            repository.getListOfWords().collect {
                when(it) {
                    is DataState.Error -> {
                        //TODO Snackybar to show error
                    }

                    is DataState.Loading -> { loading.value = true }
                    is DataState.Success -> {
                        loading.value = false
                        words.value = it.data
                        _words = words.value.toList()

                        // Select order to show the list
                        selectedChip.value = POSITION
                    }
                }
            }
        }
    }

    private fun newSearch(query: String) {

        loading.value = true

        viewModelScope.launch {
            repository.search(query = query).collect {
                when(it) {
                    is DataState.Error -> {
                        //TODO Snackybar to show error
                    }

                    is DataState.Loading -> { loading.value = true }
                    is DataState.Success -> {
                        loading.value = false
                        words.value = it.data
                        _words = words.value.toList()
                    }
                }
            }
        }
    }

    fun onQueryChanged(query: String) {
        setQuery(query)
        newSearch(query)
    }

    fun onSelectedChipChanged(order: String) {
        val newOrder = getWordOrder(order)
        setSelectedChip(newOrder)
        setNewWordOrder(newOrder)
    }

    /**
     * Set order to show the list of words
     */
    private fun setNewWordOrder(order: WordOrder?) {
        when(order) {
            POSITION -> words.value = words.value
            ALPHABETIC -> words.value = words.value.sortedBy { it.name }
            APPEARANCES -> words.value = words.value.sortedBy { it.totalAppearances }
        }
    }

    private fun setSelectedChip(order: WordOrder?) {
        selectedChip.value = order
    }

    private fun setQuery(query: String) {
        this.query.value = query
    }
}