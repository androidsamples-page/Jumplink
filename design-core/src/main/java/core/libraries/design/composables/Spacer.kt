package core.libraries.design.composables

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun ColumnScope.VerticalSpacer(value: Dp) {
    Spacer(modifier = Modifier.height(value))
}

@Composable
fun HorizontalSpacer(value: Dp) {
    Spacer(modifier = Modifier.width(value))
}