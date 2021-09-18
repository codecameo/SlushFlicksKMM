package com.sifat.slushflicks.data.repository

import android.net.Uri
import android.text.TextUtils
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.sifat.slushflicks.BuildConfig
import com.sifat.slushflicks.data.DynamicLinkConstants.AMPERSAND
import com.sifat.slushflicks.data.DynamicLinkConstants.ANDROID_FALL_BACK_URL
import com.sifat.slushflicks.data.DynamicLinkConstants.CAMPAIGN
import com.sifat.slushflicks.data.DynamicLinkConstants.EQUAL_SIGN
import com.sifat.slushflicks.data.DynamicLinkConstants.HTTP_PREFIX
import com.sifat.slushflicks.data.DynamicLinkConstants.IOS_FALL_BACK_URL
import com.sifat.slushflicks.data.DynamicLinkConstants.MEDIUM
import com.sifat.slushflicks.data.DynamicLinkConstants.QUESTION_MARK
import com.sifat.slushflicks.data.DynamicLinkConstants.SHOW_ID_PARAM
import com.sifat.slushflicks.data.DynamicLinkConstants.SHOW_TYPE_PARAM
import com.sifat.slushflicks.data.DynamicLinkConstants.SOURCE
import com.sifat.slushflicks.data.DynamicLinkParam
import com.sifat.slushflicks.domain.model.DeeplinkWrapper
import com.sifat.slushflicks.domain.repository.DynamicLinkRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class DynamicLinkRepositoryImpl(
    private val baseUrl: String,
    private val dynamicLink: String,
    private val firebaseDynamicLinks: FirebaseDynamicLinks
) : DynamicLinkRepository {
    override suspend fun generateDynamicLink(param: DynamicLinkParam) =
        suspendCancellableCoroutine<String?> {
            val socialMetaTagParameters =
                DynamicLink.SocialMetaTagParameters.Builder().setTitle(param.showName)
                    .setDescription(param.overview)
                    .setImageUrl(Uri.parse(param.imageUrl))
                    .build()

            firebaseDynamicLinks.createDynamicLink()
                .setSocialMetaTagParameters(socialMetaTagParameters)
                .setLink(Uri.parse(generateDeepLink(param.showId, param.showType)))
                .setDomainUriPrefix(HTTP_PREFIX + dynamicLink)
                // Open links with this app on Android
                .setAndroidParameters(
                    DynamicLink.AndroidParameters.Builder(BuildConfig.LIBRARY_PACKAGE_NAME)
                        .setFallbackUrl(Uri.parse(ANDROID_FALL_BACK_URL))
                        .build()
                )
                // As currently there is no ios version of this app, navigating to play store from ios as well
                .setIosParameters(
                    DynamicLink.IosParameters.Builder(BuildConfig.LIBRARY_PACKAGE_NAME)
                        .setFallbackUrl(Uri.parse(IOS_FALL_BACK_URL))
                        .build()
                )
                .setGoogleAnalyticsParameters(
                    DynamicLink.GoogleAnalyticsParameters.Builder()
                        .setSource(SOURCE)
                        .setMedium(MEDIUM)
                        .setCampaign(CAMPAIGN)
                        .build()
                )
                .buildShortDynamicLink()
                .addOnCompleteListener { task ->
                    if (it.isCancelled) return@addOnCompleteListener
                    if (task.isSuccessful) {
                        it.resume(task.result.shortLink?.toString())
                    } else {
                        it.resume(null)
                    }
                }
        }

    override suspend fun getDynamicLink(deeplinkData: DeeplinkWrapper<*>): Pair<String?, Long?>? =
        suspendCancellableCoroutine {
            (deeplinkData.data as? Uri)?.let { uri ->
                FirebaseDynamicLinks.getInstance()
                    .getDynamicLink(uri)
                    .addOnCompleteListener { task ->
                        var deepLink: Uri? = null
                        if (task.isSuccessful && task.result != null) {
                            deepLink = task.result.link
                        } else if (!TextUtils.isEmpty(uri.toString())) {
                            deepLink = uri
                        }
                        deepLink?.let { link ->
                            it.resume(
                                Pair(
                                    link.getQueryParameter(SHOW_TYPE_PARAM),
                                    link.getQueryParameter(SHOW_ID_PARAM)?.toLong()
                                )
                            )
                        } ?: it.resume(null)
                    }
            } ?: it.resume(null)
        }

    private fun generateDeepLink(showId: Long, showType: String): String? {
        val dynamicLinkUrl = StringBuilder(baseUrl)
            .append(QUESTION_MARK)
            .append(SHOW_ID_PARAM)
            .append(EQUAL_SIGN)
            .append(showId)
            .append(AMPERSAND)
            .append(SHOW_TYPE_PARAM)
            .append(EQUAL_SIGN)
            .append(showType)
        return dynamicLinkUrl.toString()
    }
}
