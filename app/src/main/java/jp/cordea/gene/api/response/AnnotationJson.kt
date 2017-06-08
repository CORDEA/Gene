package jp.cordea.gene.api.response

import com.squareup.moshi.Json

/**
 * Created by Yoshihiro Tanaka on 2017/05/26.
 */
data class AnnotationJson(
        val rgd: String?,
        val hgnc: String?,
        val mim: String?,
        val vega: String?,
        @Json(name = "_id") val id: String,
        @Json(name = "_score") val score: Float,
        val accession: Accession,
        val alias: Any?,
        val entrezgene: Int,
        val generif: List<GeneRif>?,
        @Json(name = "genomic_pos") val genomicPos: GenomicPos,
        @Json(name = "genomic_pos_mm9") val genomicPosMm9: GenomicPos?,
        @Json(name = "genomic_pos_hg19") val genomicPosHg19: GenomicPos?,
        @Json(name = "map_location") val mapLocation: String,
        val name: String,
        @Json(name = "other_names") val otherNames: Any,
        val summary: String?,
        val symbol: String,
        @Json(name = "type_of_gene") val typeOfGene: String
)