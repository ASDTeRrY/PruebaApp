package com.prueba.pruebaapp.data.db.entity

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class MovieEntity(): RealmObject {
    @PrimaryKey
    var id: Int = 0
    var posterPath: String = ""
    var title: String = ""
    var voteAverage: Double = 0.0
    var releaseDate: String = ""
    var overview: String = ""

    constructor(id: Int, posterPath: String, title: String, voteAverage: Double, releaseDate: String, overview: String): this(){
        this.id = id
        this.posterPath = posterPath
        this.title = title
        this.voteAverage = voteAverage
        this.releaseDate = releaseDate
        this.overview = overview
    }
}

