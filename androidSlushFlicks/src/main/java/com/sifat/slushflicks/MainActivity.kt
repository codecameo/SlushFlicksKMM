package com.sifat.slushflicks

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.view.WindowCompat
import coil.annotation.ExperimentalCoilApi
import com.sifat.slushflicks.component.SlushFlicksApp
import kotlinx.coroutines.FlowPreview

@ExperimentalCoilApi
@FlowPreview
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            SlushFlicksApp()
        }
    }
}
