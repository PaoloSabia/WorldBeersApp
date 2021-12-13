package com.example.worldbeersapp.activities

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.View
import android.view.WindowManager
import android.widget.RelativeLayout
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.worldbeersapp.R
import com.example.worldbeersapp.adapters.BeerListAdapter
import com.example.worldbeersapp.fragments.BeerDetailFragment
import com.example.worldbeersapp.rest.models.BeerModel
import com.example.worldbeersapp.viewModels.BeerVM
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity(), BeerListAdapter.CallbackInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //spinner di caricamento
        showLoadingDialog(true, getString(R.string.list_title_loading_beers))

        //chiamata all'endpoint
        BeerVM.callBeers(this)

        //recycler per visualizzare le cards
        val recView : RecyclerView = findViewById(R.id.recyclerView)
        recView.setHasFixedSize(true)
        val layoutManagerFixed: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recView.layoutManager = layoutManagerFixed

        //osservo la lista delle birre
        findBeers("")
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        //searchview
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        //comportamento della query
        val queryTextListener: SearchView.OnQueryTextListener = object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {

                    showLoadingDialog(true, getString(R.string.list_title_loading_beers))

                    findBeers("%$query%")

                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {

                    if(newText.isEmpty()){
                        findBeers("")
                    }

                    return true
                }
            }

        (menu.findItem(R.id.search).actionView as SearchView).setOnQueryTextListener(queryTextListener)

        return true
    }

    private fun findBeers(query: String) {

        val recView : RecyclerView = findViewById(R.id.recyclerView)

        //osservo le liste delle birre
        BeerVM.getBeersByFilter(this, query)?.observe(this, { beers ->

            showLoadingDialog(false, "")

            //popolo l'adapter e mostro le cards
            val adapter = BeerListAdapter(beers, this)
            recView.adapter = adapter

        })
    }

    fun showLoadingDialog(showLoadingProgress: Boolean, loadingTxt: String) {

        val progressContainer = findViewById<RelativeLayout>(R.id.progressContainer)
        val loadingMessage = findViewById<MaterialTextView>(R.id.loadingMessage)

        loadingMessage.text = if (loadingTxt.isNotEmpty()) loadingTxt else loadingMessage.text

        if (showLoadingProgress) {
            //mostro lo spinner e disabilito le interazioni utente
            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )

            progressContainer.visibility = View.VISIBLE
        } else {
            //nascondo lo spinner dopo 0.5 secondi e riabilito le interazioni dell'utente
            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({
                progressContainer.visibility = View.GONE
                window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            }, 500)
        }
    }

    /**
     * interfaccia che restituisce il model della birra selezionata nell'adapter
     */
    override fun passResultCallback(beer: BeerModel) {

        //avvio fragment di dettaglio
        val frag : BeerDetailFragment = BeerDetailFragment.newInstance(beer)

                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fragContainer, frag)
                    transaction.addToBackStack(frag.javaClass.name)
                    transaction.commit()
    }
}