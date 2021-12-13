package com.example.worldbeersapp.rest.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Beers")
data class BeerModel(

    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    @Expose
    var id : Int,

    @SerializedName("name")
    @Expose
    var name : String?,

    @SerializedName("tagline")
    @Expose
    var tagline : String?,

    @SerializedName("first_brewed")
    @Expose
    var firstBrewed : String?,

    @SerializedName("description")
    @Expose
    var description : String?,

    @SerializedName("image_url")
    @Expose
    var imageUrl : String?,

    @SerializedName("abv")
    @Expose
    var abv : Float,

    @SerializedName("ibu")
    @Expose
    var ibu : Float,

    @SerializedName("target_fg")
    @Expose
    var targetFg : Float,

    @SerializedName("target_og")
    @Expose
    var targetOg : Float,

    @SerializedName("ebc")
    @Expose
    var ebc : Float,

    @SerializedName("srm")
    @Expose
    var srm : Float,

    @SerializedName("ph")
    @Expose
    var ph : Float,

    @SerializedName("attenuation_level")
    @Expose
    var attenuationLevel : Float,

    @SerializedName("volume")
    @Expose
    var volume : VolumeModel?,

    @SerializedName("boil_volume")
    @Expose
    var boilVolume : BoilVolumeModel?,

    @SerializedName("method")
    @Expose
    var method : MethodModel?,

    @SerializedName("ingredients")
    @Expose
    var ingredients : IngredientsModel?,

    @SerializedName("food_pairing")
    @Expose
    var foodPairing : List<String>?,

    @SerializedName("brewers_tips")
    @Expose
    var brewersTips : String?
) : Parcelable