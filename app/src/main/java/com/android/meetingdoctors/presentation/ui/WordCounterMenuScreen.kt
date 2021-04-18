package com.android.meetingdoctors.presentation.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.android.meetingdoctors.data.model.Word
import com.android.meetingdoctors.presentation.components.MenuCardComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun WordMenu(
    loading: Boolean = false,
    listOfWords: List<Word>,
    onNavigateToRecipeDetailScreen: (Int) -> Unit,
) {

    if (loading && listOfWords.isEmpty()) {
        Text(text = "Loading...")
        // TODO Loader
    } else if (listOfWords.isEmpty()) {
        Text(text = "No words to show")
    } else {
        LazyColumn {
            itemsIndexed(
                items = listOfWords,
            ) { index, word ->

                MenuCardComponent(word = word) {

                }
            }
        }
    }

}