package com.hstechapps.livesatellite.gps.navigation.applovinadsconfiguration.adsconfig

import android.app.Activity
import android.util.Log
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxInterstitialAd
import com.hstechapps.livesatellite.gps.navigation.applovinadsconfiguration.GeneralUtils.AD_TAG
import com.hstechapps.livesatellite.gps.navigation.applovinadsconfiguration.GeneralUtils.isInternetConnected
import com.hstechapps.livesatellite.gps.navigation.applovinadsconfiguration.adsconfig.callbacks.LovinInterstitialOnCallBack


class LovinInterstitialAds(activity: Activity) {
    private var mInterstitialAd: MaxInterstitialAd? = null
    private val mActivity: Activity = activity
    private var lovinInterstitialOnCallBack: LovinInterstitialOnCallBack? = null
    var isLoadingAd = false

    // load and show strategy
    fun loadAndShowInterstitialAd(
        lovinInterstitialIds: String,
        isRemoteConfigActive: Boolean,
        isAppPurchased: Boolean,
        mListener: LovinInterstitialOnCallBack
    ) {
        lovinInterstitialOnCallBack = mListener
        if (isInternetConnected(mActivity) && !isAppPurchased && isRemoteConfigActive) {
            if (mInterstitialAd == null && !isLoadingAd) {
                isLoadingAd = true
                mInterstitialAd = MaxInterstitialAd( lovinInterstitialIds, mActivity )
                mInterstitialAd?.let{
                    it.setListener( object : MaxAdListener{
                        override fun onAdLoaded(ad: MaxAd?) {
                            isLoadingAd = false
                            lovinInterstitialOnCallBack?.onAdLoaded(ad)
                            Log.d(AD_TAG, "Lovin Interstitial ad is loaded and ready to be displayed!")
                        }

                        override fun onAdDisplayed(ad: MaxAd?) {
                            lovinInterstitialOnCallBack?.onAdDisplayed(ad)
                            Log.d(AD_TAG, "Lovin Interstitial ad is displayed!")
                        }

                        override fun onAdHidden(ad: MaxAd?) {
                            isLoadingAd = false
                            lovinInterstitialOnCallBack?.onAdHidden(ad)
                            mInterstitialAd = null
                            Log.d(AD_TAG, "Lovin Interstitial ad dismissed!")
                        }

                        override fun onAdClicked(ad: MaxAd?) {
                            lovinInterstitialOnCallBack?.onAdClicked(ad)
                            Log.d(AD_TAG, "Lovin Interstitial ad Clicked!")
                        }

                        override fun onAdLoadFailed(adUnitId: String?, error: MaxError?) {
                            isLoadingAd = false
                            lovinInterstitialOnCallBack?.onAdLoadFailed(adUnitId,error)
                            mInterstitialAd = null
                            Log.e(AD_TAG, "Lovin Interstitial ad failed to load: " + error?.message)
                        }

                        override fun onAdDisplayFailed(ad: MaxAd?, error: MaxError?) {
                            isLoadingAd = false
                            lovinInterstitialOnCallBack?.onAdDisplayFailed(ad,error)
                            mInterstitialAd = null
                            Log.e(AD_TAG, "Lovin Interstitial ad display Failed!")
                        }

                    })
                    it.loadAd()

                }

            }
        }

    }

    // load show and load strategy
    fun loadShowAndLoadInterstitialAd(
        lovinInterstitialIds: String,
        isRemoteConfigActive: Boolean,
        isAppPurchased: Boolean,
        mListener: LovinInterstitialOnCallBack
    ) {
        lovinInterstitialOnCallBack = mListener
        if (isInternetConnected(mActivity) && !isAppPurchased && isRemoteConfigActive) {
            if (mInterstitialAd == null && !isLoadingAd) {
                isLoadingAd = true
                mInterstitialAd = MaxInterstitialAd( lovinInterstitialIds, mActivity )
                mInterstitialAd?.setListener( object : MaxAdListener{
                    override fun onAdLoaded(ad: MaxAd?) {
                        isLoadingAd = false
                        lovinInterstitialOnCallBack?.onAdLoaded(ad)
                        Log.d(AD_TAG, "Lovin Interstitial ad is loaded and ready to be displayed!")
                    }

                    override fun onAdDisplayed(ad: MaxAd?) {
                        lovinInterstitialOnCallBack?.onAdDisplayed(ad)
                        Log.d(AD_TAG, "Lovin Interstitial ad is displayed!")
                    }

                    override fun onAdHidden(ad: MaxAd?) {
                        isLoadingAd = false
                        lovinInterstitialOnCallBack?.onAdHidden(ad)
                        mInterstitialAd?.loadAd()
                        Log.d(AD_TAG, "Lovin Interstitial ad dismissed!")
                    }

                    override fun onAdClicked(ad: MaxAd?) {
                        lovinInterstitialOnCallBack?.onAdClicked(ad)
                        Log.d(AD_TAG, "Lovin Interstitial ad Clicked!")
                    }

                    override fun onAdLoadFailed(adUnitId: String?, error: MaxError?) {
                        isLoadingAd = false
                        lovinInterstitialOnCallBack?.onAdLoadFailed(adUnitId,error)
                        mInterstitialAd = null
                        Log.e(AD_TAG, "Lovin Interstitial ad failed to load: " + error?.message)
                    }

                    override fun onAdDisplayFailed(ad: MaxAd?, error: MaxError?) {
                        isLoadingAd = false
                        lovinInterstitialOnCallBack?.onAdDisplayFailed(ad,error)
                        Log.e(AD_TAG, "Lovin Interstitial ad display Failed!")
                        mInterstitialAd?.loadAd()
                    }

                })
                mInterstitialAd?.loadAd()

            }
        }

    }

    fun destroyInterstitialAds(){
        mInterstitialAd?.destroy()
    }

    fun showInterstitialAds(){
        mInterstitialAd?.let {
            if (it.isReady){
                it.showAd()
            }
        }
    }

    fun isInterstitialAdsLoaded():Boolean{
       return mInterstitialAd?.isReady ?:false
    }


}