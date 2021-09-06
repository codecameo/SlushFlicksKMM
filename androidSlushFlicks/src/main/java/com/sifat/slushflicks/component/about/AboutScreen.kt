package com.sifat.slushflicks.component.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import com.sifat.slushflicks.BuildConfig
import com.sifat.slushflicks.R

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
            .statusBarsPadding()
            .padding(horizontal = 24.dp)
    ) {

        Image(
            modifier = Modifier
                .padding(vertical = 24.dp)
                .size(128.dp)
                .fillMaxSize()
                .align(Alignment.CenterHorizontally),
            painter = painterResource(R.drawable.ic_launcher),
            contentDescription = "App Logo",
            contentScale = ContentScale.Crop
        )

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h6
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(4.dp),
            text = "v${BuildConfig.VERSION_NAME}",
            style = MaterialTheme.typography.body2
        )

        Column(
            modifier = Modifier
                .padding(top = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(id = R.string.developed_by),
                style = MaterialTheme.typography.subtitle1
            )

            Text(
                modifier = Modifier.padding(top = 8.dp, start = 12.dp),
                text = stringResource(id = R.string.email),
                style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onSecondary)
            )

            Text(
                modifier = Modifier.padding(top = 24.dp),
                text = stringResource(id = R.string.text_appreciation),
                style = MaterialTheme.typography.subtitle1
            )

            Text(
                modifier = Modifier.padding(top = 8.dp, start = 12.dp),
                text = stringResource(id = R.string.reference_list),
                style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onSecondary)
            )

            Text(
                modifier = Modifier.padding(top = 24.dp),
                text = stringResource(id = R.string.text_language),
                style = MaterialTheme.typography.subtitle1
            )

            Text(
                modifier = Modifier.padding(top = 8.dp, start = 12.dp),
                text = stringResource(id = R.string.kotlin),
                style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onSecondary)
            )

            Text(
                modifier = Modifier.padding(top = 24.dp),
                text = stringResource(id = R.string.text_architecture),
                style = MaterialTheme.typography.subtitle1
            )

            Text(
                modifier = Modifier.padding(top = 8.dp, start = 12.dp),
                text = stringResource(id = R.string.mvvm_mvi_clean_code),
                style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onSecondary)
            )

            Text(
                modifier = Modifier.padding(top = 24.dp),
                text = stringResource(id = R.string.text_libraries_and_components),
                style = MaterialTheme.typography.subtitle1
            )

            Text(
                modifier = Modifier.padding(top = 8.dp, start = 12.dp, bottom = 24.dp),
                text = stringResource(id = R.string.libraries_components_list),
                style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onSecondary)
            )
        }
    }
}
