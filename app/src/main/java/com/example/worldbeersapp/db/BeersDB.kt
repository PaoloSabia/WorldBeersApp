package com.example.worldbeersapp.db

import android.content.Context
import androidx.room.*
import androidx.room.TypeConverter
import com.example.worldbeersapp.db.daos.BeersDao
import com.example.worldbeersapp.rest.models.BeerModel
import java.util.concurrent.Executors

@Database(entities = [BeerModel::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class BeersDB : RoomDatabase() {

    abstract fun beersDao(): BeersDao

    companion object {

        private var db : BeersDB? = null

        //crea l'executor per eseguire operazioni in background asynchrone
        val databaseWriteExecutor = Executors.newFixedThreadPool(4)

        fun getDB(ctx: Context): BeersDB? {

            if(db != null){
                return db
            }

            return Room.databaseBuilder(ctx, BeersDB::class.java, "beers_db").build()
        }

    }
}