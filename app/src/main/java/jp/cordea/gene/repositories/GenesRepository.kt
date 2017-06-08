package jp.cordea.gene.repositories

import android.arch.lifecycle.MutableLiveData
import jp.cordea.gene.api.Api
import jp.cordea.gene.api.response.Gene
import jp.cordea.gene.api.response.Genes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Yoshihiro Tanaka on 2017/06/04.
 */
class GenesRepository @Inject constructor(
        private val apiClient: Api
) {

    val items = MutableLiveData<List<Gene>>()

    fun getId(position: Int): String? {
        return items.value?.get(position)?.id
    }

    fun fetchGenes(query: String) {
        apiClient.getGenes(query).enqueue(object : Callback<Genes> {
            override fun onFailure(call: Call<Genes>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<Genes>?, response: Response<Genes>?) {
                response?.let {
                    if (!it.isSuccessful) {
                        return
                    }
                    it.body()?.let {
                        items.value = it.hits
                    }
                }
            }
        })
    }
}