package com.hstechapps.livesatellite.gps.navigation.applovinadsconfiguration.adsconfig.callbacks

import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxError

interface LovinBannerCallBack {
    fun onAdLoaded(maxAd: MaxAd?)
    fun onAdLoadFailed(adUnitId: String?, error: MaxError?)
    fun onAdDisplayFailed(ad: MaxAd?, error: MaxError?)
    fun onAdClicked(maxAd: MaxAd?)
    fun onAdExpanded(maxAd: MaxAd?)
    fun onAdCollapsed(maxAd: MaxAd?)
    fun onAdDisplayed(maxAd: MaxAd?)
    fun onAdHidden(maxAd: MaxAd?)

}