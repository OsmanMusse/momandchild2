package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import components.DefaultBtn
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import org.example.momandbaby2.MR

class DueDateScreen: Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        var switchChecked by remember { mutableStateOf(false) }

        val mutableInteractionSource = remember{ MutableInteractionSource() }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = 80.dp)
        ){

            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp)
            ){
                Text(
                    text = "Your Pregnancy",
                    color = colorResource(MR.colors.h2_color),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Are you pregnant?",
                        color = colorResource(MR.colors.blackish_color),
                        fontSize = 16.5.sp,
                        modifier = Modifier.weight(1f)
                    )

                    Switch(
                        checked = switchChecked,
                        onCheckedChange = { switchChecked = it},
                        colors = SwitchDefaults.colors(

                            checkedTrackColor = colorResource(MR.colors.primaryColor),
                            checkedThumbColor = Color.White,
                            uncheckedThumbColor = colorResource(MR.colors.thumbColor),
                            uncheckedTrackColor = colorResource(MR.colors.trackColor)
                        )
                    )
                }

                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    color = colorResource(MR.colors.thumbColor)
                )

            }




            Spacer(modifier = Modifier.height(30.dp))


            if(switchChecked){
                // Hideable Section
                Column {
                    Text(
                        text = "Set my due date:",
                        color = colorResource(MR.colors.blackish_color),
                        fontSize = 16.5.sp,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )

                    Spacer(modifier = Modifier.height(15.dp))
                    Button(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp).height(50.dp),
                        onClick = {},
                        contentPadding = PaddingValues(horizontal = 0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(MR.colors.primaryColor))
                    ) {
                        Text(
                            text = "Set date",
                            modifier = Modifier.fillMaxWidth().weight(1f),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                interactionSource = mutableInteractionSource,
                                indication = null,
                                onClick = { }
                            )
                            .padding(horizontal = 15.dp)
                            .border(
                                width = 1.25.dp,
                                color = Color.Black.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(corner = CornerSize(3.5.dp))
                            )
                            .indication(interactionSource = mutableInteractionSource, indication = null),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "Add your due date",
                            color = colorResource(MR.colors.brown_color),
                            fontWeight = FontWeight.Normal,
                            fontSize = 19.sp,
                            modifier = Modifier.padding(13.dp),
                        )
                    }


                }
            }


            Column(
                modifier = Modifier.weight(1f,)
            ) {}

            Button(
                modifier = Modifier.fillMaxWidth().height(47.dp),
                onClick = {
                   navigator.popUntilRoot()
                },
                shape = RectangleShape,
                contentPadding = PaddingValues(horizontal = 13.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(MR.colors.btn_green_color))
            ) {
                Text(
                    text = "Continue".uppercase(),
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
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
