package com.example.animeapp.components.detail.utils

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun formatResourceString(@StringRes stringResId: Int, value: Any): String {
    return "${stringResource(stringResId)}: $value"
}