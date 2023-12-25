package screens

import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.nodes.Document
import com.fleeksoft.ksoup.nodes.Element
import data.local.MainData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class ParserViewModel: ViewModel() {

    private val _state = MutableStateFlow(ParserViewState())
    val state =  _state.asStateFlow()


    private val httpClient: HttpClient = HttpClient() {
        install(ContentNegotiation) { json() }

    }

     suspend fun getImages(){
        val data = httpClient
            .get("https://nativecarebucket.s3.eu-west-2.amazonaws.com/main.json").call.body<Map<String,MainData>>()
         _state.value = _state.value.copy(mainData = data.get("Your pregnancy"))
    }

     fun parserHTML(){
        val html = "<h1 class ='h1.entry-title'>My Heading</h1><p class = 'main-text'>Mascuud is playing football</p>"

        val doc: Document = Ksoup.parse(html = html)
        val body = doc.body().children()

        val list = arrayListOf<Component>()
         body.forEach {
             when(it.tagName()){
                  "h1" -> list.add(parseTitle(it))
                  "p" -> list.add(parseParagraph(it))
             }
        }

        _state.value = _state.value.copy(article = Article(components = list))

    }

    private fun parseTitle(element: Element): Title{
        return Title(element.getElementsByClass("h1.entry-title")?.text() ?: "NOTHING")
    }

    private fun parseImage(element: Element): Image {
        return Image(link = element.selectFirst("img-header")?.text() ?: "NO IMAGE", null)
    }

    private fun parseParagraph(element: Element): Paragraph {
        return Paragraph(text = element.getElementsByClass("main-text")?.text() ?: "NO PARAGRAPH",null)
    }
}

data class ParserViewState(
    val article: Article? = null,
    val title: String = "",
    val mainData: MainData? = null
){

}
