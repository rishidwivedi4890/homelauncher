package com.rishi.homelauncher.model

import android.graphics.drawable.Drawable

/**
 * Created by Rishi Dwivedi on 08-10-2020.
 */
data class ApplicationDetails(
    val appName: String,
    val packageName: String,
    val activityName: String,
    val versionName: String,
    val versionCode: Int,
    val appIcon: Drawable
) {
}