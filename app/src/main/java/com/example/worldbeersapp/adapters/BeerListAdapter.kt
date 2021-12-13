package com.example.worldbeersapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.worldbeersapp.R
import com.example.worldbeersapp.rest.models.BeerModel
import com.google.android.material.card.MaterialCardView

class BeerListAdapter(private val beersList: List<BeerModel>, private val callbackInterface:CallbackInterface) : RecyclerView.Adapter<BeerListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val beerCard : MaterialCardView = view.findViewById(R.id.beerCard)
        val beerImg : ImageView = view.findViewById(R.id.beerImg)
        val beerName: TextView = view.findViewById(R.id.beerNameValue)
        val beerAbv: TextView = view.findViewById(R.id.abvValue)
        val beerIbu: TextView = view.findViewById(R.id.ibuValue)
        val beerDescription: TextView = view.findViewById(R.id.descritionValue)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.beers_adapter_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val beer : BeerModel = beersList[position]

        Glide.with(holder.beerImg.context).load(beer.imageUrl).into(holder.beerImg)

        holder.beerName.text = beer.name

        if(beer.abv > 0){
            holder.beerAbv.text = beer.abv.toString()
        }
        else{
            holder.beerAbv.setText(R.string.beer_value_na)
        }

        if(beer.ibu > 0){
            holder.beerIbu.text = beer.ibu.toString()
        }
        else{
            holder.beerIbu.setText(R.string.beer_value_na)
        }

        holder.beerDescription.text = beer.description

        holder.beerCard.setOnClickListener{
            callbackInterface.passResultCallback(beer)
        }
    }

    override fun getItemCount(): Int {
       return beersList.size
    }

    interface CallbackInterface {
        fun passResultCallback(beer: BeerModel)
    }
}