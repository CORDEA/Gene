package jp.cordea.gene.api

import android.content.Context
import jp.cordea.gene.R
import jp.cordea.gene.api.response.Annotation
import jp.cordea.gene.api.response.Genes
import retrofit2.Call
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Created by Yoshihiro Tanaka on 2017/06/06.
 */
class MockApiClient(private val ctx: Context) : ApiClientBase() {

    private val mockRetrofit = MockRetrofit
            .Builder(retrofit)
            .networkBehavior(NetworkBehavior.create())
            .build()

    private val service = mockRetrofit.create(Api::class.java)

    private fun readRawFile(id: Int): String {
        val reader = BufferedReader(InputStreamReader(ctx.resources.openRawResource(id)))
        return reader.readText()
    }

    override fun getGenes(query: String): Call<Genes> {
        val text = readRawFile(R.raw.response_query)
        val adapter = moshi.adapter(Genes::class.java)
        return service.returningResponse(adapter.fromJson(text)).getGenes(query)
    }

    override fun getAnnotation(geneId: String): Call<Annotation> {
        val text = readRawFile(R.raw.response_gene)
        val adapter = moshi.adapter(Annotation::class.java)
        return service.returningResponse(adapter.fromJson(text)).getAnnotation(geneId)
    }
}
