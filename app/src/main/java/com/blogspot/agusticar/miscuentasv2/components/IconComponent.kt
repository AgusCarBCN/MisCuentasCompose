package com.blogspot.agusticar.miscuentasv2.components

import android.net.Uri
import androidx.compose.animation.Animatable
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.blogspot.agusticar.miscuentasv2.R
import com.blogspot.agusticar.miscuentasv2.ui.theme.LocalCustomColorsPalette
import kotlinx.coroutines.launch


@Composable
fun IconComponent(isPressed: Boolean, iconResource: Int, iconSize: Int) {
    // Animación de escala para el punto seleccionado
    val scale = animateFloatAsState(
        targetValue = if (isPressed) 1.5f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "icon"
    )
    val indicatorColor by animateColorAsState(
        targetValue = if (isPressed) {
            LocalCustomColorsPalette.current.iconInvert
        } else {
            LocalCustomColorsPalette.current.iconColor
        },
        label = "indicator color",
        animationSpec = tween(
            durationMillis = 2000, // Duración de la animación
            easing = LinearOutSlowInEasing // Controla la velocidad de la transición
        )
    )

    Icon(
        painter = painterResource(id = iconResource),
        contentDescription = "indicator",
        tint = indicatorColor,
        modifier = Modifier
            .scale(scale.value)
            .size(iconSize.dp)
    )
}

@Composable

fun UserImage(uri: Uri)
{
    Card(
        modifier = Modifier
            .size(80.dp)
            .padding(10.dp),
        shape = CircleShape, // Hace que el Card sea circular
        // Reemplaza lightYellow
    )

    {
            Image(
                painter =if(uri==Uri.EMPTY) painterResource(id = R.drawable.contabilidad)
                else rememberAsyncImagePainter(uri), // Carga la imagen desde el Uri ,
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize() // La imagen ocupa todo el Card
            )
    }
}