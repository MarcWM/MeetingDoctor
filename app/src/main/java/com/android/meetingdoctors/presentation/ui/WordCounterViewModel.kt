package com.android.meetingdoctors.presentation.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.android.meetingdoctors.data.model.Word
import com.android.meetingdoctors.data.model.WordOrder
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

}