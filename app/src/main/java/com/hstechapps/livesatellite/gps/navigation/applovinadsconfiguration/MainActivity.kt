package com.hstechapps.livesatellite.gps.navigation.applovinadsconfiguration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxError
import com.applovin.mediation.nativeAds.MaxNativeAdView
import com.hstechapps.livesatellite.gps.navigation.applovinadsconfiguration.adsconfig.LovinBannerAds
import com.hstechapps.livesatellite.gps.navigation.applovinadsconfiguration.adsconfig.LovinInterstitialAds
import com.hstechapps.livesatellite.gps.navigation.applovinadsconfiguration.adsconfig.callbacks.LovinBannerCallBack
import com.hstechapps.livesatellite.gps.navigation.applovinadsconfiguration.adsconfig.callbacks.LovinInterstitialOnCallBack
import com.hstechapps.livesatellite.gps.navigation.applovinadsconfiguration.adsconfig.callbacks.LovinNativeCallBack
import com.hstechapps.livesatellite.gps.navigation.applovinadsconfiguration.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var lovinInterstitialAds: LovinInterstitialAds
    private lateinit var lovinBannerAds: LovinBannerAds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initAds()
        binding.btnShow.setOnClickListener {
            lovinInterstitialAds.showInterstitialAds()
        }
    }

    private fun initAds(){
        lovinInterstitialAds = LovinInterstitialAds(this)
        lovinBannerAds = LovinBannerAds(this)

        // load interstitial ads
        lovinInterstitialAds.loadShowAndLoadInterstitialAd(getString(R.string.applovin_interstitial_main_ids),
            true,
            false,object: LovinInterstitialOnCallBack {
                override fun onAdLoadFailed(adUnitId: String?, error: MaxError?) {
                }

                override fun onAdLoaded(maxAd: MaxAd?) {
                }

                override fun onAdDisplayFailed(ad: MaxAd?, error: MaxError?) {
                }

                override fun onAdDisplayed(maxAd: MaxAd?) {
                }

                override fun onAdClicked(maxAd: MaxAd?) {
                }

                override fun onAdHidden(maxAd: MaxAd?) {
                }


            })

        // load small native

        lovinBannerAds.loadNative(binding.nativeAdsContainerLayout,
            binding.nativeAdContainer,
            binding.nativeLoadingLayout,
            getString(R.string.applovin_small_native_ids),
            true,
            false,
            object : LovinNativeCallBack {
                override fun onNativeAdLoaded(nativeAdView: MaxNativeAdView?, ad: MaxAd) {
                }

                override fun onNativeAdLoadFailed(adUnitId: String, error: MaxError) {
                }

                override fun onNativeAdClicked(ad: MaxAd) {
                }


            })


        // load small Banner

        lovinBannerAds.loadBannerAds(binding.bannerAdsContainerLayout,
            binding.bannerPlaceHolder,
            binding.bannerLoadingLayout,
            getString(R.string.applovin_banner_ids),
            true,
            false,
            object : LovinBannerCallBack {
                override fun onAdLoaded(maxAd: MaxAd?) {
                }

                override fun onAdLoadFailed(adUnitId: String?, error: MaxError?) {
                }

                override fun onAdDisplayFailed(ad: MaxAd?, error: MaxError?) {
                }

                override fun onAdClicked(maxAd: MaxAd?) {
                }

                override fun onAdExpanded(maxAd: MaxAd?) {
                }

                override fun onAdCollapsed(maxAd: MaxAd?) {
                }

                override fun onAdDisplayed(maxAd: MaxAd?) {
                }

                override fun onAdHidden(maxAd: MaxAd?) {
                }


            })
    }

}