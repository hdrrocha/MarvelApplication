package com.example.helderrocha.marvelapplication.model

data class SuperHero(
        val id: Long,
        val name: String,
        val thumbnail: Thumbnail,
        val description: String,
        val comics: Comics)