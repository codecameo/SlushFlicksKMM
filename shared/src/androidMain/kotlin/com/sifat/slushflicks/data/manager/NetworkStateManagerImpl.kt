package com.sifat.slushflicks.data.manager

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.sifat.slushflicks.applicationScope
import com.sifat.slushflicks.data.broadcaster.Broadcaster
import kotlinx.coroutines.launch

@SuppressLint("MissingPermission")
class NetworkStateManagerImpl(
    context: Context,
    private val networkBroadcaster: Broadcaster<Boolean>
) : NetworkStateManager {

    private val connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private var isOnline = false
    private val availableNetwork = mutableSetOf<String>()

    init {
        val request = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
            .build()

        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                availableNetwork.add(network.toString())
                if (!isOnline) {
                    isOnline = true
                    broadcastOnlineState()
                }
            }

            override fun onLost(network: Network) {
                availableNetwork.remove(network.toString())
                if (isOnline && availableNetwork.isEmpty()) {
                    isOnline = false
                    broadcastOnlineState()
                }
            }
        }
        /**
         * This wont cause memory leak as
         * The app continues to receive callbacks until either the app exits
         * or it calls unregisterNetworkCallback().
         * */
        connectivityManager.registerNetworkCallback(request, callback)
    }

    override fun isOnline() = isOnline

    private fun broadcastOnlineState() {
        applicationScope.launch {
            networkBroadcaster.broadcast(isOnline)
        }
    }
}
