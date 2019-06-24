package com.mango.know.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.widget.ScrollView

import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

object SaveViewToImageUtil {

    //view 转换成bitmap
    fun getViewBitmap(v: View): Bitmap? {
        v.clearFocus()
        v.isPressed = false
        val willNotCache = v.willNotCacheDrawing()
        v.setWillNotCacheDrawing(false)
        val color = v.drawingCacheBackgroundColor
        v.drawingCacheBackgroundColor = 0
        if (color != 0) {
            v.destroyDrawingCache()
        }
        v.buildDrawingCache()
        val cacheBitmap = v.drawingCache ?: return null
        val bitmap = Bitmap.createBitmap(cacheBitmap)
        v.destroyDrawingCache()
        v.setWillNotCacheDrawing(willNotCache)
        v.drawingCacheBackgroundColor = color
        return bitmap
    }


    //超过屏幕view 转换成bitmap
    fun getBitmapByView(scrollView: ScrollView): Bitmap {
        var h = 0
        var bitmap: Bitmap? = null
        for (i in 0 until scrollView.childCount) {
            h += scrollView.getChildAt(i).height
        }
        bitmap = Bitmap.createBitmap(
            scrollView.width, h,
            Bitmap.Config.RGB_565
        )
        val canvas = Canvas(bitmap!!)
        scrollView.draw(canvas)
        return bitmap
    }

    //将bitmap保存为图片
    fun savePhotoToSDCard(photoBitmap: Bitmap?, path: String, photoName: String) {
        if (checkSDCardAvailable()) {
            val dir = File(path)
            if (!dir.exists()) {
                dir.mkdirs()
            }

            val photoFile = File(path, "$photoName.png")
            var fileOutputStream: FileOutputStream? = null
            try {
                fileOutputStream = FileOutputStream(photoFile)
                if (photoBitmap != null) {
                    if (photoBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)) {
                        fileOutputStream.flush()
                    }
                }
            } catch (e: FileNotFoundException) {
                photoFile.delete()
                e.printStackTrace()
            } catch (e: IOException) {
                photoFile.delete()
                e.printStackTrace()
            } finally {
                try {
                    fileOutputStream!!.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
    }

    fun checkSDCardAvailable(): Boolean {
        return android.os.Environment.getExternalStorageState() == android.os.Environment.MEDIA_MOUNTED
    }

}
