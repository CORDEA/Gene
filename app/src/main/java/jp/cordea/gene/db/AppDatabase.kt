package jp.cordea.gene.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import jp.cordea.gene.models.Favorite

/**
 * Created by Yoshihiro Tanaka on 2017/06/03.
 */
@Database(entities = arrayOf(Favorite::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

}
