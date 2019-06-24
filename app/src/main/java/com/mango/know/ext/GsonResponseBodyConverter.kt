package com.mango.know.ext


import com.mango.know.bean.BaseBean
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException
import java.lang.reflect.Type

class GsonResponseBodyConverter<T>(private val gson: Gson, private val type: Type) : Converter<ResponseBody, T> {

    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T? {
        val response = value.string()
        try {
            val baseBean = gson.fromJson(response, BaseBean::class.java)
            if (baseBean.code != "200") {
                throw ResultException(baseBean.code, baseBean.message)
            }
            return gson.fromJson<T>(response, type)
        } finally {
            value.close()
        }
    }
}

