package com.example.worldbeersapp.rest.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MashTempModel(

    @SerializedName("temp")
    @Expose
    var temp : TempModel?,

    @SerializedName("duration")
    @Expose
    var duration : String?
    ) : Parcelable