package com.example.eden.ui.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsPadding

private data class ReportScreenTextFieldContent(
    val label: String,
    val imageVector: ImageVector,
    val stringValue: MutableState<String> = mutableStateOf("")
)

@Composable
fun ReportScreen() {
    val capturePhotoLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) {}
    val addressAndPhoneNumberTextFieldContentList = remember {
        listOf(
            ReportScreenTextFieldContent("Address", Icons.Filled.Place),
            ReportScreenTextFieldContent("Phone Number", Icons.Filled.PhoneAndroid)
        )
    }
    val cityAndStateReportTextFieldContentList = remember {
        listOf(
            ReportScreenTextFieldContent("City", Icons.Filled.LocationCity),
            ReportScreenTextFieldContent("State", Icons.Filled.Apartment)
        )
    }
    Box(
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .navigationBarsPadding()
            .fillMaxSize()
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(text = "Report incident", style = MaterialTheme.typography.h3)// TODO string res
            addressAndPhoneNumberTextFieldContentList.forEach { reportTextFieldContent ->
                ReportScreenTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = reportTextFieldContent.label) },
                    leadingIcon = { Icon(reportTextFieldContent.imageVector, null) },
                    value = reportTextFieldContent.stringValue.value,
                    onValueChange = { reportTextFieldContent.stringValue.value = it },
                    maxLines = 1
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                cityAndStateReportTextFieldContentList.forEach { reportTextFieldContent ->
                    ReportScreenTextField(
                        modifier = Modifier.weight(1f),
                        label = { Text(text = reportTextFieldContent.label) },
                        leadingIcon = { Icon(reportTextFieldContent.imageVector, null) },
                        value = reportTextFieldContent.stringValue.value,
                        onValueChange = { reportTextFieldContent.stringValue.value = it },
                        maxLines = 1
                    )
                }
            }
            ReportScreenTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Type Of Incident") },
                value = "",
                onValueChange = {},
                maxLines = 1
            )
            ReportScreenTextField(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                label = { Text(text = "Description") },
                value = "",
                onValueChange = {},
            )
            Button(onClick = { capturePhotoLauncher.launch() }) {
                Icon(imageVector = Icons.Filled.PhotoCamera, contentDescription = null)
                Text(text = "Attach Image")
            }
        }
        Button(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.BottomStart)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.error),
            onClick = {}
        ) {
            Icon(
                imageVector = Icons.Filled.Report,
                tint = MaterialTheme.colors.onError,
                contentDescription = null
            )
            Text(text = "Report", color = MaterialTheme.colors.onError)
        }
    }
}

@Composable
fun ReportScreenTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    label: (@Composable () -> Unit)? = null,
    maxLines: Int = Int.MAX_VALUE,
) {
    TextField(
        modifier = modifier,
        leadingIcon = leadingIcon,
        label = label,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = MaterialTheme.colors.secondary,
            cursorColor = MaterialTheme.colors.secondary,
            focusedLabelColor = MaterialTheme.colors.secondary
        ),
        maxLines = maxLines,
        value = value,
        onValueChange = onValueChange
    )
}