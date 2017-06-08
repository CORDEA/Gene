package jp.cordea.gene.api

import jp.cordea.gene.api.response.Annotation
import jp.cordea.gene.api.response.Genes
import retrofit2.Call

/**
 * Created by Yoshihiro Tanaka on 2017/05/26.
 */
class ApiClient : ApiClientBase() {

    private val service = retrofit.create(Api::class.java)

    override fun getGenes(query: String): Call<Genes> {
        return service.getGenes(query)
    }

    override fun getAnnotation(geneId: String): Call<Annotation> {
        return service.getAnnotation(geneId)
    }

}