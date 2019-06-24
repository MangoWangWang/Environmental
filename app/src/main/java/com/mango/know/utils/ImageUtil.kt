package com.mango.know.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri

import java.io.*

object ImageUtil {
    private val TAG = "ImageUtil"
    private val DEFAULT_BITMAP_COMPRESS_OPTIONS = 87 // 默认的bimap压缩质量等级


    /**
     * 图片压缩方法：
     *
     * @param context  appcontext
     * @param file     路径
     * @param reqWidth 传屏幕宽度
     * @return
     */
    @SuppressLint("NewApi")
    fun decodeSampledBitmapFromFile(
        context: Context,
        file: String, reqWidth: Int
    ): Bitmap? {
        val degree = readPictureDegree(file)
        var bitmap: Bitmap? = null
        try {
            bitmap = compressBitmapNew(file, reqWidth)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return rotateImageView(degree, bitmap)
    }

    /**
     * 旋转图片
     *
     * @param angle
     * @param bitmap
     * @return Bitmap
     */
    @Throws(OutOfMemoryError::class)
    fun rotateImageView(angle: Int, bitmap: Bitmap?): Bitmap? {
        if (bitmap == null)
            return null
        val matrix = Matrix()
        matrix.postRotate(angle.toFloat())
        // 创建新的图片
        return Bitmap.createBitmap(
            bitmap, 0, 0, bitmap.width,
            bitmap.height, matrix, true
        )
    }

    /**
     * 保存Bitmap到指定的文件
     *
     * @param path
     * @param mBitmap
     */
    // @SuppressLint("NewApi")
    fun saveBitmapToFile(mBitmap: Bitmap?, path: String): Boolean {
        val file = File(path)
        if (!file.parentFile.exists()) {
            if (!file.parentFile.mkdirs())
                return false
        }
        var fOut: FileOutputStream? = null
        try {
            val ext = path.substring(path.lastIndexOf('.') + 1)
                .toLowerCase()
            val format = if ("png" == ext)
                Bitmap.CompressFormat.PNG
            else
                Bitmap.CompressFormat.JPEG
            fOut = FileOutputStream(file)
            mBitmap!!.compress(format, DEFAULT_BITMAP_COMPRESS_OPTIONS, fOut)
            // Log.i("ImageUtil","save："+mBitmap.getByteCount());
            fOut.flush()
            return true
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        } finally {
            closeOutputStream(fOut)
        }
    }


    /**
     * 回收bitmap
     */
    fun recycleBitmap(bitmap: Bitmap?) {
        // 先判断是否已经回收
        if (bitmap != null && !bitmap.isRecycled) {
            // 回收并且置为null
            bitmap.recycle()
        }
        System.gc()
    }

    /**
     * 关闭输出流
     */
    private fun closeOutputStream(out: OutputStream?) {
        try {
            out?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * 关闭输入流
     */
    private fun closeInputStream(`in`: InputStream?) {
        try {
            `in`?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * 图片按比例压缩
     *
     * @param path
     * @param size
     * @return
     * @throws IOException
     * @author yusucheng
     */
    @Throws(IOException::class)
    fun compressBitmapNew(path: String, size: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(path, options)
        // 生成压缩的图片
        var i = 0
        val bitmap: Bitmap
        while (true) {
            // 这一步是根据要设置的大小，使宽和高都能满足
            if (options.outWidth shr i <= size && options.outHeight shr i <= size) {
                // 这个参数表示 新生成的图片为原始图片的几分之一。
                options.inSampleSize = Math.pow(2.0, i.toDouble()).toInt()
                options.inPreferredConfig = Bitmap.Config.RGB_565
                options.inDither = true
                // 这里之前设置为了true，所以要改为false，否则就创建不出图片
                options.inJustDecodeBounds = false
                bitmap = BitmapFactory.decodeFile(path, options)
                break
            }
            i += 1
        }
        return bitmap
    }


    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    fun readPictureDegree(path: String): Int {
        var degree = 0
        try {
            val exifInterface = ExifInterface(path)
            val orientation = exifInterface.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )
            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> degree = 90
                ExifInterface.ORIENTATION_ROTATE_180 -> degree = 180
                ExifInterface.ORIENTATION_ROTATE_270 -> degree = 270
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return degree
    }


    fun saveImageToGallery(context: Context, bmp: Bitmap, storePath: String, fileName: String): Boolean {
        // 首先保存图片
        val appDir = File(storePath)
        if (!appDir.exists()) {
            appDir.mkdir()
        }
        val file = File(appDir, fileName)
        try {
            val fos = FileOutputStream(file)
            //通过io流的方式来压缩保存图片
            val isSuccess = bmp.compress(Bitmap.CompressFormat.JPEG, 60, fos)
            fos.flush()
            fos.close()

            //把文件插入到系统图库
            //MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);

            //保存图片后发送广播通知更新数据库
            val uri = Uri.fromFile(file)
            context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri))
            return isSuccess
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return false
    }

}// 1~100