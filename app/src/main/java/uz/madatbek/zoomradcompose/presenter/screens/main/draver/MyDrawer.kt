package uz.madatbek.zoomradcompose.presenter.screens.main.draver

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import uz.madatbek.zoomradcompose.R
import uz.madatbek.zoomradcompose.data.model.DrawerData
import uz.madatbek.zoomradcompose.data.sourse.local.MyShar
import uz.madatbek.zoomradcompose.presenter.screens.main.drawerData
import uz.madatbek.zoomradcompose.presenter.screens.main.pages.help.HelpPage
import uz.madatbek.zoomradcompose.ui.components.UnidentifiedClient
import uz.madatbek.zoomradcompose.utils.toPhoneFormat


@Composable
fun MyDrawer(
    modifier: Modifier = Modifier,
    onClickProfile: () -> Unit,
    onClickDrawerData: (DrawerData)->Unit
) {

    androidx.compose.material3.Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background,
    ) {
        Box(modifier = Modifier
            .fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.25f)
                        .clickable {
                            onClickProfile()
                        }
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.bg_top),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(8.dp)
                    ) {

                        UnidentifiedClient(modifier = Modifier)
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            text = (
                                    MyShar.getUserLoginData()?.phone?:
                                    "+998912345679").toPhoneFormat(),
                            color = Color.White
                        )
                    }

                    Image(
                        modifier= Modifier
                            .padding(start = 16.dp, top = 16.dp)
                            .width(64.dp)
                            .height(64.dp)
                            .clip(RoundedCornerShape(300.dp))
                            .align(Alignment.TopStart),
                        painter = painterResource(id = R.drawable.img_user),
                        contentDescription =null
                    )

                }
                for (i in 0 until drawerData.size) {
                    DrawerItem(data = drawerData[i], onClick = {

                        onClickDrawerData(it)
                    })
                }
            }
        }
    }
}




@Composable
fun DrawerItem(data: DrawerData, onClick: (data: DrawerData) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable {
                onClick(data)
            }
    ) {
        Image(
            modifier = Modifier
                .padding(16.dp)
                .width(24.dp)
                .height(24.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(id = data.icon), contentDescription = null,
            colorFilter = ColorFilter.tint(colorResource(id = R.color.zumrat))
        )
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = stringResource(id = data.name),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}



@Preview
@Composable
fun MyDrawerPreview() {

    MyDrawer(
        modifier=Modifier,
    onClickProfile= {},
    onClickDrawerData= {  }
    )
}
