package com.android.meetingdoctors.presentation.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.android.meetingdoctors.repository.WordCounterRepository

class WordCounterViewModel

@ViewModelInject
constructor(
    private val repository: WordCounterRepository
) : ViewModel() {

    // TODO

}