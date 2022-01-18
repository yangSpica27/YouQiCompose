package me.spica.wuqi.ui.home

import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import me.spica.wuqi.tool.ComposePagerSnapHelper
import me.spica.wuqi.ui.navigation.NavScreen
import me.spica.wuqi.widget.DateView


/**
 * 主页
 */
@Composable
fun HomeScreen(
    navController: NavController,
    dates: List<Any> = arrayListOf("", "", "", "")
) {

    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.DarkGray)
    ) {
        //  分割线
        Spacer(
            modifier =
            Modifier
                .fillMaxWidth()
                .statusBarsPadding()
        )

        Spacer(
            modifier =
            Modifier
                .fillMaxWidth()
                .height(20.dp)
        )


        // 头部
        Header()

        // 卡片列表


        ComposePagerSnapHelper(
            width = 320.dp,
            modifier = Modifier.weight(1F)
        ) {
            LazyRow(
                reverseLayout = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F),
                flingBehavior = ScrollableDefaults.flingBehavior(),
                contentPadding = PaddingValues(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
                state = it
            ) {

                items(dates) {

                    DateCard(it)

                }


            }
        }


        // 迪兰
        BottomBar(
            navController = navController
        )
        Spacer(modifier = Modifier.height(40.dp))
        Spacer(modifier = Modifier.navigationBarsPadding())
    }


}


@Composable
private fun Header() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 12.dp,
                horizontal = 20.dp
            )
    ) {

        val (
            tv72,// 72候
            tv24, // 24节气
            tvDate, // 日期
            btnBackToToday,// 回到今天
        ) = createRefs()

        Text(
            text = "鹊始巢",
            color = Color.White,
            modifier = Modifier.constrainAs(tv72) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            },
            style = MaterialTheme.typography.h4
        )

        Text(
            text = "腊月 十四",
            color = Color.White,
            modifier = Modifier
                .padding(top = 12.dp)
                .constrainAs(tv24) {
                    top.linkTo(tv72.bottom)
                    start.linkTo(tv72.start)
                },
        )

        DateView(month = "11", day = "12",
            Modifier.constrainAs(tvDate) {
                top.linkTo(tv72.top)
                bottom.linkTo(tv24.bottom)
                end.linkTo(parent.end)
            }
        )

    }
}


@Composable
fun BottomBar(
    navController: NavController,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 20.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Start
    ) {

        Image(
            imageVector = Icons.Filled.Share,
            colorFilter = ColorFilter.tint(Color.White),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(text = "24", color = Color.White)

        Spacer(modifier = Modifier.width(40.dp))

        Image(
            imageVector = Icons.Filled.Share,
            colorFilter = ColorFilter.tint(Color.White),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(text = "24", color = Color.White)

        Spacer(modifier = Modifier.weight(1F))

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
                .clickable {
                    // 点击跳转到设置页
                    navController.navigate(NavScreen.Setting.route)
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


@Composable
fun DateCard(content: Any) {
    Card(
        modifier = Modifier
            .width(320.dp)
            .padding(horizontal = 12.dp)
            .height(320.dp),
        contentColor = Color.White,
    )
    {

        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(20.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F),
                contentAlignment = Alignment.Center

            ) {
                Text(
                    text = "测试",
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }


            Text(
                text = "SPICa27",
                modifier = Modifier.fillMaxWidth(),
            )

        }

    }
}