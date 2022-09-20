package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerQualityListener
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import java.util.HashSet

internal class QualityHelper() {

    var quality: String = "auto"
        private set

    private val qualityListeners: MutableSet<YouTubePlayerQualityListener> = HashSet()
    
    private val mainThreadHandler: Handler = Handler(Looper.getMainLooper())
    
    fun changeQuality(playbackQuality: String) {
        
        quality = playbackQuality
        
        mainThreadHandler.post { loadUrl("javascript:setQuality($quality)") }
        
        for (qualityListener in qualityListeners)
            qualityListener.onYouTubePlayerChangeQuality(quality)
    }

    fun automateQuality() {
        if (quality == "auto") return

        quality = "auto"
        
        mainThreadHandler.post { loadUrl("javascript:setQuality($quality)") }

        for (qualityListener in qualityListeners)
            qualityListener.onYouTubePlayerAutomateQuality()
    }
    
    fun addQualityListener(qualityListener: YouTubePlayerQualityListener): Boolean {
        return qualityListeners.add(qualityListener)
    }
}
