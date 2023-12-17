package switch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UISwitch
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.getUIColor
import kotlinx.cinterop.ObjCAction
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGRectMake
import platform.UIKit.UIColor
import org.example.momandbaby2.MR
import platform.Foundation.NSSelectorFromString
import platform.UIKit.UIControlEventValueChanged
import platform.UIKit.UITextField
import platform.objc.sel_registerName
import kotlinx.cinterop.BetaInteropApi

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun CustomSwitch(
    shouldPreSelect: Boolean,
    onValueChange: (Boolean) -> Unit
) {

    val customSwitch = remember {
        UISwitch()
    }

    val customSwitchManager = remember {
        CustomSwitchManager(
            customSwitch,
            shouldPreSelect = shouldPreSelect,
            onSelectChange = {
            onValueChange(it)
        })
    }


    UIKitView(
            factory = {
                customSwitch
            },
    modifier = Modifier
        .width(customSwitchManager.customSwitchWidth.value.dp)
        .height(customSwitchManager.customSwitchHeight.value.dp)
        .background(Color.Blue)
    )
}