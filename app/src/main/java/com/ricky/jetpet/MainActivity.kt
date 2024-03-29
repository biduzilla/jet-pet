package com.ricky.jetpet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.lifecycleScope
import com.ricky.jetpet.data.local.DataStoreUtil
import com.ricky.jetpet.data.network.token.AccessTokenRepository
import com.ricky.jetpet.navigation.AppNav
import com.ricky.jetpet.ui.theme.JetPetTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dataStoreUtil: DataStoreUtil

    @Inject
    lateinit var tokenRepository: AccessTokenRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataStoreUtil = DataStoreUtil(applicationContext)
        var darkMode by mutableStateOf(false)

        lifecycleScope.launch {
            dataStoreUtil.getTheme().collect {
                darkMode = it
            }
        }

        setContent {
            JetPetTheme(darkTheme = darkMode) {
                AppNav()
            }
        }
    }

}

