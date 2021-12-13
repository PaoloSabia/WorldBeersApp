package com.example.worldbeersapp.repos

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.worldbeersapp.MyApplication
import com.example.worldbeersapp.db.BeersDB
import com.example.worldbeersapp.rest.models.BeerModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object BeerRepo {

    fun getBeersCall(ctx : Context){

        //recupero il client di retrofit per l'invio della request
        val subscribe = MyApplication.getRestClient().getBeers().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                { beerList ->

                    //salva le birre nel db
                    val db : BeersDB? = BeersDB.getDB(ctx)
                    BeersDB.databaseWriteExecutor.execute {
                        db?.beersDao()?.insertBeers(beerList)
                    }

                }, { error ->
                    //gestire http exceptions
                })
    }

    /**
     * restituisce tutte le birre nel db
     */
    fun getBeers(ctx : Context) : LiveData<List<BeerModel>>? {
        return BeersDB.getDB(ctx)?.beersDao()?.getBeers()
    }

    /**
     * restituisce solo le birre che soddisfano il filtro
     */
    fun getBeersByFilter(ctx : Context, filter : String) : LiveData<List<BeerModel>>? {
        return BeersDB.getDB(ctx)?.beersDao()?.getBeersByFilters(filter)
    }
}