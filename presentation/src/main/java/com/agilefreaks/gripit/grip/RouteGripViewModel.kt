package com.agilefreaks.gripit.grip

import android.content.Context
import android.databinding.BaseObservable
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.net.Uri
import android.provider.MediaStore
import android.support.design.widget.Snackbar
import android.support.v4.content.CursorLoader
import android.view.View
import com.agilefreaks.gripit.R
import com.agilefreaks.gripit.core.Lifecycle
import io.reactivex.Observable
import javax.inject.Inject


class RouteGripViewModel @Inject constructor(val context: Context) : BaseObservable(), RouteGripContract.ViewModel {
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

    fun onThumbnailClick(view: View) {
        if (videoThumbnail != null) {
            Snackbar.make(view, context.getString(R.string.remove_video_string), Snackbar.LENGTH_LONG).
                    setAction("Remove", {
                        videoThumbnail = null
                        notifyChange()
                    }).
                    show()
        }

    }

    override fun handleGalleryResult(data: Uri) {

        Observable.just(ThumbnailUtils.createVideoThumbnail(getRealPath(data), MediaStore.Video.Thumbnails.MICRO_KIND)).
                subscribe({
                    videoThumbnail = it
                    notifyChange()
                })
    }

    fun getRealPath(uri: Uri): String {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val loader = CursorLoader(context, uri, projection, null, null, null)
        val cursor = loader.loadInBackground()
        val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val result = cursor.getString(columnIndex)
        cursor.close()
        return result
    }
}