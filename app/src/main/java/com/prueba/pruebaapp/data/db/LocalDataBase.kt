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
class LocalDataBase @Inject constructor(): LocalDataBaseImpl{
    override suspend fun saveMovie(movieEntity: List<MovieEntity>) {
        val config = RealmConfiguration.create(schema = setOf(MovieEntity::class))
        val realm: Realm = Realm.open(config)
        realm.writeBlocking  {
            for (entity in movieEntity) {
                copyToRealm(entity)
            }
        }
        realm.close()
    }

    override suspend fun getMovie(id: Int): MovieEntity {
        val config = RealmConfiguration.create(schema = setOf(MovieEntity::class))
        val realm: Realm = Realm.open(config)
        val incompleteItems: RealmResults<MovieEntity> =
            realm.query<MovieEntity>("id == $id")
                .find()
        return incompleteItems.first()
    }
}