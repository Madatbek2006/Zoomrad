package com.sudo_pacman.paynet.presenter.screens.fast_payment.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.madatbek.zoomradcompose.ui.components.MySurface
import uz.madatbek.zoomradcompose.utils.visualtransformation.PhoneMaskTransformation

val PHONE_NUMBER_LENGTH = 9

@Composable
fun PhoneInputFastPayment(
    text: String = "",
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    error: String? = null,
    onTextChanged: (value: String) -> Unit = {}
) {
    MySurface {
        Column(modifier = modifier) {
            BasicTextField(
                value = text,
                onValueChange = {
                    if (it.length <= PHONE_NUMBER_LENGTH) {
                        onTextChanged(it)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                enabled, readOnly,
                singleLine = true,
                maxLines = 1,
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
//                fontFamily = FontFamily(Font(R.font.pnfont_semibold))
                    color = MaterialTheme.colorScheme.onSurface
                ),

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = PhoneMaskTransformation,
//            cursorBrush = SolidColor(
//                getColorX(colorName = activeButtonGreenColor)
//            ),
                decorationBox = { innerTextField ->

                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
//                        .border(
//                            width = 1.dp,
////                            color = if (isError) getColorX(colorName = errorColorX) else getColorX(
////                                colorName = gray
////                            ),
//                            shape = RoundedCornerShape(8.dp)
//                        )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp)
                        ) {

                            Text(
                                text = "+998",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Box(
                                modifier = Modifier
                                    .padding(
                                        start = 8.dp,
                                        end = 8.dp

                                    )
                                    .align(Alignment.CenterVertically)
                            ) {
                                innerTextField()
                            }

                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }

                }
            )
//        if (isError) {
//            Text(
//                error ?: stringResource(id = R.string.unknown_error),
//                style = Typography.headlineLarge
//            )
//        }
        }
    }
}