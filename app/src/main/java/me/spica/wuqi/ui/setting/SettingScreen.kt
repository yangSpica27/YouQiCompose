package me.spica.wuqi.ui.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import me.spica.wuqi.R


@Composable
fun SettingScreen() {


    Column(
        Modifier
            .padding(horizontal = 20.dp, vertical = 24.dp)
            .fillMaxHeight()
            .background(MaterialTheme.colors.background)
            .fillMaxWidth()
            .statusBarsPadding()
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.Top
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            Image(
                painter =
                painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = null
            )

            Text(
                text = "",
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1F)
            )

            Image(
                painter =
                painterResource(id = R.drawable.ic_setting_one),
                contentDescription = null
            )

        }

        Header()

    }
}


@Composable
private fun Header() {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    )
    {

        val (
            tvUserName,
            tvDesc,
            ivAvatar,
        ) = createRefs()

        Text(
            text = "登录",
            modifier = Modifier.constrainAs(tvUserName) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
            fontStyle = MaterialTheme.typography.h3.fontStyle,
            fontSize = 26.sp,
        )

        Text(
            text = "if we dream，everything is possible",
            modifier = Modifier
                .padding(top = 12.dp)
                .constrainAs(tvDesc) {
                    top.linkTo(tvUserName.bottom)
                    start.linkTo(tvUserName.start)
                },
        )

        BoxWithConstraints(
            Modifier
                .width(60.dp)
                .height(60.dp)
                .clip(
                    CircleShape
                )
                .background(
                    color = Color.White,
                    shape = CircleShape,
                )
                .padding(4.dp)
                .constrainAs(ivAvatar) {
                    top.linkTo(tvUserName.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(tvDesc.bottom)
                }
        )
        {

            Image(
                painter = painterResource(
                    id =
                    me.spica.wuqi.R.drawable.ic_default_avatar
                ),
                contentDescription = null
            )


        }

    }

}