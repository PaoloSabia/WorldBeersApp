package com.example.worldbeersapp.db

import androidx.room.TypeConverter
import com.example.worldbeersapp.rest.models.BoilVolumeModel
import com.example.worldbeersapp.rest.models.IngredientsModel
import com.example.worldbeersapp.rest.models.MethodModel
import com.example.worldbeersapp.rest.models.VolumeModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun fromListString(stringList: List<String?>?): String? {
        if (stringList == null) return null
        val type = object : TypeToken<List<String?>?>() {}.type
        val json = Gson().toJson(stringList, type)
        return if (stringList.isEmpty()) null else json
    }

    @TypeConverter
    fun fromJsonListString(json: String?): List<String?>? {
        val gson = Gson()
        val type = object : TypeToken<List<String?>?>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromVolumeModel(model: VolumeModel?): String? {
        if (model == null) return null
        val type = object : TypeToken<VolumeModel?>() {}.type
        return Gson().toJson(model, type)
    }

    @TypeConverter
    fun fromJsonVolumeModel(json: String?): VolumeModel? {
        val gson = Gson()
        val type = object : TypeToken<VolumeModel?>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromBoilVolumeModel(model: BoilVolumeModel?): String? {
        if (model == null) return null
        val type = object : TypeToken<BoilVolumeModel?>() {}.type
        return Gson().toJson(model, type)
    }

    @TypeConverter
    fun fromJsonBoilVolumeModel(json: String?): BoilVolumeModel? {
        val gson = Gson()
        val type = object : TypeToken<BoilVolumeModel?>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromVolumeMethodModel(model: MethodModel?): String? {
        if (model == null) return null
        val type = object : TypeToken<MethodModel?>() {}.type
        return Gson().toJson(model, type)
    }

    @TypeConverter
    fun fromJsonMethodModel(json: String?): MethodModel? {
        val gson = Gson()
        val type = object : TypeToken<MethodModel?>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromIngredientsModel(model: IngredientsModel?): String? {
        if (model == null) return null
        val type = object : TypeToken<IngredientsModel?>() {}.type
        return Gson().toJson(model, type)
    }

    @TypeConverter
    fun fromJsonIngredientModel(json: String?): IngredientsModel? {
        val gson = Gson()
        val type = object : TypeToken<IngredientsModel?>() {}.type
        return gson.fromJson(json, type)
    }
}