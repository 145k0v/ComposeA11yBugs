package com.example.composea11ybugs.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.composea11ybugs.component.Header
import com.example.composea11ybugs.ui.theme.ComposeA11yBugsTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

class HorizontalPagerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): ComposeView {
        return ComposeView(requireContext()).apply {
            setContent { ComposeA11yBugsTheme { Scr() } }
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    private fun Scr() {
        BackHandler { findNavController().popBackStack() }
        Scaffold(
            topBar = { Header(text = "Horizontal pager", onBack = { findNavController().popBackStack() }) }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                val pageCount = 10
                val coroutineScope = rememberCoroutineScope()
                val pagerState = rememberPagerState(initialPage = 1)

                Banner()
                Tabs(count = pageCount, selectedIndex = pagerState.currentPage, onSelect = {
                    coroutineScope.launch { pagerState.scrollToPage(it) }
                })
                Pager(state = pagerState, count = pageCount)
            }
        }
    }

    @Composable
    private fun Banner() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, MaterialTheme.colors.primary)
                .padding(32.dp)
        ) {
            Text(text = "Text of the banner")
        }
    }

    @Composable
    private fun Tabs(count: Int, selectedIndex: Int, onSelect: (Int) -> Unit) {
        LazyRow(contentPadding = PaddingValues(8.dp)) {
            (1..count).forEach { index ->
                item {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .border(1.dp, MaterialTheme.colors.primary)
                            .background(if (selectedIndex == index) MaterialTheme.colors.primary.copy(alpha = 0.1f) else Color.Transparent)
                            .clickable(onClick = { onSelect(index) })
                            .padding(all = 8.dp)
                    ) {
                        Text(text = "Tab $index")
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    private fun Pager(state: PagerState, count: Int) {
        HorizontalPager(count = count, state = state) { pageIndex ->
            Column {
                (1..30).forEach { itemIndex ->
                    Item(text = "Text $itemIndex on page $pageIndex")
                }
            }
        }
    }

    @Composable
    private fun Item(text: String) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = text)
        }
    }
}