package com.sifat.slushflicks.di

import co.touchlab.kermit.Logger
import org.koin.core.module.Module

expect fun platformModule(): Module

expect fun getLogger(): Logger
