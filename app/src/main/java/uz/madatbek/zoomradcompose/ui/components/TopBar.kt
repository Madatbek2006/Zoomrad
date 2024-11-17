package uz.madatbek.zoomradcompose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.presenter.screens.profile.ProfileContract

@Composable
fun TopBar(modifier: Modifier=Modifier,onClickBack:()->Unit,titleCenter:String) {
    Box(modifier = modifier.fillMaxWidth()) {
        Box(modifier = Modifier
            .width(56.dp)
            .height(56.dp)
            .align(Alignment.CenterStart)
            .clickable(
                onClick = onClickBack,
                indication = rememberRipple(
                    bounded = false,
                    radius = 36.dp
                ),
                interactionSource = remember { MutableInteractionSource() } // Источник взаимодействия
            )
        ){
            Icon(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .align(Alignment.Center),
                painter = painterResource(id = R.drawable.ic_back_ios),
                contentDescription = null,
                tint = colorResource(id = R.color.zumrat)
            )
        }

        Text(
            text = titleCenter, modifier = Modifier.align(Alignment.Center),
            color = Color.Gray
        )
    }
}