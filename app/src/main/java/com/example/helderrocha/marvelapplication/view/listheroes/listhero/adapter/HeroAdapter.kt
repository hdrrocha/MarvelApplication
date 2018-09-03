package com.example.helderrocha.marvelapplication.view.listheroes.listhero.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.helderrocha.marvelapplication.R
import com.example.helderrocha.marvelapplication.model.SuperHero
import kotlinx.android.synthetic.main.item_hero.view.*

class HeroAdapter(var superHeroes: List<SuperHero>, val clickListener: ((SuperHero) -> Unit)?) : RecyclerView.Adapter<HeroAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(superHero: SuperHero, clickListener: ((SuperHero) -> Unit)?) {
            itemView.textNameHero.text = superHero.name
            itemView.textAvailable.text = superHero.comics.available.toString()
            itemView.setOnClickListener {
                if (clickListener != null) {
                    clickListener(superHero)
                }
            }
            Glide.with(itemView)
                    .load("${superHero?.thumbnail?.path}/standard_medium.${superHero?.thumbnail?.extension}")
                    .into(itemView.imageHero)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hero, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = superHeroes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        (holder as ViewHolder).bind(superHeroes[position], clickListener)
    }
}
