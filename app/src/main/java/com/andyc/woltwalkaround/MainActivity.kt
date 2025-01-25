package com.andyc.woltwalkaround

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.andyc.core.presentation.designsystem.WoltWalkaroundTheme
import com.andyc.walkaround.presentation.venue_discovery.VenueDiscoveryScreenRoot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WoltWalkaroundTheme {
                VenueDiscoveryScreenRoot()
            }
        }
    }
}