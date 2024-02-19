package com.prueba.pruebaapp.data.db

import com.prueba.pruebaapp.data.db.entity.MovieEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class LocalDataBase @Inject constructor(private val realm: Realm) : LocalDataBaseImpl {
    override suspend fun saveMovie(movieEntity: List<MovieEntity>) {
        realm.writeBlocking {
            for (entity in movieEntity) {
                copyToRealm(entity)
            }
        }
    }

    override suspend fun getMovie(id: Int): MovieEntity {
        return realm.query<MovieEntity>("id == $id")
            .find()
            .first()
    }
}