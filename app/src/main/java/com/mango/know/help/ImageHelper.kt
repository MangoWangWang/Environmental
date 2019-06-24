//package com.tft591.tft.help
//
//import android.app.Activity
//import android.support.v4.app.Fragment
//import com.tft591.tft.R
//import com.tft591.tft.base.IView
//import com.tft591.tft.ext.sss
//import com.tft591.tft.http.RetrofitHelper
//import com.tft591.tft.utils.DialogUtil
//import com.luck.picture.lib.PictureSelector
//import com.luck.picture.lib.config.PictureConfig
//import com.luck.picture.lib.config.PictureMimeType
//import com.luck.picture.lib.entity.LocalMedia
//import okhttp3.MediaType
//import okhttp3.MultipartBody
//import okhttp3.RequestBody
//
//import java.io.File
//
///**
// * Created by Administrator on 2018/2/2 0002.
// * 封装选择头像 图片单选 多选 图片多张上传 单张上传
// * 图片选择后要在 onActivityResult 返回 请看示例
// * @Override
// * protected void onActivityResult(int requestCode, int resultCode, Intent data) {
// * super.onActivityResult(requestCode, resultCode, data);
// * if (resultCode == RESULT_OK) {
// * switch (requestCode) {
// * case PictureConfig.CHOOSE_REQUEST:
// * selectionMedia = PictureSelector.obtainMultipleResult(data);
// * mAdapter.clear();
// * mAdapter.addAll(selectionMedia);
// * recyclerView.setVisibility(View.VISIBLE);
// * break;
// * }
// * }
// * }
// */
//
//object ImageHelper {
//
//    fun browseImages(activity: Activity, position: Int, medias: List<LocalMedia>) {
//        PictureSelector.create(activity).themeStyle(R.style.picture_default_style).openExternalPreview(position, medias)
//    }
//
//    fun browseImages(fragment: Fragment, position: Int, medias: List<LocalMedia>) {
//        PictureSelector.create(fragment).themeStyle(R.style.picture_default_style).openExternalPreview(position, medias)
//    }
//
//    //选择图片 多张 拍照+图片 selectionMedia选中的图片
//    fun showImagesChoose(activity: Activity, selectionMedia: List<LocalMedia>) {
//        DialogUtil.showBottomMenuDialog(activity, "选择图片", "拍照", "相册", object : DialogUtil.BottomMenuDialogImpl {
//            override fun onMenu1() {
//                PictureSelector.create(activity)
//                    .openCamera(PictureMimeType.ofImage())
//                    .compress(true)
//                    .withAspectRatio(1, 1)
//                    .forResult(PictureConfig.CHOOSE_REQUEST)
//            }
//
//            override fun onMenu2() {
//                PictureSelector.create(activity)
//                    .openGallery(PictureMimeType.ofImage())
//                    .selectionMode(PictureConfig.MULTIPLE)
//                    .compress(true)
//                    .enableCrop(false)
//                    .selectionMedia(selectionMedia)
//                    .withAspectRatio(1, 1)
//                    .forResult(PictureConfig.CHOOSE_REQUEST)
//            }
//        })
//    }
//
//    //选择图片 多张 拍照+图片 selectionMedia选中的图片
//    fun showImagesChoose(fragment: Fragment, selectionMedia: List<LocalMedia>) {
//        DialogUtil.showBottomMenuDialog(
//            fragment.context!!,
//            "选择图片",
//            "拍照",
//            "相册",
//            object : DialogUtil.BottomMenuDialogImpl {
//                override fun onMenu1() {
//                    PictureSelector.create(fragment)
//                        .openCamera(PictureMimeType.ofImage())
//                        .compress(true)
//                        .enableCrop(true)
//                        .withAspectRatio(1, 1)
//                        .forResult(PictureConfig.CHOOSE_REQUEST)
//                }
//
//                override fun onMenu2() {
//                    PictureSelector.create(fragment)
//                        .openGallery(PictureMimeType.ofImage())
//                        .selectionMode(PictureConfig.MULTIPLE)
//                        .compress(true)
//                        .enableCrop(false)
//                        .selectionMedia(selectionMedia)
//                        .withAspectRatio(1, 1)
//                        .forResult(PictureConfig.CHOOSE_REQUEST)
//                }
//            })
//    }
//
//    //选择图片 单张
//    fun showImageChoose(activity: Activity) {
//        DialogUtil.showBottomMenuDialog(activity, "选择图片", "拍照", "相册", object : DialogUtil.BottomMenuDialogImpl {
//            override fun onMenu1() {
//                PictureSelector.create(activity)
//                    .openCamera(PictureMimeType.ofImage())
//                    .compress(true)
//                    .enableCrop(false)
//                    .withAspectRatio(1, 1)
//                    .forResult(PictureConfig.CHOOSE_REQUEST)
//            }
//
//            override fun onMenu2() {
//                PictureSelector.create(activity)
//                    .openGallery(PictureMimeType.ofImage())
//                    .selectionMode(PictureConfig.SINGLE)
//                    .compress(true)
//                    .enableCrop(false)
//                    .withAspectRatio(1, 1)
//                    .forResult(PictureConfig.CHOOSE_REQUEST)
//            }
//        })
//    }
//
//    /**
//     * 选择图片 单张
//     * @param fragment
//     */
//    fun showImageChoose(fragment: Fragment) {
//        DialogUtil.showBottomMenuDialog(
//            fragment.context!!,
//            "选择图片",
//            "拍照",
//            "相册",
//            object : DialogUtil.BottomMenuDialogImpl {
//                override fun onMenu1() {
//                    PictureSelector.create(fragment)
//                        .openCamera(PictureMimeType.ofImage())
//                        .compress(true)
//                        .enableCrop(false)
//                        .withAspectRatio(1, 1)
//                        .forResult(PictureConfig.CHOOSE_REQUEST)
//                }
//
//                override fun onMenu2() {
//                    PictureSelector.create(fragment)
//                        .openGallery(PictureMimeType.ofImage())
//                        .selectionMode(PictureConfig.SINGLE)
//                        .compress(true)
//                        .enableCrop(false)
//                        .withAspectRatio(1, 1)
//                        .forResult(PictureConfig.CHOOSE_REQUEST)
//                }
//            })
//    }
//
//    //选择头像
//    fun showAvatarChoose(activity: Activity) {
//        DialogUtil.showBottomMenuDialog(activity, "选择头像", "拍照", "相册", object : DialogUtil.BottomMenuDialogImpl {
//            override fun onMenu1() {
//                PictureSelector.create(activity)
//                    .openCamera(PictureMimeType.ofImage())
//                    .compress(true)
//                    .enableCrop(true)
//                    .withAspectRatio(1, 1)
//                    .forResult(PictureConfig.CHOOSE_REQUEST)
//            }
//
//            override fun onMenu2() {
//                PictureSelector.create(activity)
//                    .openGallery(PictureMimeType.ofImage())
//                    .selectionMode(PictureConfig.SINGLE)
//                    .compress(true)
//                    .enableCrop(true)
//                    .withAspectRatio(1, 1)
//                    .forResult(PictureConfig.CHOOSE_REQUEST)
//            }
//        })
//    }
//
//    //选择头像
//    fun showAvatarChoose(fragment: Fragment) {
//        DialogUtil.showBottomMenuDialog(
//            fragment.context!!,
//            "选择头像",
//            "拍照",
//            "相册",
//            object : DialogUtil.BottomMenuDialogImpl {
//                override fun onMenu1() {
//                    PictureSelector.create(fragment)
//                        .openCamera(PictureMimeType.ofImage())
//                        .compress(true)
//                        .enableCrop(true)
//                        .withAspectRatio(1, 1)
//                        .forResult(PictureConfig.CHOOSE_REQUEST)
//                }
//
//                override fun onMenu2() {
//                    PictureSelector.create(fragment)
//                        .openGallery(PictureMimeType.ofImage())
//                        .selectionMode(PictureConfig.SINGLE)
//                        .compress(true)
//                        .enableCrop(true)
//                        .withAspectRatio(1, 1)
//                        .forResult(PictureConfig.CHOOSE_REQUEST)
//                }
//            })
//    }
//
//    //
////    //上传单张
//    fun upLoadImage( iView: IView,token: String, imagePath: String) {
//        val file = File(imagePath)
////        2、创建RequestBody，其中`multipart/form-data`为编码类型
//        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
//        val part = MultipartBody.Part.createFormData("file", file.getName(), requestFile)
//        val param = hashMapOf<String, String>()
//        param["key"] = token
//        RetrofitHelper.service.updateImage(param, part).sss(iView) {
//            iView.showMsg(it.result.toString())
//        }
//    }
////
////    //上传多张
////    fun upLoadImages(images: List<LocalMedia>, callBack: OnUpLoadImagesCallBack) {
////        val upLoadSuccessImages = ArrayList<String>()
////        upLoad(upLoadSuccessImages, images, images[0].compressPath, 0, callBack)
////    }
////
////    private fun upLoad(
////        upLoadSuccessImages: MutableList<String>,
////        images: List<LocalMedia>,
////        file: String,
////        position: Int,
////        callBack: OnUpLoadImagesCallBack
////    ) {
////        //        showProgress("正在上传" + (position + 1) + "/" + selectionMedia.size());
////        NetworkManager.INSTANCE.upLoadFile(
////            MainApplication.getServiceHost() + Apis.uploadImage,
////            "file",
////            File(file),
////            object : OnUpLoadCallBack() {
////                fun onOk(response: String) {
////                    upLoadSuccessImages.add(response)
////                    val i = position + 1
////                    if (i == images.size) {
////                        try {
////                            callBack.onSuccess(upLoadSuccessImages)
////                        } catch (e: InterruptedException) {
////                            e.printStackTrace()
////                        }
////
////                    } else {
////                        upLoad(upLoadSuccessImages, images, images[i].compressPath, i, callBack)
////                    }
////                }
////
////                fun onError(code: Int, errorMessage: String) {
////                    callBack.onFail(errorMessage)
////                    //                hideProgress();
////                    //                showHintDialog("上传图片失败：" + errorMessage);
////                }
////
////                fun upProgress(progress: Int) {
////
////                }
////            })
////    }
//
//    interface OnUpLoadImagesCallBack {
//        @Throws(InterruptedException::class)
//        fun onSuccess(images: List<String>)
//
//        fun onFail(error: String)
//    }
//
//    interface OnUpLoadImageCallBack {
//        fun onSuccess(images: String)
//        fun onFail(error: String)
//    }
//
//}
