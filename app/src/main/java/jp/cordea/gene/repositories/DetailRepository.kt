package jp.cordea.gene.repositories

import android.arch.lifecycle.MutableLiveData
import jp.cordea.gene.api.Api
import jp.cordea.gene.api.response.Annotation
import jp.cordea.gene.db.AppDatabase
import jp.cordea.gene.db.FavoriteDao
import jp.cordea.gene.models.Favorite
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by Yoshihiro Tanaka on 2017/06/04.
 */
class DetailRepository @Inject constructor(
        private val apiClient: Api,
        private val appDatabase: AppDatabase,
        private val favoriteDao: FavoriteDao,
        private val context: CoroutineContext
) {

    private var favorite: Favorite? = null
        private set

    val annotation = MutableLiveData<Annotation>()

    val isFavorite = MutableLiveData<Boolean>()

    fun storeFavorite() {
        favorite?.let { fav ->
            isFavorite.value?.let {
                launch(context) {
                    appDatabase.beginTransaction()
                    try {
                        if (it) {
                            favoriteDao.delete(fav)
                        } else {
                            favoriteDao.add(fav)
                        }
                        appDatabase.setTransactionSuccessful()
                    } finally {
                        appDatabase.endTransaction()
                    }
                }
                isFavorite.value = !it
            }
        }
    }

    fun initFavoriteState(id: String) {
        launch(context) {
            val isFav = favoriteDao.get(id) != null
            launch(UI) {
                isFavorite.value = isFav
            }
        }
    }

    fun fetchAnnotation(id: String) {
        apiClient.getAnnotation(id).enqueue(object : Callback<Annotation> {
            override fun onFailure(call: Call<Annotation>?, t: Throwable?) {
                t?.printStackTrace()
            }

            override fun onResponse(call: Call<Annotation>?, response: Response<Annotation>?) {
                response?.let {
                    if (!it.isSuccessful) {
                        return
                    }
                    it.body()?.let {
                        favorite = Favorite(id, it.name, it.symbol)
                        annotation.value = it
                    }
                }
            }
        })
    }
}