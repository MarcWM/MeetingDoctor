package com.android.meetingdoctors.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.meetingdoctors.data.model.getAllWordOrders
import com.android.meetingdoctors.presentation.BaseApplication
import com.android.meetingdoctors.presentation.components.SearchAppBarComponent
import com.android.meetingdoctors.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class WordCounterFragment: Fragment() {

    @Inject
    lateinit var application: BaseApplication

    private val viewModel: WordCounterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {

            setContent {

                val words = viewModel.words.value

                val query = viewModel.query.value

                val selectedChip = viewModel.selectedChip.value

                val loading = viewModel.loading.value

                AppTheme {

                    Scaffold(
                        topBar = {
                            SearchAppBarComponent(
                                query = query,
                                onQueryChanged = viewModel::onQueryChanged,
                                onExecuteSearch = {
                                    // TODO Execute Search
                                },
                                orders = getAllWordOrders(),
                                selectedChip = selectedChip,
                                onSelectedChipChanged = viewModel::onSelectedChipChanged
                            )
                        }
                    ) {
                        WordMenu(
                            listOfWords = words
                        ) {
                            // TODO Navigate to detail
                        }
                    }
                }

            }

        }

    }

}