package com.example.worldbeersapp.rest

import com.example.worldbeersapp.rest.models.BeerModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RestServices {

    /**
     * chiamata per ottenere la configurazione dei log
     *
     * @param beerName: nome della birra da ricercare
     * @param description: tipologia della birra da ricercare
     *
     * --edit per permette la funzionalità offline--
     *
     * @return la response con la lista delle birre
     */
    @GET("beers")
    fun getBeers(/*@Query("beer_name") beerName : String?, @Query("yeast") description : String?*/): Single<List<BeerModel>>
}