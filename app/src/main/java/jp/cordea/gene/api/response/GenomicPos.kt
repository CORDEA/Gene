package jp.cordea.gene.api.response

/**
 * Created by Yoshihiro Tanaka on 2017/05/26.
 */
data class GenomicPos(
        val chr: String,
        val end: Long,
        val start: Long,
        val strand: Int
)