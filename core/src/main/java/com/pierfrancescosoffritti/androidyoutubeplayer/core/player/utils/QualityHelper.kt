package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerQualityListener
import android.os.Handler
import android.os.Looper
import java.util.HashSet

internal class QualityHelper() {

    var quality: String = "auto"
        private set

    private val qualityListeners: MutableSet<YouTubePlayerQualityListener> = HashSet()
    
    private val mainThreadHandler: Handler = Handler(Looper.getMainLooper())
    
    fun changeQuality(playbackQuality: String) {
        
        quality = playbackQuality
        
        mainThreadHandler.post { loadUrl("javascript:setQuality($playbackQuality)") }
        
        for (qualityListener in qualityListeners)
            qualityListener.onYouTubePlayerChangeQuality(quality)
    }

    fun automateQuality() {
        if (quality == "auto") return

        quality = "auto"
        
        mainThreadHandler.post { loadUrl("javascript:setQuality($playbackQuality)") }

        for (qualityListener in qualityListeners)
            qualityListener.onYouTubePlayerAutomateQuality()
    }
    
    fun addQualityListener(qualityListener: YouTubePlayerQualityListener): Boolean {
        return qualityListeners.add(qualityListener)
    }
}
