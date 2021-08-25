package com.sifat.slushflicks

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.sifat.slushflicks.data.state.DataState
import com.sifat.slushflicks.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    val movieRepo by inject<MovieDetailsRepository>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(text = greet(), modifier = Modifier.align(Alignment.Center))
                }
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            movieRepo.getMovieDetails(1001).let {
                when (it) {
                    is DataState.Success -> println("Movie ${it.data?.title}")
                    is DataState.Error -> println("Movie Error ${it.errorMessage} ${it.statusCode}")
                }
            }
        }
    }
}
