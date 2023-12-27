package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.screenModel.rememberNavigatorScreenModel
import data.local.MainData
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.fontFamilyResource
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.delay
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.momandbaby2.MR

data class SplashScreenViewState(
    val mainData: Map<String, MainData>? = null
){

}
class SplashScreenViewModel: ViewModel() {

    private val _state = MutableStateFlow(SplashScreenViewState())
    val state = _state.asStateFlow()

    private val httpClient: HttpClient = HttpClient() {
        install(ContentNegotiation) { json() }

    }

    suspend fun getAppData(){
        val data = httpClient
            .get("https://nativecarebucket.s3.eu-west-2.amazonaws.com/main.json").call.body<Map<String, MainData>>()
        _state.value = _state.value.copy(mainData = data)
    }
}

class SplashScreen: Screen {

    @OptIn(ExperimentalVoyagerApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val scope = rememberCoroutineScope()
        val viewModel = getViewModel(key = "SplashScreen_View_Model", viewModelFactory { SplashScreenViewModel() })
        val sharedViewModel = navigator.rememberNavigatorScreenModel { SharedViewModel() }
        val state by viewModel.state.collectAsState()

        scope.launch { viewModel.getAppData() }
        Column(
            modifier = Modifier
            .fillMaxSize()
            .background(colorResource(MR.colors.primaryColor)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "mum & baby",
                fontFamily = fontFamilyResource(MR.fonts.LobsterTwo.bold),
                fontSize = 40.sp,
                color = Color.White
            )
        }

        if(state.mainData != null){
            scope.launch {
                delay(1000)
                sharedViewModel.mainData = state.mainData!!
                navigator.push(HomeScreen())
            }

        }
    }

}