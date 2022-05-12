package com.hstechapps.livesatellite.gps.navigation.applovinadsconfiguration.adsconfig


import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdViewAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxAdView
import com.applovin.mediation.nativeAds.MaxNativeAdListener
import com.applovin.mediation.nativeAds.MaxNativeAdLoader
import com.applovin.mediation.nativeAds.MaxNativeAdView
import com.hstechapps.livesatellite.gps.navigation.applovinadsconfiguration.GeneralUtils.AD_TAG
import com.hstechapps.livesatellite.gps.navigation.applovinadsconfiguration.GeneralUtils.isInternetConnected
import com.hstechapps.livesatellite.gps.navigation.applovinadsconfiguration.R
import com.hstechapps.livesatellite.gps.navigation.applovinadsconfiguration.adsconfig.callbacks.LovinBannerCallBack
import com.hstechapps.livesatellite.gps.navigation.applovinadsconfiguration.adsconfig.callbacks.LovinNativeCallBack


class LovinBannerAds(activity: Activity) {

    private var adViewBanner: MaxAdView? = null
    private var nativeAdLoader: MaxNativeAdLoader? = null
    private var nativeAd: MaxAd? = null
    private val mActivity: Activity = activity
    private var lovinBannerCallBack: LovinBannerCallBack? = null
    private var lovinNativeCallBack: LovinNativeCallBack? = null

    fun loadBannerAds(
        adsContainerLayout: LinearLayout,
        adsHolder: LinearLayout,
        loadingFrameLayout: FrameLayout,
        lovinBannerIds: String,
        isRemoteConfigActive: Boolean,
        isAppPurchased: Boolean,
        mListener: LovinBannerCallBack
    ) {
        lovinBannerCallBack = mListener
        if (isInternetConnected(mActivity)){
            if (!isAppPurchased && isRemoteConfigActive) {
                adViewBanner =  MaxAdView(lovinBannerIds, mActivity)
                adViewBanner?.setListener(object : MaxAdViewAdListener {
                    override fun onAdLoaded(ad: MaxAd?) {
                        loadingFrameLayout.visibility = View.GONE
                        Log.d(AD_TAG, "Lovin Banner onAdLoaded")
                        lovinBannerCallBack?.onAdLoaded(ad)
                    }

                    override fun onAdDisplayed(ad: MaxAd?) {
                        Log.d(AD_TAG, "Lovin Banner onAdDisplayed")
                        lovinBannerCallBack?.onAdDisplayed(ad)
                    }

                    override fun onAdHidden(ad: MaxAd?) {
                        Log.d(AD_TAG, "Lovin Banner onAdHidden")
                        lovinBannerCallBack?.onAdHidden(ad)
                    }

                    override fun onAdClicked(ad: MaxAd?) {
                        Log.d(AD_TAG, "Lovin Banner onAdClicked")
                        lovinBannerCallBack?.onAdClicked(ad)
                    }

                    override fun onAdLoadFailed(adUnitId: String?, error: MaxError?) {
                        Log.e(AD_TAG, "Lovin Banner onError: ${error?.message}")
                        adsContainerLayout.visibility = View.GONE
                        lovinBannerCallBack?.onAdLoadFailed(adUnitId,error)
                    }

                    override fun onAdDisplayFailed(ad: MaxAd?, error: MaxError?) {
                        Log.d(AD_TAG, "Lovin Banner onAdDisplayFailed")
                        lovinBannerCallBack?.onAdDisplayFailed(ad,error)
                    }

                    override fun onAdExpanded(ad: MaxAd?) {
                        Log.d(AD_TAG, "Lovin Banner onAdExpanded")
                        lovinBannerCallBack?.onAdExpanded(ad)
                    }

                    override fun onAdCollapsed(ad: MaxAd?) {
                        Log.d(AD_TAG, "Lovin Banner onAdCollapsed")
                        lovinBannerCallBack?.onAdCollapsed(ad)
                    }

                })

                // Stretch to the width of the screen for banners to be fully functional
                val width = ViewGroup.LayoutParams.MATCH_PARENT

                // Banner height on phones and tablets is 50 and 90, respectively
                val heightPx = mActivity.resources.getDimensionPixelSize(R.dimen.banner_height)

                adViewBanner?.layoutParams = FrameLayout.LayoutParams(width, heightPx)

                // Set background or background color for banners to be fully functional
                adViewBanner?.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.white))

                adsHolder.addView(adViewBanner)

                // Load the ad
                adViewBanner?.loadAd()

            } else {
                adsContainerLayout.visibility = View.GONE
            }
        }else{
            adsContainerLayout.visibility = View.GONE
        }


    }

    fun destroyBannerAds() {
        adViewBanner?.destroy()
    }

    fun loadNative(
        adsContainerLayout: LinearLayout,
        nativeAdContainer: FrameLayout,
        loadingFrameLayout: FrameLayout,
        lovinNativeIds: String,
        isRemoteConfigActive: Boolean,
        isAppPurchased: Boolean,
        mListener: LovinNativeCallBack
    ) {
        lovinNativeCallBack = mListener

        if (isInternetConnected(mActivity)){
            if (!isAppPurchased && isRemoteConfigActive) {
                nativeAdLoader = MaxNativeAdLoader( lovinNativeIds, mActivity )
                nativeAdLoader?.setNativeAdListener(object : MaxNativeAdListener() {

                    override fun onNativeAdLoaded(nativeAdView: MaxNativeAdView?, ad: MaxAd){
                        // Clean up any pre-existing native ad to prevent memory leaks.
                        loadingFrameLayout.visibility = View.GONE
                        if ( nativeAd != null ) {
                            nativeAdLoader?.destroy( nativeAd )
                        }

                        // Save ad for cleanup.
                        nativeAd = ad

                        // Add ad view to view.
                        nativeAdContainer.removeAllViews()
                        nativeAdContainer.addView( nativeAdView )
                        lovinNativeCallBack?.onNativeAdLoaded(nativeAdView,ad)
                        Log.d(AD_TAG, "Lovin onNativeAdLoaded")
                    }

                    override fun onNativeAdLoadFailed(adUnitId: String, error: MaxError) {
                        // We recommend retrying with exponentially higher delays up to a maximum delay
                        adsContainerLayout.visibility = View.GONE
                        lovinNativeCallBack?.onNativeAdLoadFailed(adUnitId,error)
                        Log.e(AD_TAG, "Lovin onNativeAdLoadFailed")
                    }

                    override fun onNativeAdClicked(ad: MaxAd){
                        // Optional click callback
                        lovinNativeCallBack?.onNativeAdClicked(ad)
                        Log.d(AD_TAG, "Lovin onNativeAdClicked")
                    }
                })
                nativeAdLoader?.loadAd()

            } else {
                adsContainerLayout.visibility = View.GONE
            }
        }else{
            adsContainerLayout.visibility = View.GONE
        }

    }

    fun destroyNativeAds() {
        nativeAdLoader?.destroy()
    }

}