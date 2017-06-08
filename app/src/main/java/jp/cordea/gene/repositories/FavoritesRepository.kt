package jp.cordea.gene.repositories

import android.arch.lifecycle.MutableLiveData
import jp.cordea.gene.db.FavoriteDao
import jp.cordea.gene.models.Favorite
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by Yoshihiro Tanaka on 2017/06/04.
 */
class FavoritesRepository @Inject constructor(
        private val favoriteDao: FavoriteDao,
        private val context: CoroutineContext
) {

    val items = MutableLiveData<List<Favorite>>()

    fun getId(position: Int): String? {
        return items.value?.get(position)?.id
    }

    fun fetchFavorites() {
        launch(context) {
            favoriteDao.getAll().let {
                launch(UI) {
                    items.value = it
                }
            }
        }
    }
}