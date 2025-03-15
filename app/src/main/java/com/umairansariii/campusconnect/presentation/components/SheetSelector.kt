package com.umairansariii.campusconnect.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SheetSelector(
    label: String,
    options: List<T>,
    itemToString: (T) -> String,
    onItemSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
    supportingText: String? = null,
    isError: Boolean = false,
) {
    var selectedItem by remember { mutableStateOf<T?>(null) }
    var showSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    LaunchedEffect(options) {
        selectedItem = null
    }

    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedItem?.let { itemToString(it) } ?: "",
            onValueChange = { /* Do nothing */ },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = label)
            },
            supportingText = {
                if (supportingText != null) {
                    Text(text = supportingText)
                }
            },
            trailingIcon = {
                Icon(
                    Icons.Outlined.KeyboardArrowDown,
                    contentDescription = "dropdown-arrow-icon",
                    modifier = Modifier.clickable {
                        showSheet = true
                    }
                )
            },
            isError = isError,
            singleLine = true,
            readOnly = true,
        )
        if (showSheet) {
            ModalBottomSheet(
                modifier = Modifier.fillMaxHeight(),
                sheetState = sheetState,
                onDismissRequest = { showSheet = false }
            ) {
                LazyColumn {
                    items(options) { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedItem = item
                                    onItemSelected(item)
                                    showSheet = false
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                            ),
                            shape = RectangleShape,
                        ) {
                            Text(
                                text = itemToString(item),
                                modifier = Modifier.fillMaxWidth().padding(16.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}