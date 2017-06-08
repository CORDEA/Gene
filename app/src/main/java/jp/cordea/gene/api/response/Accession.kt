package jp.cordea.gene.api.response

/**
 * Created by Yoshihiro Tanaka on 2017/05/26.
 */
data class Accession(
        val genomic: List<String>?,
        val protein: List<String>?,
        val rna: List<String>?
)
