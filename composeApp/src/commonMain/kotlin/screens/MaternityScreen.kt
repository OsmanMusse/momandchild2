package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arkivanov.decompose.ComponentContext
import components.MultiColorText
import components.MultiStyleText
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import org.example.momandbaby2.MR
import verticalScrollWithScrollbar

data class RelatedLinksModel(
    val title: String,
    val icon: ImageResource,
)

class MaternityScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column(
            Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScrollWithScrollbar(rememberScrollState())
                    .background(Color.White)
                    .weight(1f,false)
                    .padding(top = 100.dp)
            ) {

                Column(modifier = Modifier.padding(horizontal = 16.dp).background(Color.White)) {

                    Text(
                        text = "Explore maternity units in your area",
                        color = colorResource(MR.colors.h2_color),
                        fontSize = 22.5.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(end = 14.dp)
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    MultiColorText(
                        Pair(
                            first = "If your NHS area is already represented in this app select it in",
                            second = colorResource(MR.colors.p_color)
                        ),
                        Pair(
                            first = " Find my NHS area",
                            second = colorResource(MR.colors.blackish_color)
                        ),
                        Pair(
                            first = " to access your choice of maternity unit and to gain local contacts and local information. If your area is not shown, you can search",
                            second = colorResource(MR.colors.p_color)
                        ),
                        Pair(
                            first = " NHS Find maternity services",
                            second = colorResource(MR.colors.primaryColor)
                        ),
                        Pair(
                            first = " to find vour nearest units; you will still be able to use all the advice and guidance within the app.",
                            second = colorResource(MR.colors.p_color),
                        )
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Button(
                        modifier = Modifier.fillMaxWidth().height(47.dp),
                        onClick = {},
                        contentPadding = PaddingValues(horizontal = 23.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(MR.colors.primaryColor))
                    ) {
                        Text(
                            text = "Find my NHS area",
                            modifier = Modifier.fillMaxWidth().weight(1f),
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Start
                        )

                        Icon(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(MR.images.arrow_right),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "What to Consider?",
                        color = colorResource(MR.colors.h2_color),
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "Many women opt to book for care with their nearest maternity unit and there are some benefits to making this choice:",
                        color = colorResource(MR.colors.p_color),
                        fontSize = 15.sp,
                    )

                    val item1 =
                        "you are more likely to receive care from a small team of midwives, in a location close to home"
                    val item2 =
                        "you are more likely to get to know one midwife, and one team which can improve your maternity experience"
                    val item3 = "where you have previously received maternity or other health care"

                    val list = arrayOf(item1, item2, item3)

                    Spacer(modifier = Modifier.height(15.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
                        list.forEach {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .align(Alignment.Top)
                                        .offset(y = 7.dp)
                                        .padding(start = 16.dp, end = 8.dp)
                                        .size(8.dp)
                                        .background(
                                            colorResource(MR.colors.primaryColor),
                                            shape = CircleShape
                                        ),
                                )

                                Spacer(modifier = Modifier.width(18.dp))

                                Text(
                                    text = "$it",
                                    color = colorResource(MR.colors.p_color),
                                    fontSize = 15.sp,
                                    modifier = Modifier.align(Alignment.Top),
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "If your area is not shown in the app, you can visit the website of your local maternity unit to find the core services it offers.",
                        color = colorResource(MR.colors.p_color),
                        fontSize = 15.sp,
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Find information about private maternity care:",
                        color = colorResource(MR.colors.p_color),
                        fontSize = 15.sp,
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Button(
                        modifier = Modifier.fillMaxWidth().height(47.dp),
                        onClick = {},
                        contentPadding = PaddingValues(horizontal = 23.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(MR.colors.primaryColor))
                    ) {
                        Text(
                            text = "Private maternity care in vour area",
                            modifier = Modifier.fillMaxWidth().weight(1f),
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Start
                        )

                        Icon(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(MR.images.arrow_right),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(13.dp))

                    Text(
                        text = "Explore the related links below for reviews and reports on your area maternity units.",
                        color = colorResource(MR.colors.p_color),
                        fontSize = 15.sp,
                    )

                }

                Spacer(modifier = Modifier.height(30.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(bottom = 5.dp)
                        .background(Color(238, 247, 246))
                ) {
                    val relatedLink1 =
                        RelatedLinksModel(title = "Care Quality Commission", icon = MR.images.worldwide_icon)
                    val relatedLink2 = RelatedLinksModel(title = "Which?", icon = MR.images.worldwide_icon)
                    val relatedLink3 = RelatedLinksModel(title = "NHS Choices", icon = MR.images.nhs_logo)
                    val relatedList = arrayListOf(relatedLink1, relatedLink2, relatedLink3)
                    Column(modifier = Modifier.fillMaxWidth()) { }
                    Text(
                        text = "Related links",
                        color = Color(78, 169, 150),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                    )

                    relatedList.forEach {
                        Row(
                            modifier = Modifier
                            .fillMaxWidth()
                            .padding(22.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(45.dp)
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(color = colorResource(MR.colors.dark_light_color)),
                                contentAlignment = Alignment.Center
                            ){
                                Icon(
                                    modifier = Modifier.size(25.dp),
                                    painter = painterResource(it.icon),
                                    contentDescription = null,
                                    tint = Color.Unspecified
                                )
                            }

                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "${it.title}",
                                modifier = Modifier.fillMaxWidth().weight(1f),
                                color = Color(60, 159, 138),
                                fontSize = 14.sp
                            )

                            Spacer(modifier = Modifier.width(15.dp))


                            Icon(
                                modifier = Modifier.size(25.dp),
                                painter = painterResource(MR.images.arrow_right),
                                contentDescription = null,
                                tint = colorResource(MR.colors.trackColor)
                            )
                        }
                    }

                }
            }


            Button(
                onClick = {},
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(MR.colors.btn_dark_green))
            ) {
                Text(
                    text = "Change Area".uppercase(),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth().weight(1f)
                )
                Icon(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(MR.images.arrow_right),
                    contentDescription = null,
                    tint = Color.White
                )
            }

        }
    }


}