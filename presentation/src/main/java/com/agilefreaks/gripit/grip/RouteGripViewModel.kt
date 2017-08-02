package com.agilefreaks.gripit.grip

import android.databinding.BaseObservable
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.provider.MediaStore
import android.view.View
import com.agilefreaks.gripit.core.Lifecycle


class RouteGripViewModel : BaseObservable(), RouteGripContract.ViewModel {
    var videoThumbnail: Bitmap? = null

    lateinit var viewCallback: RouteGripContract.View

    override fun onViewResume() {
    }

    override fun onViewAttached(viewCallback: Lifecycle.View) {
        this.viewCallback = viewCallback as RouteGripContract.View

    }

    override fun onViewDetached() {
    }

    override fun onViewPaused() {
    }

    @Suppress("UNUSED_PARAMETER")
    fun onIconClick(view: View) {
        viewCallback.loadFromGallery()
    }

    override fun handleGalleryResult(filePath: String) {
        videoThumbnail = ThumbnailUtils.createVideoThumbnail(filePath, MediaStore.Video.Thumbnails.MICRO_KIND)
        notifyChange()
    }


}