package com.hstechapps.livesatellite.gps.navigation.applovinadsconfiguration.adsconfig.callbacks

import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxError
import com.applovin.mediation.nativeAds.MaxNativeAdView

interface LovinNativeCallBack {
    fun onNativeAdLoaded(nativeAdView: MaxNativeAdView?, ad: MaxAd)
    fun onNativeAdLoadFailed(adUnitId: String, error: MaxError)
    fun onNativeAdClicked(ad: MaxAd)
}