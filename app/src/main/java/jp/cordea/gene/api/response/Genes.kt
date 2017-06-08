package jp.cordea.gene.api.response

import com.squareup.moshi.Json

/**
 * Created by Yoshihiro Tanaka on 2017/05/26.
 */
data class Genes(
        @Json(name = "max_score") val maxScore: Float,
        val took: Int,
        val total: Int,
        val hits: List<Gene>
)