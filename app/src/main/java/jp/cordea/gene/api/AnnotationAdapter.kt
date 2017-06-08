package jp.cordea.gene.api

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import jp.cordea.gene.api.response.Annotation
import jp.cordea.gene.api.response.AnnotationJson

/**
 * Created by Yoshihiro Tanaka on 2017/06/02.
 */
class AnnotationAdapter {

    @ToJson
    fun toJson(json: Annotation): AnnotationJson {
        return AnnotationJson(
                json.rgd,
                json.hgnc,
                json.mim,
                json.vega,
                json.id,
                json.score,
                json.accession,
                json.alias,
                json.entrezgene,
                json.generif,
                json.genomicPos,
                json.genomicPosMm9,
                json.genomicPosHg19,
                json.mapLocation,
                json.name,
                json.otherNames,
                json.summary,
                json.symbol,
                json.typeOfGene
        )
    }

    @FromJson
    fun fromJson(json: AnnotationJson): Annotation {
        val otherNames =
                if (json.otherNames is String) {
                    listOf(json.otherNames)
                } else if (json.otherNames is List<*>) {
                    json.otherNames.map { it as String }
                } else {
                    emptyList()
                }
        val alias =
                if (json.alias is String) {
                    listOf(json.alias)
                } else if (json.alias is List<*>) {
                    json.alias.map { it as String }
                } else {
                    emptyList()
                }
        return Annotation(
                json.rgd,
                json.hgnc,
                json.mim,
                json.vega,
                json.id,
                json.score,
                json.accession,
                alias,
                json.entrezgene,
                json.generif,
                json.genomicPos,
                json.genomicPosMm9,
                json.genomicPosHg19,
                json.mapLocation,
                json.name,
                otherNames,
                json.summary,
                json.symbol,
                json.typeOfGene
        )
    }

}
