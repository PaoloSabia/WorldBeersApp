package com.example.worldbeersapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.worldbeersapp.R

class FoodPairingAdapter(private val foods: List<String>) : RecyclerView.Adapter<FoodPairingAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val food: TextView = view.findViewById(R.id.food)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_pairing_adapter_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val foodPairingValue : String = foods[position]

        holder.food.text = foodPairingValue
    }

    override fun getItemCount(): Int {
        return foods.size
    }


}