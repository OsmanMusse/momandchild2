package screens

import Choice
import ScrollBarConfig
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.ScreenModelStore
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.stack.mutableStateStackOf
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.screenModel.rememberNavigatorScreenModel
import com.russhwolf.settings.Settings
import components.MultiStyleText
import data.local.PreferencesKeys
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.painterResource
import helpers.Utils
import org.example.momandbaby2.MR
import verticalScrollWithScrollbar

val settings: Settings = Settings()

class HomeScreenModel: ScreenModel {
    var dueDate: Long = settings.getLong(PreferencesKeys.DUE_DATE,0L)
}

data class HomeScreen(
    private val scaffoldPaddingValues: PaddingValues
):Screen {

    @OptIn(ExperimentalVoyagerApi::class)
    @Composable
    override fun Content() {
        val interactionSource = remember { MutableInteractionSource() }
        val navigator = LocalNavigator.currentOrThrow
        val sharedScreenModel = navigator.rememberNavigatorScreenModel { HomeScreenModel() }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScrollWithScrollbar(
                    state = rememberScrollState(),
                    scrollbarConfig = ScrollBarConfig(
                        padding = PaddingValues(4.dp, 4.dp, 0.5.dp, 4.dp)
                    )
                )
                .padding(top = 80.dp, start = 10.dp, end = 10.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Welcome to",
                fontFamily = fontFamilyResource(MR.fonts.Yrsa.regular),
                fontSize = 25.sp,
                color = colorResource(MR.colors.h2_color),
            )

            Text(
                text = "mum & baby",
                fontFamily = fontFamilyResource(MR.fonts.LobsterTwo.bold),
                fontSize = 30.sp,
                color = colorResource(MR.colors.primaryColor)
            )

            Spacer(modifier = Modifier.height(20.dp))
//
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = { navigator.push(DueDateScreen()) }
                    )
                    .padding(horizontal = 7.dp)
                    .border(
                        width = 1.25.dp,
                        color = Color.Black.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(corner = CornerSize(3.5.dp))
                    )
                    .indication(interactionSource, indication = null),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if(sharedScreenModel.dueDate == 0L){
                    Text(
                        text =  "Add your due date",
                        color = colorResource(MR.colors.brown_color),
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(13.dp),
                    )
                } else {
                    val weeksDue = Utils.calculateDueDate(sharedScreenModel.dueDate)
                    MultiStyleText(
                        text1 = "I'm ",
                        color1 = colorResource(MR.colors.brown_color),
                        text2 = "$weeksDue",
                        color2 = colorResource(MR.colors.primaryColor),
                        text3 = if (weeksDue == 1) " week pregnant" else " weeks pregnant"
                    )
                }
            }


            Spacer(modifier = Modifier.height(12.dp))

            Column {
                Text(
                    text = "Your personal choices",
                    color = colorResource(MR.colors.h2_color),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = "Explore your maternity unit in your area, add appointments and develop your personal care plans:",
                    color = colorResource(MR.colors.p_color),
                    fontSize = 15.sp
                )
            }
//
//
            Spacer(modifier = Modifier.height(17.dp))

            // Section 1
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                val choice1 = Choice("Maternity Unit", MR.images.hospital)
                val choice2 = Choice("Appointments", MR.images.calendar)
                val choice3 =
                    Choice("Personalised care and support plans", MR.images.id_card)
                val items = listOf(choice1, choice2, choice3)
                for (i in 0..2) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .height(130.dp),
                        colors = CardDefaults.cardColors(containerColor = colorResource(MR.colors.primaryColor))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    interactionSource = MutableInteractionSource(),

                                    indication = rememberRipple(color = Color.White)

                                ) {
                                    if (i == 0) navigator.push(MaternityScreen())
                                }
                                .padding(
                                    start = 10.dp,
                                    top = 15.dp,
                                    end = 10.dp,
                                    bottom = 0.dp
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = items[i].title,
                                color = Color.White,
                                fontSize = 12.sp,
                                lineHeight = 15.sp,
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                painter = painterResource(items[i].image),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(68.dp)
                            )
                        }

                    }


                    // Prevent extra spacing on last item
                    if (i < 2) Spacer(modifier = Modifier.width(10.dp))

                }


            }


            // Section 2

            val choice4 = Choice("Your pregnancy", MR.images.your_pregnancy)
            val choice5 =
                Choice("Getting ready for birth", MR.images.getting_ready_for_birth)
            val choice6 = Choice("Labour and birth", MR.images.labour_birth)
            val choice7 = Choice("After your baby is born", MR.images.after_baby_is_born)

            val items1 = listOf(choice4, choice5)
            val items2 = listOf(choice6, choice7)


            Column(modifier = Modifier.padding(top = 10.dp)) {
                Text(
                    text = "Helpful topics: NHS approved",
                    color = colorResource(MR.colors.h2_color),
                    fontSize = 17.5.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(18.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    for (i in 0..1) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .height(100.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = colorResource(
                                    MR.colors.primaryColor
                                )
                            )
                        ) {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    modifier = Modifier.size(40.dp),
                                    painter = painterResource(items1[i].image),
                                    contentDescription = null,
                                    tint = Color.White,
                                )

                                Text(
                                    text = items1[i].title,
                                    color = Color.White,
                                    fontSize = 13.sp,
                                    lineHeight = 15.sp
                                )
                            }

                        }

                        // Prevent extra spacing on last item
                        if (i < 1) Spacer(modifier = Modifier.width(10.dp))
                    }


                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Row {
                for (i in 0..1) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .height(100.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(
                                MR.colors.primaryColor
                            )
                        )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(40.dp),
                                painter = painterResource(items2[i].image),
                                contentDescription = null,
                                tint = Color.White,
                            )

                            Text(
                                text = items2[i].title,
                                color = Color.White,
                                fontSize = 13.sp,
                                lineHeight = 15.sp
                            )
                        }
                    }

                    // Prevent extra spacing on last item
                    if (i < 1) Spacer(modifier = Modifier.width(10.dp))
                }
            }


            Spacer(modifier = Modifier.height(15.dp))


            // Section 3

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Your feedback",
                    color = colorResource(MR.colors.h2_color),
                    fontSize = 17.5.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "Please tell us what we could improve.",
                    color = colorResource(MR.colors.p_color),
                    fontSize = 15.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    modifier = Modifier.fillMaxWidth().height(47.dp),
                    onClick = {},
                    contentPadding = PaddingValues(horizontal = 23.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(MR.colors.primaryColor))
                ) {
                    Text(
                        text = "Provide feedback",
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
            }
        }
    }
}