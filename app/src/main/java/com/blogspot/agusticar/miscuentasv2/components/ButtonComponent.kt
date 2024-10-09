package com.blogspot.agusticar.miscuentasv2.components


import androidx.compose.animation.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.blogspot.agusticar.miscuentasv2.ui.theme.LocalCustomColorsPalette
import kotlinx.coroutines.launch

@Composable
fun ModelButton(
    text: String,
    fontDimen:Int,
    modifier: Modifier = Modifier, // Permitir modificar el botón desde el exterior
    enabledButton: Boolean = true,
    onClickButton: () -> Unit
) {// Creamos un animatable para manejar el color del ícono
    val initColorButton = LocalCustomColorsPalette.current.buttonColorDefault
    val targetColorButton = LocalCustomColorsPalette.current.buttonTransition
    val initColorText = LocalCustomColorsPalette.current.textButtonColorDefault
    val targetColorText = LocalCustomColorsPalette.current.textTransition

    val colorButton = remember { Animatable(initColorButton) }
    val colorText = remember { Animatable(initColorText) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            // Animación infinita que alterna entre dos colores
            colorButton.animateTo(
                targetValue = targetColorButton,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 5000), // Duración de la transición (1 segundo)
                    repeatMode = RepeatMode.Reverse // Alterna entre los dos colores
                )
            )
        }
    }
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            // Animación infinita que alterna entre dos colores
            colorText.animateTo(
                targetValue = targetColorText,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 2000), // Duración de la transición (1 segundo)
                    repeatMode = RepeatMode.Reverse // Alterna entre los dos colores
                )
            )
        }
    }

    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    // Definir colores

    val pressedContentColor = LocalCustomColorsPalette.current.textButtonColorPressed
    val pressedContainerColor = LocalCustomColorsPalette.current.buttonColorPressed
    val disabled= LocalCustomColorsPalette.current.disableButton
    Button(
        onClick = {
            onClickButton() // Ejecutar el clic pasado como parámetro
        },
        interactionSource = interactionSource, // Para manejar eventos de interacción
        enabled = enabledButton,
        modifier = modifier
            .wrapContentHeight() // Altura ajustable
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = if (pressed) pressedContentColor else colorText.value,
            containerColor = if (pressed) pressedContainerColor else colorButton.value,
            disabledContainerColor= disabled
        ),

        ) {
        Text(
            text = text,
            fontSize = with(LocalDensity.current) { dimensionResource(id = fontDimen).toSp() },
            fontWeight = FontWeight.Bold, // Estilo de texto en negrita
            textAlign = TextAlign.Center
        )
    }
}
