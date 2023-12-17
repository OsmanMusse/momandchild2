import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.router.stack.pop
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.painterResource
import kotlinx.coroutines.launch
import org.example.momandbaby2.MR
import org.jetbrains.compose.resources.ExperimentalResourceApi
import screens.DueDateScreen
import screens.HomeScreen
import screens.MaternityScreen
import kotlin.math.max
import androidx.compose.ui.viewinterop.InteropView



data class Choice(
    val title: String,
    val image: ImageResource
){}

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
fun App() {

    val scope = rememberCoroutineScope()
    var value by remember { mutableStateOf(TextFieldValue("")) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    MaterialTheme {


     Navigator(screen = HomeScreen(PaddingValues(0.dp))) { navigator ->
         ModalNavigationDrawer(
             drawerContent = {
                 ModalDrawerSheet(
                     modifier = Modifier.fillMaxWidth(0.68f),
                     drawerShape = RectangleShape,
                 ) {
                     Column(
                         modifier = Modifier
                             .fillMaxWidth()
                             .background(color = colorResource(MR.colors.primaryColor))
                             .padding(start = 35.dp, end = 35.dp, top = 40.dp, bottom = 20.dp),
                         horizontalAlignment = Alignment.CenterHorizontally
                     ) {
                         Text(
                             text = "mum & baby",
                             fontFamily = fontFamilyResource(MR.fonts.LobsterTwo.bold),
                             fontWeight = FontWeight.Medium,
                             fontSize = 32.sp,
                             color = Color.White,
                             modifier = Modifier.clickable {
                                 scope.launch {
                                     drawerState.close()
                                 }
                                 navigator.popUntilRoot()
                             }
                         )

                         Spacer(modifier = Modifier.height(20.dp))

                         BasicTextField(
                             value = value,
                             onValueChange = { value = it },
                             textStyle = TextStyle(color = Color.White),
                             cursorBrush = SolidColor(Color(1,132,118)),
                             modifier = Modifier
                                 .fillMaxWidth()
                                 .focusRequester(focusRequester)
                                 .indicatorLine(
                                     enabled = true,
                                     isError = false,
                                     interactionSource = MutableInteractionSource(),
                                     colors = TextFieldDefaults.colors(
                                         focusedIndicatorColor = Color.Transparent,
                                         unfocusedIndicatorColor = Color.Transparent,
                                     ),
                                     focusedIndicatorLineThickness = 0.dp,  //to hide the indicator line
                                     unfocusedIndicatorLineThickness = 0.dp //to hide the indicator line
                                 )
                                 .height(40.dp),

                             interactionSource = MutableInteractionSource(),
                             enabled = true,
                             singleLine = true
                         ) {
                             TextFieldDefaults.DecorationBox(
                                 value = value.text,
                                 innerTextField = it,
                                 enabled = true,
                                 singleLine = true,
                                 shape = CircleShape,
                                 colors = TextFieldDefaults.colors(
                                     focusedContainerColor = Color.White.copy(alpha = 0.5f),
                                     unfocusedContainerColor = Color.White.copy(alpha = 0.5f),
                                     focusedIndicatorColor = Color.Transparent,
                                     unfocusedIndicatorColor = Color.Transparent,
                                 ),
                                 visualTransformation = VisualTransformation.None,
                                 interactionSource = MutableInteractionSource(),
                                 // keep horizontal paddings but change the vertical
                                 placeholder = {
                                     Text(
                                         text = "Search....",
                                         color = Color.White,
                                         fontSize = 13.sp,
                                     )
                                 },
                                 leadingIcon = {
                                     Icon(
                                         imageVector = Icons.Outlined.Search,
                                         contentDescription = null,
                                         modifier = Modifier.size(18.dp).offset(x = -2.dp),
                                         tint = Color.White
                                     )
                                 },

                                 trailingIcon = {
                                     IconButton(onClick = {
                                         value = TextFieldValue("")
                                         focusManager.clearFocus()
                                     }){
                                         Icon(
                                             imageVector = Icons.Outlined.Clear,
                                             contentDescription = null,
                                             modifier = Modifier.size(18.dp).offset(x = 5.dp),
                                             tint = Color.White
                                         )
                                     }

                                 },
                                 contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(
                                     top = 0.dp,
                                     start = 0.dp,
                                     bottom = 0.dp,
                                 ),
                             )
                         }

                     }

                     Column(
                         modifier = Modifier
                             .background(Color.White)
                             .verticalScrollWithScrollbar(
                                 state = rememberScrollState(),
                                 scrollbarConfig = ScrollBarConfig(padding = PaddingValues(4.dp, 4.dp, 0.5.dp, 4.dp))
                             )
                     ) {
                         NavigationDrawerItem(
                             icon = {
                                 Icon(
                                     painter = painterResource(MR.images.hospital),
                                     contentDescription = null,
                                     tint = colorResource(MR.colors.primaryColor),
                                     modifier = Modifier.size(34.dp)
                                 )
                             },
                             label = {
                                 Text("Maternity units", fontSize = 14.sp, color = colorResource(MR.colors.primaryColor))
                             },
                             onClick = {},
                             colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.White),
                             selected = false
                         )


                         NavigationDrawerItem(
                             icon = {
                                 Icon(
                                     painter = painterResource(MR.images.id_card),
                                     contentDescription = null,
                                     tint = colorResource(MR.colors.primaryColor),
                                     modifier = Modifier.size(34.dp)
                                 )
                             },
                             label = {
                                 Text("Personalised care and support plans", fontSize = 14.sp, color = colorResource(
                                     MR.colors.primaryColor)
                                 )
                             },
                             onClick = {},
                             colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.White),
                             selected = false
                         )
                         NavigationDrawerItem(
                             icon = {
                                 Icon(
                                     painter = painterResource(MR.images.calendar),
                                     contentDescription = null,
                                     tint = colorResource(MR.colors.primaryColor),
                                     modifier = Modifier.size(40.dp)
                                 )
                             },
                             label = {
                                 Text("Appointments", fontSize = 14.sp, color = colorResource(MR.colors.primaryColor))
                             },
                             onClick = {},
                             colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.White),
                             selected = false
                         )

                         NavigationDrawerItem(
                             icon = {
                                 Icon(
                                     painterResource(MR.images.your_pregnancy),
                                     contentDescription = null,
                                     tint = colorResource(MR.colors.primaryColor),
                                     modifier = Modifier.size(40.dp)
                                 )
                             },
                             label = {
                                 Text("Your Pregnancy", fontSize = 14.sp, color = colorResource(MR.colors.primaryColor))
                             },
                             onClick = {},
                             colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.White),
                             selected = false
                         )

                         NavigationDrawerItem(
                             icon = {
                                 Icon(
                                     painterResource(MR.images.getting_ready_for_birth),
                                     contentDescription = null,
                                     tint = colorResource(MR.colors.primaryColor),
                                     modifier = Modifier.size(40.dp)
                                 )
                             },
                             label = {
                                 Text("Getting ready for birth", fontSize = 14.sp, color = colorResource(
                                     MR.colors.primaryColor)
                                 )
                             },
                             onClick = {},
                             colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.White),
                             selected = false
                         )

                         NavigationDrawerItem(
                             icon = {
                                 Icon(
                                     painterResource(MR.images.labour_birth),
                                     contentDescription = null,
                                     tint = colorResource(MR.colors.primaryColor),
                                     modifier = Modifier.size(40.dp)
                                 )
                             },
                             label = {
                                 Text("Labour and birth", fontSize = 14.sp, color = colorResource(MR.colors.primaryColor))
                             },
                             onClick = {},
                             colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.White),
                             selected = false
                         )

                         NavigationDrawerItem(
                             icon = {
                                 Icon(
                                     painterResource(MR.images.after_baby_is_born),
                                     contentDescription = null,
                                     tint = colorResource(MR.colors.primaryColor),
                                     modifier = Modifier.size(40.dp)
                                 )
                             },
                             label = {
                                 Text("After your baby is born", fontSize = 14.sp, color = colorResource(
                                     MR.colors.primaryColor)
                                 )
                             },
                             shape = RoundedCornerShape(7.dp),
                             onClick = {},
                             colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.White),
                             selected = false
                         )

                         Column(
                             horizontalAlignment = Alignment.Start,
                             modifier = Modifier.padding(start = 0.dp)
                         ){
                             TextButton(
                                 modifier = Modifier.fillMaxWidth(),
                                 onClick = {},
                                 shape = RectangleShape
                             ){
                                 Text(
                                     text = "Your due date",
                                     color = colorResource(MR.colors.primaryColor),
                                     textAlign = TextAlign.Start,
                                     fontWeight = FontWeight.Medium,
                                     modifier = Modifier.fillMaxWidth()
                                 )
                             }

                             TextButton(
                                 modifier = Modifier.fillMaxWidth(),
                                 onClick = {},
                                 shape = RectangleShape
                             ) {
                                 Text(
                                     text = "Get involved",
                                     color = colorResource(MR.colors.primaryColor),
                                     textAlign = TextAlign.Start,
                                     fontWeight = FontWeight.Medium,
                                     modifier = Modifier.fillMaxWidth()
                                 )
                             }

                             TextButton(
                                 modifier = Modifier.fillMaxWidth(),
                                 onClick = {},
                                 shape = RectangleShape
                             ) {
                                 Text(
                                     text = "Feedback",
                                     color = colorResource(MR.colors.primaryColor),
                                     textAlign = TextAlign.Start,
                                     fontWeight = FontWeight.Medium,
                                     modifier = Modifier.fillMaxWidth()
                                 )
                             }

                             TextButton(
                                 modifier = Modifier.fillMaxWidth(),
                                 onClick = {},
                                 shape = RectangleShape
                             ) {
                                 Text(
                                     text = "Donations",
                                     color = colorResource(MR.colors.primaryColor),
                                     textAlign = TextAlign.Start,
                                     fontWeight = FontWeight.Medium,
                                     modifier = Modifier.fillMaxWidth()
                                 )
                             }

                             TextButton(
                                 modifier = Modifier.fillMaxWidth(),
                                 onClick = {},
                                 shape = RectangleShape
                             ) {
                                 Text(
                                     text = "Backup",
                                     color = Color.Gray,
                                     textAlign = TextAlign.Start,
                                     fontWeight = FontWeight.Normal,
                                     modifier = Modifier.fillMaxWidth()
                                 )
                             }

                             TextButton(
                                 modifier = Modifier.fillMaxWidth(),
                                 onClick = {},
                                 shape = RectangleShape
                             ) {

                                 Text(
                                     text = "About this app",
                                     color = Color.Gray,
                                     textAlign = TextAlign.Start,
                                     fontWeight = FontWeight.Normal,
                                     modifier = Modifier.fillMaxWidth()
                                 )
                             }

                             TextButton(
                                 modifier = Modifier.fillMaxWidth(),
                                 onClick = {},
                                 shape = RectangleShape
                             ) {

                                 Text(
                                     text = "Contact us",
                                     color = Color.Gray,
                                     textAlign = TextAlign.Start,
                                     fontWeight = FontWeight.Normal,
                                     modifier = Modifier.fillMaxWidth()
                                 )
                             }
                         }


                     }


                 }
             },
             drawerState = drawerState

         ){
             Scaffold(
                 topBar = {
                     TopAppBar(
                         title = {
                             Text(
                                 text = when(navigator.lastItem){
                                     is HomeScreen -> "Home"
                                     is MaternityScreen -> "Maternity units"
                                     is DueDateScreen -> "Your due date"
                                     else -> "Home"
                                 }, color = Color.White, textAlign = TextAlign.Center, fontSize = 19.sp, fontWeight = FontWeight.SemiBold,
                                 modifier = Modifier.fillMaxWidth()
                             )
                         },
                         actions = {
                             Text(
                                 text = "Menu",
                                 color = Color.White,
                                 fontSize = 14.sp,
                                 modifier = Modifier.offset(y = 2.dp).clickable {
                                     scope.launch {
                                         drawerState.open()
                                     }

                                 }
                             )
                         },
                         colors = TopAppBarDefaults.topAppBarColors(containerColor = colorResource(MR.colors.primaryColor))
                     )
                 }
             ) {
                 CurrentScreen()
             }
         }
     }

    }
}

