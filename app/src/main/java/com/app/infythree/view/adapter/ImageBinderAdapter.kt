package com.app.infythree.view.adapter

import android.R
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


object ImageBinderAdapter {

        @JvmStatic
        @BindingAdapter("android:src")
        fun setImageUrl(view: ImageView, url: String?) {
            if (!url.isNullOrBlank()) {

                Log.e("III",url)

                val options: RequestOptions = RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.ic_notification_clear_all)
                    .error(R.drawable.ic_lock_idle_lock)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)

                Glide.with(view.context).load(url).into(view)
            }
    }
}