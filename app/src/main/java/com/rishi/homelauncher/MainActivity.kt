package com.rishi.homelauncher

import android.content.Intent
import android.content.pm.ResolveInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.pm.PackageInfoCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.rishi.homelauncher.adapter.AppInfoListAdapter
import com.rishi.homelauncher.model.ApplicationDetails
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private val applicationInfoList = mutableListOf<ApplicationDetails>()
    private lateinit var appInfoListAdapter: AppInfoListAdapter
    private lateinit var context: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        context = this
        loadApps()
        showApplicationData()
    }

    private fun loadApps() {
        applicationInfoList.clear()
        val manager = packageManager
        val i = Intent(Intent.ACTION_MAIN, null)
        i.addCategory(Intent.CATEGORY_LAUNCHER)
        val availableActivities: List<ResolveInfo> = manager.queryIntentActivities(i, 0)
        for (ri in availableActivities) {
            val appName = ri.loadLabel(manager)
            val packageName = ri.activityInfo.packageName
            val appIcon = ri.activityInfo.loadIcon(manager)
            val mainActivityName = ri.activityInfo.name
            val versionName = manager.getPackageInfo(packageName, 0).versionName
            val versionCode =
                PackageInfoCompat.getLongVersionCode(manager.getPackageInfo(packageName, 0));
            val applicationInfo = ApplicationDetails(
                appName.toString(),
                packageName,
                mainActivityName,
                versionName,
                versionCode.toInt(),
                appIcon
            )
            applicationInfoList.add(applicationInfo)
        }
        applicationInfoList.sortWith(Comparator { applicationDetails, applicationDetail ->
            applicationDetails.appName.toLowerCase(Locale.getDefault())
                .compareTo(applicationDetail.appName.toLowerCase(Locale.getDefault()))
        })
    }

    private fun showApplicationData() {
        appInfoListAdapter = AppInfoListAdapter(context, applicationInfoList)
        app_list_rv.layoutManager = GridLayoutManager(baseContext, 4)
        app_list_rv.adapter = appInfoListAdapter
    }
}