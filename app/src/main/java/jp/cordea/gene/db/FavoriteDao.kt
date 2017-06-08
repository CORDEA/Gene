package jp.cordea.gene.db

import android.arch.persistence.room.*
import jp.cordea.gene.models.Favorite

/**
 * Created by Yoshihiro Tanaka on 2017/06/03.
 */
@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite")
    fun getAll(): List<Favorite>

    @Query("SELECT * FROM favorite WHERE id = :arg0 LIMIT 1")
    fun get(id: String): Favorite?

    @Insert
    fun add(favorite: Favorite)

    @Update
    fun update(favorite: Favorite)

    @Delete
    fun delete(favorite: Favorite)

}
