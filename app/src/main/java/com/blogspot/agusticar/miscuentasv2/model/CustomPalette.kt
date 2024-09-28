package com.blogspot.agusticar.miscuentasv2.model

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.blogspot.agusticar.miscuentasv2.ui.theme.Black
import com.blogspot.agusticar.miscuentasv2.ui.theme.CoralPink
import com.blogspot.agusticar.miscuentasv2.ui.theme.Crimson
import com.blogspot.agusticar.miscuentasv2.ui.theme.DarkBrown
import com.blogspot.agusticar.miscuentasv2.ui.theme.DeepPurple
import com.blogspot.agusticar.miscuentasv2.ui.theme.LightGrey
import com.blogspot.agusticar.miscuentasv2.ui.theme.LightYellow
import com.blogspot.agusticar.miscuentasv2.ui.theme.PeachyCream
import com.blogspot.agusticar.miscuentasv2.ui.theme.SoftPeach
import com.blogspot.agusticar.miscuentasv2.ui.theme.White

@Immutable
data class CustomColorsPalette(
    val buttonColorDefault: Color = Color.Unspecified,
    val buttonColorPressed: Color = Color.Unspecified,
    val disableButton:Color = Color.Unspecified,
    val textButtonColorDefault: Color = Color.Unspecified,
    val textButtonColorPressed: Color = Color.Unspecified,
    val backgroundPrimary: Color = Color.Unspecified,
    val backgroundSecondary: Color = Color.Unspecified,
    val barBackground: Color = Color.Unspecified,
    val textColor: Color = Color.Unspecified,
    val boldTextColor: Color = Color.Unspecified,
    val barColor: Color = Color.Unspecified,
    val iconColor: Color = Color.Unspecified,
    val contentBarColor:Color = Color.Unspecified,
    val textFieldColor:Color=Color.Unspecified,
    val indicatorDefault:Color = Color.Unspecified,
    val indicatorSelected:Color = Color.Unspecified,
    val imageBackground:Color = Color.Unspecified,
    val drawerColor:Color = Color.Unspecified,
    val headDrawerColor:Color = Color.Unspecified,
    val contentDrawerColor:Color = Color.Unspecified
    )

val LightCustomColorsPalette = CustomColorsPalette(
    buttonColorDefault =  SoftPeach,
    buttonColorPressed =  CoralPink,
    disableButton = PeachyCream,
    textButtonColorDefault =  Black,
    textButtonColorPressed =  White,
    backgroundPrimary =  PeachyCream,
    backgroundSecondary =  LightYellow,
    barBackground= CoralPink,
    textColor =  DarkBrown,
    boldTextColor =  Black,
    iconColor = Black,
    contentBarColor = Black,
    textFieldColor = LightYellow,
    indicatorDefault =  SoftPeach,
    indicatorSelected =  CoralPink,
    imageBackground = CoralPink,
    drawerColor = LightYellow,
    headDrawerColor = SoftPeach,
    contentDrawerColor = Black
)
val DarkCustomColorsPalette = CustomColorsPalette(
    buttonColorDefault =  SoftPeach,
    buttonColorPressed =  PeachyCream,
    disableButton = SoftPeach,
    textButtonColorDefault =  White,
    textButtonColorPressed =  Black,
    backgroundPrimary =  DarkBrown,
    backgroundSecondary =  DarkBrown,
    barBackground= Black,
    textColor =  LightYellow,
    boldTextColor =  White,
    iconColor = White,
    contentBarColor = White,
    textFieldColor = LightGrey,
    indicatorDefault =  Crimson,
    indicatorSelected =  SoftPeach,
    imageBackground = Black,
    drawerColor = PeachyCream,
    headDrawerColor = SoftPeach,
    contentDrawerColor = DarkBrown
)

val LocalCustomColorsPalette = staticCompositionLocalOf { CustomColorsPalette() }
