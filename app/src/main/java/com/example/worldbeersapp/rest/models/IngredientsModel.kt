package com.example.worldbeersapp.rest.models

import android.os.Parcelable
import androidx.room.Embedded
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class IngredientsModel(

    @SerializedName("malt")
    @Expose
    @Embedded(prefix = "M_")
    var malts : List<MaltModel>?,

    @SerializedName("hops")
    @Expose
    @Embedded(prefix = "H_")
    var hops : List<HopsModel>?,

    @SerializedName("yeast")
    @Expose
    var yeast : String?
) : Parcelable