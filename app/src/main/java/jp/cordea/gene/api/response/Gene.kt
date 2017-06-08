package jp.cordea.gene.api.response

import com.squareup.moshi.Json

/**
 * Created by Yoshihiro Tanaka on 2017/05/26.
 */
data class Gene(
        @Json(name = "_id") val id: String,
        @Json(name = "_score") val score: Float,
        val entrezgene: Int,
        val name: String,
        val symbol: String,
        val taxId: Int
)
