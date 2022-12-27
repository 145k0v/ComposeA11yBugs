package com.example.composea11ybugs.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.composea11ybugs.R
import com.example.composea11ybugs.component.Header
import com.example.composea11ybugs.ui.theme.ComposeA11yBugsTheme

class StartFragment : Fragment() {

    private val navigationController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ComposeView {
        return ComposeView(requireContext()).apply {
            setContent { ComposeA11yBugsTheme { Scr() } }
        }
    }

    @Composable
    private fun Scr() {
        Scaffold(
            topBar = { Header(text = "Start menu", onBack = null) }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                Item(
                    text = "Go to HorizontalPager bugs",
                    onClick = { navigationController.navigate(R.id.horizontalPagerFragment) })
            }
        }
    }

    @Composable
    private fun Item(text: String, onClick: () -> Unit) {
        Box(
            modifier = Modifier
                .clickable(onClick = onClick)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = text)
        }
    }
}