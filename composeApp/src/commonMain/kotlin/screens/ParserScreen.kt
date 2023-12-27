package screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import data.local.Topic
import open_link.Linker
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.painterResource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.example.momandbaby2.MR
import verticalScrollWithScrollbar

data class ParserScreen(
    val mainData: Topic
): Screen {


  @Composable
    override fun Content() {
      val viewModel = getViewModel("Parser_View_Model", viewModelFactory { ParserViewModel() })
      val state by viewModel.state.collectAsState()
      viewModel.parserHTML(mainData.contents?: "")
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScrollWithScrollbar(rememberScrollState())
                .padding(top = 100.dp)
        ){
            Column(
                modifier = Modifier
            ) {
                state.article?.components?.forEach {
                    DrawUI(it)
                }
            }

        }
    }


    @Composable
    fun DrawUI(component: Component){
        when(component){
            is Title ->  DrawTitle(component)
            is SubTitle -> DrawSubTitle(component)
            is Paragraph -> DrawParagraph(component)
            is Image -> DrawImage(component)
            is BulletList -> DrawLi(component)
            is RelatedLink -> DrawRelatedLinkSection(component)
            else -> Unit
        }
    }

    @Composable
    private fun DrawTitle(title: Title){
        Text(
            text = "${title.text}",
            color = colorResource(MR.colors.h2_color),
            fontSize = 22.5.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 22.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))
    }

    @Composable
    private fun DrawSubTitle(text: SubTitle){
        Text(
            text = "${text.text}",
            color = colorResource(MR.colors.h2_color),
            fontSize = 17.5.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 18.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))
    }

    @Composable
    private fun DrawLi(li: BulletList){
                val mainColor = colorResource(MR.colors.primaryColor)
                Column(modifier = Modifier.padding(horizontal = 18.dp)) {
                    Row(Modifier.padding(8.dp),verticalAlignment = Alignment.Top) {
                        Canvas(modifier = Modifier.padding(start = 8.dp,end = 8.dp).size(8.dp)){
                            drawCircle(color = mainColor)
                        }

                        Spacer(modifier = Modifier.width(18.dp))

                        Text(
                            text = li.text,
                            fontSize = 14.85.sp,
                            color = colorResource(MR.colors.p_dark_color),
                            modifier = Modifier.offset(y = (-4).dp)
                        )
                    }
                }

        Spacer(modifier = Modifier.height(8.dp))

    }

    @Composable
    private fun DrawParagraph(paragraph: Paragraph){
        Text(
            text = "${paragraph.text}",
            color = colorResource(MR.colors.p_dark_color),
            fontSize = 14.85.sp,
            modifier = Modifier.padding(horizontal = 18.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))
    }

    @Composable
    private fun DrawImage(image: Image){

        KamelImage(
            resource = asyncPainterResource(data = image.link),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 18.dp).height(210.dp)
        )

        Spacer(modifier = Modifier.height(13.dp))
    }


    @Composable
    private fun DrawRelatedLinkSection(relatedLink: RelatedLink){
        if(relatedLink.isElementFirst) Spacer(modifier = Modifier.height(35.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color(238, 247, 246))
        ) {
            Column(modifier = Modifier.fillMaxWidth()) { }
            if(relatedLink.isElementFirst){
                Text(
                    text = "Related links",
                    color = Color(78, 169, 150),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                )
            }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            Linker.openLink(relatedLink.link)
                        }
                        .padding(24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(color = colorResource(MR.colors.dark_light_color)),
                        contentAlignment = Alignment.Center
                    ){
                        Icon(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(MR.images.worldwide_icon),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    }

                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = "${relatedLink.text}",
                        modifier = Modifier.fillMaxWidth().weight(1f),
                        color = Color(60, 159, 138),
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.width(20.dp))


                    Icon(
                        modifier = Modifier.size(25.dp),
                        painter = painterResource(MR.images.arrow_right),
                        contentDescription = null,
                        tint = colorResource(MR.colors.trackColor)
                    )

                }

                Divider(modifier = Modifier.fillMaxWidth(), color = Color.White)

        }
    }

}