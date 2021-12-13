package com.example.worldbeersapp.rest.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BoilVolumeModel(

    @SerializedName("value")
    @Expose
    var value : Float,

    @SerializedName("unit")
    @Expose
    var unit : String?
) : Parcelable