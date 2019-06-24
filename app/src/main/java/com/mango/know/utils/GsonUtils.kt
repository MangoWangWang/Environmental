package com.mango.know.utils

import android.text.TextUtils
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject

import java.lang.reflect.Type
import java.util.ArrayList
import java.util.HashMap

object GsonUtils {

    var gson = Gson()

    @Throws(JsonSyntaxException::class)
    fun <T> deSerializedFromJson(json: String, clazz: Class<T>): T {
        return gson.fromJson(json, clazz)
    }

    @Throws(JsonSyntaxException::class)
    fun <T> deSerializedFromJson(json: String, type: Type): T {
        return gson.fromJson(json, type)
    }

    fun serializedToJson(`object`: Any?): String {
        return if (`object` != null) {
            gson.toJson(`object`)
        } else {
            ""
        }
    }

    /**
     * 获取JsonObject
     *
     * @return JsonObject
     */
    fun parseJson(json: String): JsonObject? {
        var jsonObj: JsonObject? = null
        try {
            val parser = JsonParser()
            jsonObj = parser.parse(json).asJsonObject
        } catch (e: JsonSyntaxException) {
            Log.e("frank", "parseJson Exception===$e")
        }

        return jsonObj
    }

    /**
     * json字符串转成Bean对象
     *
     * @param str
     * @param type
     * @return
     */
    fun <T> jsonToBean(str: String, type: Class<T>): T? {
        val gson = Gson()
        try {
            return gson.fromJson(str, type)
        } catch (e: JsonSyntaxException) {
            Log.e("frank", "jsonToBean Exception===$e\n$str")

        }

        return null
    }

    fun <T> jsonToBeanFromData(str: String, type: Class<T>): T? {
        return jsonToBean(str, "datas", type)
    }

    fun <T> jsonToBean(str: String, key: String, type: Class<T>): T? {
        val data = getStringFromJSON(str, key) ?: return null
        val gson = Gson()
        try {
            return gson.fromJson(data, type)
        } catch (e: JsonSyntaxException) {
            Log.e("frank", "jsonToBean Exception===$e\n$str")

        }

        return null
    }


    fun getStringFromJSON(json: String, key1: String, key2: String): String? {
        var data = ""
        try {
            val jsonObject = JSONObject(json).getJSONObject(key1)
            data = jsonObject.getString(key2)

        } catch (e: JSONException) {
            e.printStackTrace()
            Log.e("frank", "getStringFromJSON Exception===$e")
        }

        return data
    }


    fun getLongFormJSON(json: String, key1: String, key2: String): Long {
        var data: Long = 0
        try {
            val jsonObject = JSONObject(json).getJSONObject(key1)
            data = jsonObject.getLong(key2)

        } catch (e: JSONException) {
            Log.e("frank", "getLongFormJSON Exception===$e")
        }

        return data
    }

    /**
     * @param json
     * @param key1
     * @param key2
     * @return
     */
    // 现有逻辑有问题 ：更新包判断
    fun getBooleanFormJSON(json: String, key1: String, key2: String): Boolean {
        var data = true
        try {
            val jsonObject = JSONObject(json).getJSONObject(key1)
            data = jsonObject.getBoolean(key2)
        } catch (e: JSONException) {
            Log.e("frank", "getBooleanFormJSON Exception===$e")
        }

        return data
    }

    fun jsonToBeanMap(json: String?): Map<String, String> {
        val map: Map<String, String>?
        if (json == null || TextUtils.isEmpty(json)) {
            return HashMap()
        } else { //json data 字段不为空
            map = gson.fromJson<Map<String, String>>(json, object : TypeToken<Map<String, String>>() {

            }.type)
            if (map != null) {
                return map
            }
        }
        return HashMap()
    }

    fun <T, P> jsonToBeanMap(json: String?, tClass: Type): Map<T, P> {
        val map: Map<T, P>?
        if (json == null || TextUtils.isEmpty(json)) {
            return HashMap()
        } else { //json data 字段不为空
            map = gson.fromJson<Map<T, P>>(json, tClass)
            if (map != null) {
                return map
            }
        }
        return HashMap()
    }

