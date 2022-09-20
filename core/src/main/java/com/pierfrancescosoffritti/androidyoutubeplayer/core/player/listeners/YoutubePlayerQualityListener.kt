package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners

interface YouTubePlayerQualityListener {
    fun onYouTubePlayerChangeQuality(quality: String)
    fun onYouTubePlayerAutomateQuality()
}
