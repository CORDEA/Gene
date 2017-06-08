package jp.cordea.gene.api.response

import org.parceler.Parcel
import org.parceler.ParcelConstructor
import org.parceler.ParcelProperty

/**
 * Created by Yoshihiro Tanaka on 2017/05/26.
 */
@Parcel(Parcel.Serialization.BEAN)
data class GeneRif @ParcelConstructor constructor(
        @ParcelProperty("pubmed") val pubmed: Long,
        @ParcelProperty("text") val text: String
)