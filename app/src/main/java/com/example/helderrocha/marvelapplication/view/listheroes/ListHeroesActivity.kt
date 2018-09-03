package com.example.helderrocha.marvelapplication.view.listheroes

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.helderrocha.marvelapplication.R
import com.example.helderrocha.marvelapplication.model.SuperHero
import com.example.helderrocha.marvelapplication.view.listheroes.adapter.HeroAdapter
import com.example.helderrocha.marvelapplication.view_model.CharactersViewModel
import com.example.helderrocha.marvelapplication.view_model.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.list_heroes_activity.*
import javax.inject.Inject

class ListHeroesActivity : AppCompatActivity() {
    @Inject
    lateinit var charactersVMFactory: ViewModelFactory<CharactersViewModel>

    private val charactersViewModel by lazy {
        ViewModelProviders.of(this, charactersVMFactory)[CharactersViewModel::class.java]
    }
    protected val ItemsObserver = Observer<List<SuperHero>>(::onItemsFetched)

    var listHeroesView: MutableList<SuperHero> = mutableListOf()
    private lateinit var adapter: HeroAdapter
    var layoutManager = LinearLayoutManager(this)

    var totalItemCount: Int = 0
    var visibleItemCount: Int = 0
    var pastVisibleItemCount: Int = 0
    var loading: Boolean = false
    var page: Long = 1L

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_heroes_activity)

        charactersViewModel.data.observe(this, ItemsObserver)
        charactersViewModel.getMoreMovies(page)
    }

    private fun onItemsFetched(list: List<SuperHero>?) {
        if (list != null) {
            loading = true
            setUpdateAdapter(list)
        }

    }

    private fun setUpdateAdapter(heroes: List<SuperHero>){
        recyclerHeroes.layoutManager = layoutManager
        recyclerHeroes.setHasFixedSize(true)
        if(listHeroesView.size == 0){
            listHeroesView = heroes as MutableList<SuperHero>
            adapter = HeroAdapter(listHeroesView, { superHero: SuperHero -> partItemClicked(superHero) } )
            recyclerHeroes.adapter = adapter
            progressBar.visibility = View.GONE

        }else{
            var currentPosition =(recyclerHeroes.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            listHeroesView.addAll(heroes)
            adapter.notifyDataSetChanged()
            recyclerHeroes.adapter!!.notifyDataSetChanged()
            recyclerHeroes.scrollToPosition(currentPosition)
            progressBar.visibility = View.GONE
        }

        recyclerHeroes.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if(dy > 0) {
                    visibleItemCount = layoutManager.childCount
                    totalItemCount = layoutManager.itemCount
                    pastVisibleItemCount =(recyclerView!!.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if(loading){
                        if((visibleItemCount+ pastVisibleItemCount) >= totalItemCount) {
                            progressBar.visibility = View.VISIBLE
                            loading = false
                            page++
                            var offSet: Long
                            offSet = page++
                            charactersViewModel.getMoreMovies(offSet*7L)
                        }
                    }
                }

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerHeroes, newState)
            }
        })
    }

    private fun partItemClicked(superHero : SuperHero) {
        //TO DO
    }

}
