package com.example.helderrocha.marvelapplication.model

data class SuperHero(
        val name: String,
        val thumbnail: Thumbnail,
        val isAvenger: Boolean,
        val description: String,
        val comics: Comics)