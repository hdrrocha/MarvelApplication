package com.example.helderrocha.marvelapplication.view.listheroes.hero
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bumptech.glide.Glide
import com.example.helderrocha.marvelapplication.R
import com.example.helderrocha.marvelapplication.model.SuperHero
import com.example.helderrocha.marvelapplication.view.listheroes.listhero.adapter.HeroAdapter
import com.example.helderrocha.marvelapplication.view_model.CharactersIdViewModel
import com.example.helderrocha.marvelapplication.view_model.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.hero_activity.*
import kotlinx.android.synthetic.main.item_hero.view.*
import javax.inject.Inject

class HeroActivity : AppCompatActivity() {
    @Inject
    lateinit var charactersIdVMFactory: ViewModelFactory<CharactersIdViewModel>

    private val charactersViewModel by lazy {
        ViewModelProviders.of(this, charactersIdVMFactory)[CharactersIdViewModel::class.java]
    }
    protected val ItemsObserver = Observer<List<SuperHero>>(::onItemsFetched)

    var listHeroesView: MutableList<SuperHero> = mutableListOf()
    private lateinit var adapter: HeroAdapter
    var layoutManager = LinearLayoutManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hero_activity)

        val Item = intent.extras!!.getString("superHero")
        var idSuperHero: Long
        idSuperHero = Item.toLong()

        if(idSuperHero != null) {
            charactersViewModel.data.observe(this, ItemsObserver)
            charactersViewModel.getHero(idSuperHero)
        }else{
            textDescription.text = "Ainda não existem informações cadastradas \n para este herói! =("
            progressBarHero.visibility = View.GONE
        }
    }

    private fun onItemsFetched(list: List<SuperHero>?) {
        if (list != null) {
            var superHero = list.first()
            Glide.with(this)
                    .load("${list[0]?.thumbnail?.path}/standard_medium.${list[0]?.thumbnail?.extension}")
                    .into(imageHero.imageHero)
            textNameHero.text = list[0].name
            if(list[0].description != ""){
                textDescription.text = list[0].description
            }else {
                textDescription.text = "Ainda não existem informações cadastradas \n para este herói! =("
            }
            progressBarHero.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