@Composable
fun Modifier.simpleVerticalScrollbar(
    state: LazyListState,
    width: Dp = 8.dp
): Modifier {
    val targetAlpha = if (state.isScrollInProgress) 1f else 0f
    val duration = if (state.isScrollInProgress) 150 else 500

    val alpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(durationMillis = duration)
    )

    return drawWithContent {
        drawContent()

        val firstVisibleElementIndex = state.layoutInfo.visibleItemsInfo.firstOrNull()?.index
        val needDrawScrollbar = state.isScrollInProgress || alpha > 0.0f

        // Draw scrollbar if scrolling or if the animation is still running and lazy column has content
        if (needDrawScrollbar && firstVisibleElementIndex != null) {
            val elementHeight = this.size.height / state.layoutInfo.totalItemsCount
            val scrollbarOffsetY = firstVisibleElementIndex * elementHeight
            val scrollbarHeight = state.layoutInfo.visibleItemsInfo.size * elementHeight

            drawRect(
                color = Color.Red,
                topLeft = Offset(this.size.width - width.toPx(), scrollbarOffsetY),
                size = Size(width.toPx(), scrollbarHeight),
                alpha = alpha
            )
        }
    }
}

@Composable
fun Modifier.scrollbar(
    state: ScrollState,
    direction: Orientation,
    indicatorThickness: Dp = 8.dp,
    indicatorColor: Color = Color.DarkGray,
    alpha: Float = if (state.isScrollInProgress) 0.8f else 0f,
    alphaAnimationSpec: AnimationSpec<Float> = tween(
        delayMillis = if (state.isScrollInProgress) 0 else 1500,
        durationMillis = if (state.isScrollInProgress) 150 else 500
    ),
    padding: PaddingValues = PaddingValues(all = 0.dp)
): Modifier = composed {
    val scrollbarAlpha by animateFloatAsState(
        targetValue = alpha,
        animationSpec = alphaAnimationSpec
    )

    drawWithContent {
        drawContent()

        val showScrollBar = state.isScrollInProgress || scrollbarAlpha > 0.0f

        // Draw scrollbar only if currently scrolling or if scroll animation is ongoing.
        if (showScrollBar) {
            val (topPadding, bottomPadding, startPadding, endPadding) = listOf(
                padding.calculateTopPadding().toPx(), padding.calculateBottomPadding().toPx(),
                padding.calculateStartPadding(layoutDirection).toPx(),
                padding.calculateEndPadding(layoutDirection).toPx()
            )
            val contentOffset = state.value
            val viewPortLength = if (direction == Orientation.Vertical)
                size.height else size.width
            val viewPortCrossAxisLength = if (direction == Orientation.Vertical)
                size.width else size.height
            val contentLength = max(viewPortLength + state.maxValue, 0.001f)  // To prevent divide by zero error
            val indicatorLength = ((viewPortLength / contentLength) * viewPortLength) - (
                    if (direction == Orientation.Vertical) topPadding + bottomPadding
                    else startPadding + endPadding
                    )
            val indicatorThicknessPx = indicatorThickness.toPx()

            val scrollOffsetViewPort = viewPortLength * contentOffset / contentLength

            val scrollbarSizeWithoutInsets = if (direction == Orientation.Vertical)
                Size(indicatorThicknessPx, indicatorLength)
            else Size(indicatorLength, indicatorThicknessPx)

            val scrollbarPositionWithoutInsets = if (direction == Orientation.Vertical)
                Offset(
                    x = if (layoutDirection == LayoutDirection.Ltr)
                        viewPortCrossAxisLength - indicatorThicknessPx - endPadding
                    else startPadding,
                    y = scrollOffsetViewPort + topPadding
                )
            else
                Offset(
                    x = if (layoutDirection == LayoutDirection.Ltr)
                        scrollOffsetViewPort + startPadding
                    else viewPortLength - scrollOffsetViewPort - indicatorLength - endPadding,
                    y = viewPortCrossAxisLength - indicatorThicknessPx - bottomPadding
                )

            drawRoundRect(
                color = indicatorColor,
                topLeft = scrollbarPositionWithoutInsets,
                size = scrollbarSizeWithoutInsets,
                alpha = scrollbarAlpha
            )
        }
    }
}

