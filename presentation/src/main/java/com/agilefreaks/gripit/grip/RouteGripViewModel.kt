package com.agilefreaks.gripit.grip

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.ObservableField
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.net.Uri
import android.provider.MediaStore
import android.provider.MediaStore.Video.Thumbnails.MICRO_KIND
import android.support.design.widget.Snackbar
import android.support.v4.content.CursorLoader
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.agilefreaks.gripit.R
import com.agilefreaks.gripit.core.Lifecycle
import com.agilefreaks.gripit.core.model.RouteState
import com.agilefreaks.gripit.domain.RouteGrip
import com.agilefreaks.gripit.domain.interactor.AddRouteGrip
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import javax.inject.Inject


class RouteGripViewModel @Inject constructor(val context: Context, val useCase: AddRouteGrip) : BaseObservable(), RouteGripContract.ViewModel {
    val emptyBitmap: Bitmap = Bitmap.createBitmap(MICRO_KIND, MICRO_KIND, Bitmap.Config.ARGB_8888)
    var videoThumbnail = emptyBitmap
    var videoLocation = ""
    var comment: ObservableField<String> = ObservableField("")
    var routeState: RouteState = RouteState.GripIt
    var buttonState: ObservableField<String> = ObservableField(routeState.toString())
    var screenTitle: ObservableField<String> = ObservableField(routeState.toString() + "Screen")

    var watcher: TextWatcher = object : TextWatcherAdapter() {
        override fun afterTextChanged(text: Editable?) {
            comment.set(text.toString())
        }
    }

    lateinit var viewCallback: RouteGripContract.View

    override fun onViewResume() {
    }

    override fun onViewAttached(viewCallback: Lifecycle.View) {
        this.viewCallback = viewCallback as RouteGripContract.View
        routeState = RouteState.valueOf(this.viewCallback.getRouteState())
        buttonState.set(routeState.toString())
        screenTitle.set(routeState.toString() + " Screen")
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
        if (videoThumbnail.sameAs(emptyBitmap)) return

        Snackbar.make(view, context.getString(R.string.remove_video_string), Snackbar.LENGTH_LONG).
                setAction("Remove", {
                    videoThumbnail = emptyBitmap
                    notifyChange()
                }).
                show()
    }

    @Suppress("UNUSED_PARAMETER")
    fun onButtonClick(view: View) {
        useCase.executeSingle(RouteGrip(viewCallback.getRouteId(), videoLocation, comment.get(), false))
    }

    override fun handleGalleryResult(data: Uri) {
        val location = getRealPath(data)
        Observable.just(ThumbnailUtils.createVideoThumbnail(location, MICRO_KIND)).
                subscribe({
                    videoLocation = location
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