    /**
     * 根据key获取json object 的value
     *
     * @param json
     * @param key
     * @return
     */
    fun getBooleanFormJSON(json: String, key: String): Boolean {
        var data = false
        try {
            val jsonObject = JSONObject(json)
            data = jsonObject.getBoolean(key)
        } catch (e: JSONException) {
            Log.e("frank", "getBooleanFormJSON Exception===$e\njson=$json\nkey=$key")
        }

        return data
    }


    /**
     * 从JSON字符串提取出对应 Key的 字符串
     *
     * @param json
     * @param key
     * @return
     */
    fun getStringFromJSON(json: String?, key: String): String? {
        var data: String? = null
        if (json == null) return null
        try {
            val jsonObject = JSONObject(json)
            data = jsonObject.getString(key)
        } catch (e: JSONException) {
            Log.e("frank", "getStringFromJSON Exception===\n$e\n【key=$key】json=$json")
        }

        return data
    }


    fun getIntFromJSON(obj: JSONObject?, key: String): Int {
        var data = 0
        try {
            if (obj == null) {
                return data
            }
            data = obj.getInt(key)
        } catch (e: JSONException) {
            Log.e("frank", "getIntFromJSON Exception===$e")
        }

        return data
    }


    fun getIntFromJSON(json: String, key: String): Int {
        var data = 0
        try {
            val jsonObject = JSONObject(json)
            data = jsonObject.getInt(key)
        } catch (e: JSONException) {
            Log.e("frank", "getIntFromJSON Exception===$e")
        }

        return data
    }

    fun getDoubleFromJSON(json: String, key: String): Double {
        var data = 0.0
        try {
            val jsonObject = JSONObject(json)
            data = jsonObject.getDouble(key)
        } catch (e: JSONException) {
            Log.e("frank", "getIntFromJSON Exception===$e")
        }

        return data
    }

    fun getIntFromJSON(json: String, key1: String, key2: String): Int {
        var data = 0
        try {
            val jsonObject = JSONObject(json)
            val dataJson = jsonObject.getString(key1)
            data = getIntFromJSON(dataJson, key2)
        } catch (e: JSONException) {
            Log.e("frank", "getIntFromJSON Exception===$e")
        }

        return data
    }

    fun getLongFromJSON(json: String, key: String): Long {
        var data: Long = 0
        try {
            val jsonObject = JSONObject(json)
            data = jsonObject.getLong(key)
        } catch (e: JSONException) {
            Log.e("frank", "getLongFromJSON Exception===$e")
        }

        return data
    }

    fun getLongFromJSON(json: String, key1: String, key2: String): Long {
        var data: Long = 0
        try {
            val jsonObject = JSONObject(json)
            val dataJson = jsonObject.getString(key1)
            data = getLongFromJSON(dataJson, key2)
        } catch (e: JSONException) {
            Log.e("frank", "getLongFromJSON Exception===$e")
        }

        return data
    }

    fun <T> jsonToBeanList(json: String?, tClass: Type): List<T> {
        val list: List<T>?
        if (json == null || TextUtils.isEmpty(json)) { // json data 字段为空
            return ArrayList()
        } else { //json data 字段不为空
            try {
                list = gson.fromJson<List<T>>(json, tClass)
                if (list != null) {
                    return list
                }
            } catch (e: JsonSyntaxException) {
                Log.e("frank", "jsonToBeanList_2 Exception ===$e")
            }

        }
        return ArrayList()
    }

    /**
     * 3.0
     * 从data里解析出对象数组  key1 一般为 : data  key2 ： your key
     *
     * @param json
     * @param tClass
     * @return list<T>  数据异常返回  list (size is 0)
    </T> */
    fun <T> jsonToBeanList(json: String, key1: String, key2: String, tClass: Type): List<T> {
        val list: List<T>?
        val data = getStringFromJSON(json, key1, key2)
        if (data == null || TextUtils.isEmpty(data)) { // json data 字段为空
            return ArrayList()
        } else { //json data 字段不为空
            try {
                list = gson.fromJson<List<T>>(data, tClass)
                if (list != null) {
                    return list
                }
            } catch (je: JsonSyntaxException) {
                Log.e("frank", "jsonToBeanList Exception===$je")
            }

        }
        return ArrayList()
    }
}