data class ScrollBarConfig(
    val indicatorThickness: Dp = 5.dp,
    val indicatorColor: Color = Color.DarkGray,
    val alpha: Float? = null,
    val alphaAnimationSpec: AnimationSpec<Float>? = null,
    val padding: PaddingValues = PaddingValues(all = 0.dp)
)

@Composable
fun Modifier.verticalScrollWithScrollbar(
    state: ScrollState,
    enabled: Boolean = true,
    flingBehavior: FlingBehavior? = null,
    reverseScrolling: Boolean = false,
    scrollbarConfig: ScrollBarConfig = ScrollBarConfig()
) = this
    .scrollbar(
        state, Orientation.Vertical,
        indicatorThickness = scrollbarConfig.indicatorThickness,
        indicatorColor = scrollbarConfig.indicatorColor,
        alpha = scrollbarConfig.alpha ?: if (state.isScrollInProgress) 0.3f else 0f,
        alphaAnimationSpec = scrollbarConfig.alphaAnimationSpec ?: tween(
            delayMillis = if (state.isScrollInProgress) 0 else 1500,
            durationMillis = if (state.isScrollInProgress) 150 else 500
        ),
        padding = scrollbarConfig.padding
    )
    .verticalScroll(state, enabled, flingBehavior, reverseScrolling)
