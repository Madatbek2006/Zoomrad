package uz.madatbek.zoomradcompose.presenter.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Switch
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.presenter.screens.settings.components.SettingsLanguageItem
import uz.madatbek.zoomradcompose.presenter.screens.settings.components.SettingsThemeItem
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.ui.theme.ThemeType
import uz.madatbek.zoomradcompose.ui.theme.ZoomradTheme
import uz.madatbek.zoomradcompose.utils.Languages
import uz.madatbek.zoomradcompose.utils.getCurrentLanguage
import uz.madatbek.zoomradcompose.utils.myLog
import uz.madatbek.zoomradcompose.utils.setLocale

class SettingScreen : Screen {
    @Composable
    override fun Content() {
        val context = LocalContext.current
        val viewModel = getViewModel<SettingModel>()
        val currentTheme = remember { mutableStateOf(MyShar.getTheme()) }

        // Функция для обновления темы
        fun updateTheme(theme: String) {
            MyShar.setTheme(theme)
            currentTheme.value = theme
        }
        SettingComponent(viewModel::onEventDispatchers, onСhangeLanguage = {
            context.setLocale(it)
            MyShar.setLanguage(it)
        }, onChangeTheme =  {
            updateTheme(it)
        },currentTheme)
    }
}

@Composable
fun SettingComponent(
    onEventDispatchers: (SettingContract.Intent) -> Unit,
    onСhangeLanguage: (language: String) -> Unit,
    onChangeTheme: (theme: String) -> Unit,
    theme:State<String>
) {
    val changeLanguage = remember {
        mutableStateOf(getCurrentLanguage())
    }
    val isOpenLanguage = remember {
        mutableStateOf(false)
    }
    val isOpenNotification = remember {
        mutableStateOf(false)
    }
    val isOpenTheme = remember {
        mutableStateOf(false)
    }
    val monitorSwitch = remember {
        mutableStateOf(false)
    }
    val notificationSwitch1 = remember {
        mutableStateOf(false)
    }
    val notificationSwitch2 = remember {
        mutableStateOf(false)
    }
    if (changeLanguage.value != ""&&theme.value!="") {
        ZoomradTheme(
            currentTheme = theme.value
        ) {
            MySurface(
                modifier = Modifier.fillMaxSize()
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)

                    ) {
                        Icon(
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(16.dp)
                                .size(24.dp)
                                .clickable(
                                    onClick = {
                                        onEventDispatchers(SettingContract.Intent.OnCLickBack)
                                    },
                                    indication = rememberRipple(
                                        bounded = false,
                                        radius = 24.dp
                                    ),
                                    interactionSource = remember { MutableInteractionSource() }
                                ),
                            painter = painterResource(id = R.drawable.ic_back_ios),
                            contentDescription = null,
                            tint = colorResource(id = R.color.zumrat)
                        )
                        Text(
                            text = stringResource(id = R.string.settings_settings_txt),
                            modifier = Modifier.align(Alignment.Center),
                        )
                    }
                    LazyColumn{
                        item {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 24.dp)
                                    .height(64.dp)
                                    .clickable {
                                        isOpenLanguage.value = !isOpenLanguage.value
                                        getCurrentLanguage().myLog()
                                    }
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .padding(start = 32.dp)
                                            .align(Alignment.CenterVertically)
                                            .size(24.dp),
                                        painter = painterResource(id = R.drawable.ic_network),
                                        contentDescription = null,
                                        tint = colorResource(id = R.color.zumrat)
                                    )
                                    Text(
                                        modifier = Modifier
                                            .align(Alignment.CenterVertically)
                                            .padding(start = 16.dp),
                                        text = stringResource(id = R.string.settings_language_txt)
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Icon(
                                        modifier = Modifier
                                            .align(Alignment.CenterVertically)
                                            .padding(end = 32.dp),
                                        painter = if (isOpenLanguage.value) painterResource(id = R.drawable.arrow_up) else painterResource(
                                            id = R.drawable.arrow_down
                                        ), contentDescription = null,
                                        tint = colorResource(id = R.color.zumrat)
                                    )
                                }
                                if (isOpenLanguage.value) {
                                    SettingsLanguageItem(type = Languages.UZB, txt = "O'zbekcha", isCheck = MyShar.getLanguage()==Languages.UZB, onClick = {
                                        onСhangeLanguage(it)
                                        changeLanguage.value = it
                                        isOpenLanguage.value = false
                                    })
                                    SettingsLanguageItem(type = Languages.RUS, txt = "Русский", isCheck = MyShar.getLanguage()==Languages.RUS, onClick = {
                                        onСhangeLanguage(it)
                                        changeLanguage.value = it
                                        isOpenLanguage.value = false
                                    })
                                    SettingsLanguageItem(type = Languages.ENG, txt = "English", isCheck = MyShar.getLanguage()==Languages.ENG, onClick = {
                                        onСhangeLanguage(it)
                                        changeLanguage.value = it
                                        isOpenLanguage.value = false
                                    })

                                }
                            }
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp)
                                .height(64.dp)
                                .clickable {
                                }
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .padding(start = 32.dp)
                                        .align(Alignment.CenterVertically)
                                        .size(24.dp),
                                    painter = painterResource(id = R.drawable.ic_lock),
                                    contentDescription = null,
                                    tint = colorResource(id = R.color.zumrat)
                                )
                                Text(
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(start = 16.dp),
                                    text = stringResource(id = R.string.settings_sequrity_txt)
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Icon(
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(end = 32.dp)
                                        .size(16.dp),
                                    painter = painterResource(id = R.drawable.arrow_next),
                                    contentDescription = null,
                                    tint = colorResource(id = R.color.zumrat)
                                )
                            }
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp)
                                .height(64.dp)
                                .clickable {
                                }
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .padding(start = 32.dp)
                                        .align(Alignment.CenterVertically)
                                        .size(24.dp),
                                    painter = painterResource(id = R.drawable.ic_monitoring_icon),
                                    contentDescription = null,
                                    tint = colorResource(id = R.color.zumrat)
                                )
                                Text(
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(start = 16.dp),
                                    text = stringResource(id = R.string.settings_monitoring_txt)
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Switch(
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(end = 32.dp)
                                        .size(24.dp),
                                    checked = monitorSwitch.value,
                                    onCheckedChange = {
                                        monitorSwitch.value = !monitorSwitch.value
                                    }
                                )
                            }
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 24.dp)
                                    .height(64.dp)
                                    .clickable {
                                        isOpenNotification.value = !isOpenNotification.value
                                    }
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .padding(start = 32.dp)
                                            .align(Alignment.CenterVertically)
                                            .size(24.dp),
                                        painter = painterResource(id = R.drawable.ic_bell),
                                        contentDescription = null,
                                        tint = colorResource(id = R.color.zumrat)
                                    )
                                    Text(
                                        modifier = Modifier
                                            .align(Alignment.CenterVertically)
                                            .padding(start = 16.dp),
                                        text = stringResource(id = R.string.settings_notification_txt)
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Icon(
                                        modifier = Modifier
                                            .align(Alignment.CenterVertically)
                                            .padding(end = 32.dp),
                                        painter = if (isOpenNotification.value) painterResource(id = R.drawable.arrow_up) else painterResource(
                                            id = R.drawable.arrow_down
                                        ), contentDescription = null,
                                        tint = colorResource(id = R.color.zumrat)
                                    )
                                }
                                if (isOpenNotification.value) {
                                    Row {
                                        Text(
                                            modifier = Modifier
                                                .padding(start = 72.dp)
                                                .padding(vertical = 16.dp),
                                            text = stringResource(id = R.string.settings_notification_txt1)
                                        )
                                        Spacer(modifier = Modifier.weight(1f))
                                        Switch(
                                            modifier = Modifier
                                                .align(Alignment.CenterVertically)
                                                .padding(end = 32.dp)
                                                .size(24.dp),
                                            checked = notificationSwitch1.value,
                                            onCheckedChange = {
                                                notificationSwitch1.value = !notificationSwitch1.value
                                            }
                                        )
                                    }
                                    Row {
                                        Text(
                                            modifier = Modifier
                                                .padding(start = 72.dp)
                                                .padding(vertical = 16.dp),
                                            text = stringResource(id = R.string.settings_notification_txt2)
                                        )
                                        Spacer(modifier = Modifier.weight(1f))
                                        Switch(
                                            modifier = Modifier
                                                .align(Alignment.CenterVertically)
                                                .padding(end = 32.dp)
                                                .size(24.dp),
                                            checked = notificationSwitch2.value,
                                            onCheckedChange = {
                                                notificationSwitch2.value = !notificationSwitch2.value
                                            }
                                        )
                                    }

                                }
                            }
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp)
                                .height(64.dp)
                                .clickable {
                                    onEventDispatchers(SettingContract.Intent.OpenReadScreen)
                                }
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .padding(start = 32.dp)
                                        .align(Alignment.CenterVertically)
                                        .size(24.dp),
                                    painter = painterResource(id = R.drawable.ic_change_offer),
                                    contentDescription = null,
                                    tint = colorResource(id = R.color.zumrat)
                                )
                                Text(
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(start = 16.dp),
                                    text = stringResource(id = R.string.settings_public_ofera_txt)
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Icon(
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(end = 32.dp)
                                        .size(16.dp),
                                    painter = painterResource(id = R.drawable.arrow_next),
                                    contentDescription = null,
                                    tint = colorResource(id = R.color.zumrat)
                                )
                            }
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 24.dp)
                                    .height(64.dp)
                                    .clickable {
                                        isOpenTheme.value = !isOpenTheme.value
                                    }
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .padding(start = 32.dp)
                                            .align(Alignment.CenterVertically)
                                            .size(24.dp),
                                        painter = painterResource(id = R.drawable.ic_network),
                                        contentDescription = null,
                                        tint = colorResource(id = R.color.zumrat)
                                    )
                                    Text(
                                        modifier = Modifier
                                            .align(Alignment.CenterVertically)
                                            .padding(start = 16.dp),
                                        text = stringResource(id = R.string.settings_theme_txt)
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Icon(
                                        modifier = Modifier
                                            .align(Alignment.CenterVertically)
                                            .padding(end = 32.dp),
                                        painter = if (isOpenTheme.value) painterResource(id = R.drawable.arrow_up) else painterResource(
                                            id = R.drawable.arrow_down
                                        ), contentDescription = null,
                                        tint = colorResource(id = R.color.zumrat)
                                    )
                                }
                                if (isOpenTheme.value) {
                                    SettingsThemeItem(
                                        type = ThemeType.SYSTEM,
                                        res = R.string.settings_theme_txt1,
                                        isCheck = MyShar.getTheme() == ThemeType.SYSTEM,
                                        onClick = {
                                            onChangeTheme(it)
                                            isOpenTheme.value = false
                                        })
                                    SettingsThemeItem(
                                        type = ThemeType.LIGHT,
                                        res = R.string.settings_theme_txt2,
                                        isCheck = MyShar.getTheme() == ThemeType.LIGHT,
                                        onClick = {
                                            onChangeTheme(it)
                                            isOpenTheme.value = false
                                        })
                                    SettingsThemeItem(
                                        type = ThemeType.NIGHT,
                                        res = R.string.settings_theme_txt3,
                                        isCheck = MyShar.getTheme() == ThemeType.NIGHT,
                                        onClick = {
                                            onChangeTheme(it)
                                            isOpenTheme.value = false
                                        })
                                    SettingsThemeItem(
                                        type = ThemeType.DARK,
                                        res = R.string.settings_theme_txt4,
                                        isCheck = MyShar.getTheme() == ThemeType.DARK,
                                        onClick = {
                                            onChangeTheme(it)
                                            isOpenTheme.value = false
                                        })
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun SettingPreview() {
    SettingComponent({},{},{}, remember {
        mutableStateOf("")
    })
}