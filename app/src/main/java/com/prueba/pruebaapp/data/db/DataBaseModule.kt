package com.prueba.pruebaapp.data.db

import android.content.Context
import com.prueba.pruebaapp.data.db.entity.MovieEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {
    @Provides
    @Singleton
    fun provideRealDataBase(): Realm {
        val config = RealmConfiguration.create(schema = setOf(MovieEntity::class))
        return Realm.open(config)
    }
}