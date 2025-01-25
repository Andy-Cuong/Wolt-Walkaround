package com.andyc.core.presentation.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

val NonFavoriteIcon: ImageVector
    @Composable get() = ImageVector.vectorResource(R.drawable.favorite_border_icon)

val FavoriteIcon: ImageVector
    @Composable get() = ImageVector.vectorResource(R.drawable.favorite_icon)

val ErrorIcon: ImageVector
    @Composable get() = ImageVector.vectorResource(R.drawable.error_outline)

val NoImageIcon: ImageVector
    @Composable get() = ImageVector.vectorResource(R.drawable.rounded_question_mark)