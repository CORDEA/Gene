package jp.cordea.gene.viewmodels

import android.content.Context
import jp.cordea.gene.R
import jp.cordea.gene.api.response.GenomicPos

/**
 * Created by Yoshihiro Tanaka on 2017/06/02.
 */
class DetailInnerListItemViewModel {

    val title: String

    val description: String

    constructor(context: Context, title: String, item: GenomicPos) {
        this.title = title
        description = context
                .getString(R.string.detail_inner_list_item_description_format)
                .format(item.chr, item.start, item.end)
    }

    constructor(title: String) {
        this.title = title
        description = ""
    }
}
