package com.example.worldbeersapp.rest.models

import android.os.Parcelable
import androidx.room.Embedded
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HopsModel(

    @SerializedName("name")
    @Expose
    var name : String?,

    @SerializedName("amount")
    @Expose
    @Embedded(prefix = "T")
    var amount : TempModel?,

    @SerializedName("add")
    @Expose
    var add : String?,

    @SerializedName("attribute")
    @Expose
    var attribute : String?
) : Parcelable