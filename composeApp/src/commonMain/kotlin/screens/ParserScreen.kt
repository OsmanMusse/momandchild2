package screens

import ScrollBarConfig
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.nodes.Document
import data.local.PreferencesKeys
import data.local.PreferencesKeys.IMAGE_PATH
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import dev.icerock.moko.resources.compose.colorResource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.ktor.client.engine.HttpClientEngine
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import org.example.momandbaby2.MR
import verticalScrollWithScrollbar
import kotlin.coroutines.resume



sealed class Component

data class Title(val text: String) : Component()

data class Header(val text: String) : Component()

data class Image(val link: String, val caption: String?) : Component()

data class GifImage(val link: String, val caption: String?) : Component()

data class Paragraph(val text: String, val title: String? = null) : Component()

data class Newsletter(val rawHtml: String) : Component()

data class Article(
    val url: String? = null,
    val components: List<Component>
)


class ParserScreen: Screen {


    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()
        val viewModel = getViewModel("Parser_View_Model", viewModelFactory { ParserViewModel() })
        val state by viewModel.state.collectAsState()

        scope.launch { viewModel.getImages() }
        Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScrollWithScrollbar(rememberScrollState()).padding(top = 100.dp)
            ) {

            Column(
                modifier = Modifier.padding(horizontal = 15.dp)
            ) {

                Text(
                    text = "Your pregnancy",
                    color = colorResource(MR.colors.h2_color),
                    fontSize = 22.5.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(end = 14.dp)
                )

                Spacer(modifier = Modifier.heightIn(20.dp))


                LazyColumn(modifier = Modifier.heightIn(max = 2000.dp)) {
                    items(items = state.mainData?.topics ?: emptyList()) { topic ->

                        Divider(
                            modifier = Modifier.fillMaxWidth(),
                            thickness = 1.5.dp,
                            color = colorResource(MR.colors.primaryColor)
                        )
                        Row(
                            modifier = Modifier.height(130.dp)
                                .padding(10.dp),

                            ) {
                            Text(
                                text = "${topic.title}",
                                modifier = Modifier.weight(1f)
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            KamelImage(
                                resource = asyncPainterResource(data = "${IMAGE_PATH}/fit-in/200x400/${topic.imageURL}"),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize().weight(1f)
                            )
                        }
                    }
                }
            }
        }

    }

    @Composable
    fun DrawUI(component: Component){
        when(component){
            is Title ->  Text(text = component.text , color = Color.Red,modifier = Modifier, maxLines = 10)
            is Paragraph -> Text(text = component.text, color = Color.Blue)
            else -> Unit
        }
    }



}