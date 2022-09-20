package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerQualityListener

import java.util.HashSet

internal class QualityHelper() {

    var quality: String = "auto"
        private set

    private val qualityListeners: MutableSet<YouTubePlayerQualityListener> = HashSet()

    fun changeQuality(playbackQuality: String) {
        
        quality = playbackQuality
        
        for (qualityListener in qualityListeners)
            qualityListener.onYouTubePlayerChangeQuality(quality)
    }

    fun automateQuality() {
        if (quality == "auto") return

        quality = "auto"

        for (qualityListener in qualityListeners)
            qualityListener.onYouTubePlayerAutomateQuality()
    }
    
    fun addQualityListener(qualityListener: YouTubePlayerQualityListener): Boolean {
        return qualityListeners.add(qualityListener)
    }
}
