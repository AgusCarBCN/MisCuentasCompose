package com.blogspot.agusticar.miscuentasv2.tutorial.view


import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.blogspot.agusticar.miscuentasv2.R
import com.blogspot.agusticar.miscuentasv2.components.ModelButton
import com.blogspot.agusticar.miscuentasv2.ui.theme.LocalCustomColorsPalette
import com.blogspot.agusticar.miscuentasv2.main.model.Routes
import com.blogspot.agusticar.miscuentasv2.tutorial.model.TutorialItem
import kotlinx.coroutines.launch

@Composable
fun Tutorial(tutorialViewModel:TutorialViewModel,modifier: Modifier,navToScreen:()->Unit,listOfItems: List<TutorialItem> = getItems()) {

    val toLogin by tutorialViewModel.toLogin.observeAsState(false)

    ConstraintLayout(

        modifier
            .fillMaxSize()
            .background(LocalCustomColorsPalette.current.backgroundPrimary)
    ) {

        val (horizontalPager, loginButton) = createRefs()
        //Cargar valor de DataStore...

        val pagerState = rememberPagerState(pageCount = {
            listOfItems.size  // Cada página corresponde a un item de la lista
        })

        Column(
            modifier.constrainAs(horizontalPager) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(loginButton.top)
            }
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)

            ) { page ->
                // Contenido de cada página
                ItemCard(item = listOfItems[page]) // Mostrando un item en cada página
            }

            CircleIndicator(totalDots = listOfItems.size, pagerState.targetPage)
        }


        ModelButton(text = stringResource(id =if(toLogin) R.string.loginButton else R.string.createProfileButton),
            R.dimen.text_title_medium,modifier = Modifier
                .width(360.dp)
                .constrainAs(loginButton) {
                    top.linkTo(horizontalPager.bottom)          // Parte superior anclada al imageBox
                    start.linkTo(parent.start)           // Empieza en el lado izquierdo del padre
                    end.linkTo(parent.end)               // Termina en el lado derecho del padre
                    bottom.linkTo(parent.bottom)         // Parte inferior anclada al padre
                }, true,
            onClickButton = {navToScreen()}

                         // Lógica para navegar a la pantalla de login o crear cuenta
               /* if (toLogin) {
                    navigationController.navigate(Routes.Login.route)
                } else {
                    navigationController.navigate(Routes.CreateProfile.route)
                }*/


        )
    }

}

@Composable
private fun ItemCard(item: TutorialItem) {
    // Un card sencillo que muestra título, descripción e ícono
    Card(
        modifier = Modifier
            .width(360.dp)
            .height(460.dp)

    ) {
        Column(
            modifier = Modifier
                .background(LocalCustomColorsPalette.current.backgroundPrimary)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = item.titleItem,
                modifier = Modifier
                    .width(360.dp)
                    .padding(top = 15.dp, bottom = 15.dp)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = with(LocalDensity.current) { dimensionResource(id = R.dimen.text_title_medium).toSp() },
                color = (LocalCustomColorsPalette.current.boldTextColor)
            )
            Spacer(modifier = Modifier.width(5.dp)) // Espacio entre imagen y texto
            // Mostrar imagen
            Image(
                painter = painterResource(id = item.iconItem),
                contentDescription = null, // No se requiere descripción accesible para imágenes decorativas
                modifier = Modifier
                    .width(250.dp)
                    .height(200.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.width(5.dp)) // Espacio entre imagen y texto
            Text(
                text = item.descriptionItem,
                modifier = Modifier
                    .width(360.dp)
                    .height(220.dp)
                    .padding(top = 10.dp, bottom = 5.dp)
                    .align(Alignment.CenterHorizontally),

                fontWeight = FontWeight.Normal,
                fontSize = with(LocalDensity.current) { dimensionResource(id = R.dimen.text_body_extra_large).toSp() },
                color = LocalCustomColorsPalette.current.textColor
            )

        }

    }
}


@Composable
private fun getItems(): List<TutorialItem> {
    // Obtener recursos de string usando stringResource
    return listOf(
        TutorialItem(
            title = stringResource(id = R.string.title0),
            description = stringResource(id = R.string.des0),
            icon = R.drawable.contabilidad
        ),
        TutorialItem(
            title = stringResource(id = R.string.title1),
            description = stringResource(id = R.string.des1),
            icon = R.drawable.person_intro
        ),
        TutorialItem(
            title = stringResource(id = R.string.title2),
            description = stringResource(id = R.string.des2),
            icon = R.drawable.payments_intro
        ),
        TutorialItem(
            title = stringResource(id = R.string.title3),
            description = stringResource(id = R.string.des3),
            icon = R.drawable.barchart_intro
        ),
        TutorialItem(
            title = stringResource(id = R.string.title4),
            description = stringResource(id = R.string.des4),
            icon = R.drawable.notifications_intro
        ),
        TutorialItem(
            title = stringResource(id = R.string.title5),
            description = stringResource(id = R.string.des5),
            icon = R.drawable.settings_intro
        )
    )
}

@Composable
private fun CircleIndicator(
    totalDots: Int,
    selectedIndex: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxWidth(),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),

            ) {
            repeat(totalDots) { index ->
                // Animación de escala para el punto seleccionado
                val scale = animateFloatAsState(
                    targetValue = if (index == selectedIndex) 1.1f else 1f, // Escala más grande si es el seleccionado
                    animationSpec = spring(dampingRatio = Spring.DefaultDisplacementThreshold, stiffness = Spring.StiffnessLow)
                )
                Icon(
                    painter = painterResource(if(index==selectedIndex)R.drawable.indicatorselected
                    else R.drawable.circleindicator ),
                    contentDescription = "indicator",
                    tint = if (index == selectedIndex) LocalCustomColorsPalette.current.indicatorSelected
                    else LocalCustomColorsPalette.current.indicatorDefault,
                    modifier = Modifier
                        .scale(scale.value)


                )
            }
        }
    }

}