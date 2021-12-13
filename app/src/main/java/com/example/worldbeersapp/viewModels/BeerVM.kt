package com.example.worldbeersapp.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.worldbeersapp.repos.BeerRepo
import com.example.worldbeersapp.rest.models.BeerModel

object BeerVM : ViewModel() {

    /**
     * chiamata rest
     */
    fun callBeers(ctx : Context){
        BeerRepo.getBeersCall(ctx)
    }

    /**
     * query sul db
     */
    fun getBeersByFilter(ctx : Context, filter : String) : LiveData<List<BeerModel>>? {

        if(filter.isEmpty()){
            return BeerRepo.getBeers(ctx)
        }
        return BeerRepo.getBeersByFilter(ctx, filter)
    }
}