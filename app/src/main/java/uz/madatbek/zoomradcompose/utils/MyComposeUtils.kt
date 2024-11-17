package uz.madatbek.zoomradcompose.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.ui.theme.ThemeType


@Composable
fun RowScope.TabNavigatorItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        label = {
            Text(
                text = tab.options.title,
                modifier = Modifier.padding(vertical = 8.dp),
                fontSize = 12.sp
            )
        },
        icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) },

        selectedContentColor = colorResource(id =R.color.zumrat ), // Цвет иконки и текста при выборе
        unselectedContentColor = if (MyShar.getTheme()==ThemeType.LIGHT)Color.Gray else Color.White, // Цвет иконки и текста без выбора
        alwaysShowLabel = true,
    )
}

