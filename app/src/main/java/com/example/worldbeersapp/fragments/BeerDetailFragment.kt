package com.example.worldbeersapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.worldbeersapp.R
import com.example.worldbeersapp.adapters.FoodPairingAdapter
import com.example.worldbeersapp.rest.models.BeerModel

private const val ARG_PARAM1 = "model"

class BeerDetailFragment : Fragment() {

    private var beer: BeerModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            beer = it.getParcelable(ARG_PARAM1)
        }

        //dismiss keyboard
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity?.window?.decorView?.rootView?.windowToken, 0)

        //modifica toolbar
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as? AppCompatActivity)?.supportActionBar?.title = beer?.name

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        menu.clear()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == android.R.id.home) {

            activity?.supportFragmentManager?.popBackStack()

            return true
        }

        return false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beer_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val picture : ImageView = view.findViewById(R.id.beerImg)
        context?.let { Glide.with(it).load(beer?.imageUrl).into(picture) }

        val brewedDate : TextView = view.findViewById(R.id.beerBrewedValue)
        brewedDate.text = beer?.firstBrewed

        val recView : RecyclerView = view.findViewById(R.id.recyclerView)
        recView.setHasFixedSize(true)
        val layoutManagerFixed: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recView.layoutManager = layoutManagerFixed
        val adapter = beer?.foodPairing?.let { FoodPairingAdapter(it) }
        recView.adapter = adapter

        val tips : TextView = view.findViewById(R.id.tipsValue)
        tips.text = beer?.brewersTips
    }

    override fun onDetach() {
        super.onDetach()

        //reset toolbar
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as? AppCompatActivity)?.supportActionBar?.title = activity?.title
    }

    companion object {

        @JvmStatic
        fun newInstance(beer: BeerModel) = BeerDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, beer)
                }
            }
    }
}