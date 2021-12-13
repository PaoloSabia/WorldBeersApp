package com.example.worldbeersapp

import android.app.Application
import com.example.worldbeersapp.db.BeersDB
import com.example.worldbeersapp.rest.RestServices
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //init del db
        BeersDB.getDB(this)
    }

    companion object {

        private var retrofit: RestServices? = null

        fun getRestClient() : RestServices {

            if(retrofit != null){
                return retrofit as RestServices
            }

            //aggiunto componente per intercettare le request e response
            val logging = HttpLoggingInterceptor()

            //imposto il livello di log per il debug
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            //client per le chiamate http
            val clientHttp: OkHttpClient = OkHttpClient.Builder().addInterceptor(logging)
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build()

            //builder per il parse dei body
            val gson = GsonBuilder()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .excludeFieldsWithoutExposeAnnotation()
                .create()

            //creazione client di retrofit per effettuare le chiamate REST
            retrofit = Retrofit.Builder()
                .baseUrl("https://api.punkapi.com/v2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(clientHttp)
                .build()
                .create(RestServices::class.java)

            return retrofit as RestServices
        }

    }
}