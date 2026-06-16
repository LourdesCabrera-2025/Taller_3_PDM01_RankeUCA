package com.pdm0126.rankeuca.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.HowToVote
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdm0126.rankeuca.screens.components.OptionBottomSheet
import com.pdm0126.rankeuca.viewmodel.OptionsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionScreen(
    viewModel: OptionsViewModel = viewModel(factory = OptionsViewModel.Factory),
    onNavigateTovoting: () -> Unit
) {
    val options by viewModel.options.collectAsStateWithLifecycle()

    var showSheet by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Administrar opciones",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp, 16.dp)
                .fillMaxSize()
        ) {
            if (options.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Inbox,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.height(36.dp)
                    )
                    Text(
                        text = "Todavia no hay opciones",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Toca Nuevo para crear la primera",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    OutlinedButton(
                        onClick = { showSheet = true },
                        modifier = Modifier.fillMaxWidth().height(20.dp).padding(top = 4.dp),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Agregar Nueva Opción")
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(items = options, key = { it.id }) { option ->
                        ElevatedCard {
                            ListItem(
                                headlineContent = {
                                    Text(
                                        text = option.name,
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                },
                                supportingContent = {
                                    Text(
                                        text = option.imageUrl,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                },
                                trailingContent = {
                                    IconButton(onClick = { viewModel.deleteOption(option) }) {
                                        Icon(
                                            imageVector = Icons.Default.DeleteOutline,
                                            contentDescription = "Borrar ${option.name}",
                                            tint = MaterialTheme.colorScheme.error
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = { onNavigateTovoting() },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Icon(imageVector = Icons.Default.HowToVote, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Ir a Votaciones →",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
        if (showSheet) {
            OptionBottomSheet(
                onSave = { name, imageUrl ->
                    viewModel.addOption(name, imageUrl)
                },
                onDismiss = { showSheet = false }
            )
        }
    }
}