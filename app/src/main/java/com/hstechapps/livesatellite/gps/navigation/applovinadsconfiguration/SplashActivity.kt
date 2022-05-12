package com.hstechapps.livesatellite.gps.navigation.applovinadsconfiguration

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxError
import com.applovin.mediation.nativeAds.MaxNativeAdView
import com.hstechapps.livesatellite.gps.navigation.applovinadsconfiguration.adsconfig.LovinBannerAds
import com.hstechapps.livesatellite.gps.navigation.applovinadsconfiguration.adsconfig.LovinInterstitialAds
import com.hstechapps.livesatellite.gps.navigation.applovinadsconfiguration.adsconfig.callbacks.LovinInterstitialOnCallBack
import com.hstechapps.livesatellite.gps.navigation.applovinadsconfiguration.adsconfig.callbacks.LovinNativeCallBack
import com.hstechapps.livesatellite.gps.navigation.applovinadsconfiguration.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var lovinSplashInterstitial: LovinInterstitialAds
    private lateinit var lovinBannerAds: LovinBannerAds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        binding.btnNext.setOnClickListener {
            binding.btnNext.isEnabled = false
            intentMethod()
        }
        initAds()

    }

    private fun initAds(){
        lovinSplashInterstitial = LovinInterstitialAds(this)
        lovinBannerAds = LovinBannerAds(this)


        // call splash interstitial
        lovinSplashInterstitial.loadAndShowInterstitialAd(getString(R.string.applovin_interstitial_splash_ids),
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



        // call native ads
        lovinBannerAds.loadNative(binding.adsContainerLayout,
            binding.nativeAdContainer,
            binding.loadingLayout,
            getString(R.string.applovin_splash_native_ids),
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

    }


    private fun intentMethod() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()

        lovinSplashInterstitial.showInterstitialAds()
    }


    override fun onDestroy() {
        super.onDestroy()
        lovinSplashInterstitial.destroyInterstitialAds()

    }


}