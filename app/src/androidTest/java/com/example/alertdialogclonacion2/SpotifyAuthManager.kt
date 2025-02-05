package com.example.alertdialogclonacion2

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import com.github.scribejava.core.builder.ServiceBuilder
import com.github.scribejava.core.model.OAuth2AccessToken
import com.github.scribejava.core.oauth.OAuth20Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class SpotifyAuthManager(private val context: Context) {

    companion object {
        private const val CLIENT_ID = "e42d03e83bc64c7485315823a0cac030"
        private const val CLIENT_SECRET = "3dc87a54c457442c8051401e107e9320"
        private const val REDIRECT_URI = "com.example.alertdialogclonacion2://callback"
        private const val SCOPE = "user-read-private user-read-email playlist-read-private" // Add other scopes as needed
    }

    private val service: OAuth20Service = ServiceBuilder(CLIENT_ID)
        .apiSecret(CLIENT_SECRET)
        .defaultScope(SCOPE)
        .callback(REDIRECT_URI)
        .build(SpotifyApi.instance())

    fun getAuthorizationUrl(): String {
        return service.authorizationUrl
    }

    fun launchCustomTab(authUrl: String) {
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.launchUrl(context, Uri.parse(authUrl))
    }

    suspend fun handleAuthResponse(intent: Intent?): OAuth2AccessToken? {
        val uri = intent?.data
        val code = uri?.getQueryParameter("code")

        return if (code != null) {
            try {
                withContext(Dispatchers.IO) {
                    service.getAccessToken(code)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        } else {
            null
        }
    }

    suspend fun getUserPlaylists(accessToken: String): List<Playlist>? {
        val url = "https://api.spotify.com/v1/me/playlists"
        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", "Bearer $accessToken")
            .build()

        return try {
            val response = OkHttpClient().newCall(request).execute()
            if (response.isSuccessful) {
                val jsonResponse = JSONObject(response.body?.string() ?: "")
                val items = jsonResponse.getJSONArray("items")

                val playlists = mutableListOf<Playlist>()
                for (i in 0 until items.length()) {
                    val playlistJson = items.getJSONObject(i)
                    val name = playlistJson.getString("name")
                    val imageUrl = playlistJson.getJSONArray("images").getJSONObject(0).getString("url")
                    playlists.add(Playlist(name, imageUrl))
                }
                playlists
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    data class Playlist(val name: String, val imageUrl: String)

    class SpotifyApi private constructor() : com.github.scribejava.core.builder.api.DefaultApi20() {

        override fun getAccessTokenEndpoint(): String {
            return "https://accounts.spotify.com/api/token"
        }

        override fun getAuthorizationBaseUrl(): String {
            return "https://accounts.spotify.com/authorize"
        }

        companion object {
            fun instance(): SpotifyApi {
                return SpotifyApi()
            }
        }
    }
}
