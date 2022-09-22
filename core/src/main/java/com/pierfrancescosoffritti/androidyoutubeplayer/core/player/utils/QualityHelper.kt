package com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerQualityListener
import java.util.HashSet
import java.util.Collections

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.pierfrancescosoffritti.androidyoutubeplayer.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerQualityListener       /** **/
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.FullScreenHelper
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.QualityHelper                          /** **/
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.PlaybackResumer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.PlayerUiController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.NetworkListener

internal class QualityHelper(private val player: WebViewYoutubePlayer) {

    var quality: String = "auto"
        private set

    private val qualityListeners: MutableSet<YouTubePlayerQualityListener> = HashSet()
        
    fun changeQuality(playbackQuality: String) {
        
        quality = playbackQuality
        player.setQuality(quality)
                
        for (qualityListener in qualityListeners)
            qualityListener.onYouTubePlayerChangeQuality(quality)
    }

    fun automateQuality() {
        if (quality == "auto") return

        quality = "auto"
        player.setQuality(quality)
        
        for (qualityListener in qualityListeners)
            qualityListener.onYouTubePlayerAutomateQuality()
    }
    
    fun addQualityListener(qualityListener: YouTubePlayerQualityListener): Boolean {
        return qualityListeners.add(qualityListener)
    }
}
