package org.work.effect

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color

@SuppressLint("ModifierFactoryUnreferencedReceiver")
inline fun Modifier.rippleClickable(crossinline onClick: () -> Unit): Modifier =
    composed {
        clickable(indication = rememberRipple(color = Color.LightGray),
            interactionSource = remember { MutableInteractionSource() }) {
            onClick()
        }
    }