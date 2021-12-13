package com.example.worldbeersapp.rest.models

import android.os.Parcelable
import androidx.room.Embedded
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FermentationModel(

    @SerializedName("temp")
    @Expose
    @Embedded(prefix = "T_")
    var temp : TempModel?
) : Parcelable