package com.example.worldbeersapp.rest.models

import android.os.Parcelable
import androidx.room.Embedded
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MethodModel(

    @SerializedName("mash_temp")
    @Expose
    @Embedded(prefix = "MT_")
    var mashTemp : List<MashTempModel>?,

    @SerializedName("fermentation")
    @Expose
    @Embedded(prefix = "F_")
    var fermentation : FermentationModel?,

    @SerializedName("twist")
    @Expose
    var twist : String?
) : Parcelable