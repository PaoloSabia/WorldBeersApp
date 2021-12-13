package com.example.worldbeersapp.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.worldbeersapp.rest.models.BeerModel

@Dao
interface BeersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBeers(beers: List<BeerModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBeer(beer: BeerModel)

    @Query("SELECT * FROM Beers")
    fun getBeers(): LiveData<List<BeerModel>>

    @Query("SELECT * FROM Beers where (:filter = '' or name like :filter) or (:filter = '' or description like :filter) ")
    fun getBeersByFilters(filter : String?): LiveData<List<BeerModel>>
}