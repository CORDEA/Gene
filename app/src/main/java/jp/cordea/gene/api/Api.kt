package jp.cordea.gene.api

import jp.cordea.gene.api.response.Annotation
import jp.cordea.gene.api.response.Genes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Yoshihiro Tanaka on 2017/05/26.
 */
interface Api {

    @GET("v3/query")
    fun getGenes(@Query("q") query: String): Call<Genes>

    @GET("v3/gene/{geneId}")
    fun getAnnotation(@Path("geneId") geneId: String): Call<Annotation>

}