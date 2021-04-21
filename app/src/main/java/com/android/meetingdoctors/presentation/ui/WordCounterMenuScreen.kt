package com.android.meetingdoctors.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.meetingdoctors.dataSource.model.Word
import com.android.meetingdoctors.presentation.components.MenuCardComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@Composable
fun WordMenu(
    loading: Boolean = false,
    listOfWords: List<Word>,
    uploadFileButtonAvailable: Boolean = true,
    onSelectFileSelected: () -> Unit,
    onTriggerNextPage: () -> Unit
) {

    if (loading && listOfWords.isEmpty()) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Loading...")
        }

    } else if (listOfWords.isEmpty()) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "No words found..")
            Spacer(modifier = Modifier.height(4.dp))

            if (uploadFileButtonAvailable) {
                Text(text = "Maybe upload a new File?")
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { onSelectFileSelected() }) {
                    Text(text = "Upload File")
                }
            }
        }

    } else {

        LazyColumn {
            itemsIndexed(
                items = listOfWords,
            ) { index, word ->

                if ((index + 1) >= listOfWords.size && !loading) {
                    onTriggerNextPage()
                }

                MenuCardComponent(word = word)
            }
        }

    }

}