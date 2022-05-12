package com.hstechapps.livesatellite.gps.navigation.applovinadsconfiguration.adsconfig.callbacks

import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxError

interface LovinInterstitialOnCallBack {
    fun onAdLoadFailed(adUnitId: String?, error: MaxError?)
    fun onAdLoaded(maxAd: MaxAd?)
    fun onAdDisplayFailed(ad: MaxAd?, error: MaxError?)
    fun onAdDisplayed(maxAd: MaxAd?)
    fun onAdClicked(maxAd: MaxAd?)
    fun onAdHidden(maxAd: MaxAd?)
}