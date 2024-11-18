package com.abhilash.newshopping.ui.theme.testing

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import com.abhilash.newshopping.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@SuppressLint("VisibleForTests")
@Composable
fun BannerAdView(
    isTest: Boolean = true,
    banner: AdSize = AdSize.BANNER
) {
    val unitId = if (isTest) stringResource(R.string.ad_mob_test_banner_id)
    else stringResource(R.string.ad_mob_banner_id)
    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = { context ->
                AdView(context).apply {
                    setAdSize(banner)
                    adUnitId = unitId
                    loadAd(AdRequest.Builder().build())
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        )
    }
}