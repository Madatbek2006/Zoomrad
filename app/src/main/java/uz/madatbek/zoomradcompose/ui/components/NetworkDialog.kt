package uz.madatbek.zoomradcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import uz.madatbek.zoomradcompose.R

class NetworkDialog {
}
@Composable
fun NetworkDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {

    if (showDialog) {
        Dialog(
            onDismissRequest = {  },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true),

            ) {
            MySurface(
                shape = MaterialTheme.shapes.medium,
//                color = MaterialTheme.colorScheme.surface
            ) {

                Column(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .size(164.dp)
                            .align(Alignment.CenterHorizontally),
                        painter = painterResource(id = R.drawable.ic_dont_have_network), contentDescription =null )
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 16.dp),
                        text = stringResource(id = R.string.network_dialog_txt),
                        textAlign = TextAlign.Center
                    )
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)) {
                        Spacer(modifier = Modifier.weight(0.5f))
                        Card(modifier = Modifier
                            .weight(1f)
                            .padding(bottom = 16.dp)
                            .padding(vertical = 4.dp)
                            .height(56.dp)
                            .clickable {
                                onConfirm()
                            }
                            , colors = CardDefaults.cardColors(colorResource(id = R.color.zumrat))
                        ) {
                            Box(modifier = Modifier.fillMaxSize()){
                                Text(
                                    modifier = Modifier.align(Alignment.Center),
                                    text = stringResource(id = R.string.network_dialog_repeat),
                                    textAlign = TextAlign.Center,
                                    color = Color.White
                                )
                            }
                        }
                        Spacer(modifier = Modifier.weight(0.5f))


                    }
                }

            }
        }
    }

}

@Preview
@Composable
fun DialogPreview() {
    NetworkDialog(showDialog = true, onDismiss = {  }) {

    }
}