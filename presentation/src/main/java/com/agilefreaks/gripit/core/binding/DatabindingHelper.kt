package com.agilefreaks.gripit.core.binding

import android.content.Context
import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.provider.MediaStore
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.agilefreaks.gripit.BR
import com.agilefreaks.gripit.R
import com.agilefreaks.gripit.core.model.RouteColor
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DatabindingHelper {
    companion object {
        @JvmStatic @BindingAdapter("image") fun loadImage(view: ImageView, imageLocation: String?) {
            if (imageLocation != null) {
                Picasso.with(view.context).load("file:///android_asset/" + imageLocation).
                        into(view)
            }
        }

        @JvmStatic @BindingAdapter("android:src") fun loadImage(view: ImageView, bitmap: Bitmap?) {
            view.setImageBitmap(bitmap)
        }

        @JvmStatic @BindingAdapter("android:thumbnail") fun createThumbnail(view: ImageView, imageLocation: String?) {
            if (imageLocation.isNullOrBlank()) return

            Observable.just(ThumbnailUtils.createVideoThumbnail(imageLocation, MediaStore.Video.Thumbnails.MICRO_KIND) ?: 0).
                    subscribeOn(Schedulers.newThread()).
                    observeOn(AndroidSchedulers.mainThread()).
                    subscribe({
                        when (it) {
                            is Bitmap -> view.setImageBitmap(it)
                        }
                    })
        }

        @JvmStatic @BindingAdapter("types") fun loadTypes(viewGroup: ViewGroup, entries: List<String>?) {
            viewGroup.removeAllViews()
            if (entries != null) {
                val inflater = viewGroup.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                for (i in entries.indices) {
                    val entry = entries[i]
                    val binding = DataBindingUtil
                            .inflate<ViewDataBinding>(inflater, R.layout.route_type, viewGroup, true)
                    binding.setVariable(BR.type, entry)
                }
            }
        }

        @JvmStatic @BindingAdapter("color") fun loadColor(view: TextView, routeName: String?) {
            if (routeName != null) {
                val colorValue = RouteColor.from(routeName.first())
                when (colorValue) {
                    RouteColor.Green -> view.background = ContextCompat.getDrawable(view.context, R.drawable.ic_circle_green)
                    RouteColor.Yellow -> view.background = ContextCompat.getDrawable(view.context, R.drawable.ic_circle_yellow)
                    RouteColor.Red -> view.background = ContextCompat.getDrawable(view.context, R.drawable.ic_circle_red)
                    RouteColor.Black -> view.background = ContextCompat.getDrawable(view.context, R.drawable.ic_circle_black)
                }
            }
        }
    }
}