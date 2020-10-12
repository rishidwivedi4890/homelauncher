package com.rishi.homelauncher.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.rishi.homelauncher.R
import com.rishi.homelauncher.model.ApplicationDetails


/**
 * Created by Rishi Dwivedi on 08-10-2020.
 */
class AppInfoListAdapter(
    private val context: AppCompatActivity,
    private val appInfoList: List<ApplicationDetails>
) :
    RecyclerView.Adapter<AppInfoListAdapter.RecyclerViewViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AppInfoListAdapter.RecyclerViewViewHolder {
        val rootView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_app_info_layout, parent, false)
        return RecyclerViewViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return appInfoList.size
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        val appInfo = appInfoList[position]
        holder.appIcon.setImageDrawable(appInfo.appIcon)
        holder.appName.text = appInfo.appName
        holder.appName.setOnClickListener {
            launchApplication(appInfo.packageName)
        }
        holder.appIcon.setOnClickListener {
            launchApplication(appInfo.packageName)
        }
    }

    private fun launchApplication(packageName: String) {
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        context.startActivity(intent)
    }

    class RecyclerViewViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val appIcon: ImageView = v.findViewById(R.id.app_icon)
        val appName: TextView = v.findViewById(R.id.app_name)
    }

}