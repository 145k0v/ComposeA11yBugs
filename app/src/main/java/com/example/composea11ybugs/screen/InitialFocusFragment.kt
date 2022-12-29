package com.example.composea11ybugs.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.composea11ybugs.component.Header
import com.example.composea11ybugs.ui.theme.ComposeA11yBugsTheme

class InitialFocusFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): ComposeView {
        return ComposeView(requireContext()).apply {
            setContent { ComposeA11yBugsTheme { Scr() } }
        }
    }

    @Composable
    private fun Scr() {
        BackHandler { findNavController().popBackStack() }
        Scaffold(
            topBar = { Header(text = "Start menu", onBack = { findNavController().popBackStack() }) }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                Banner()
                Spacer(modifier = Modifier.height(8.dp))
                Column {
                    Item(text = "Item Zero")
                    Spacer(modifier = Modifier.height(8.dp))
                    (1..3).forEach {
                        Item(text = "Item $it")
                    }
                }
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
                .padding(8.dp)
        ) {
            Text(text = "Initial element focus bug")
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