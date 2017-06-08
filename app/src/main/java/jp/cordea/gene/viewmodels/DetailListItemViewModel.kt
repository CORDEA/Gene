package jp.cordea.gene.viewmodels

import android.content.Context
import android.view.View
import jp.cordea.gene.R
import jp.cordea.gene.adapters.BindingListAdapter

/**
 * Created by Yoshihiro Tanaka on 2017/06/01.
 */
class DetailListItemViewModel(val title: String,
                              private val items: List<DetailInnerListItemViewModel>) : DetailListBaseViewModel() {

    val visibility: Int = if (title.isBlank()) View.GONE else View.VISIBLE

    fun getAdapter(context: Context): BindingListAdapter<DetailInnerListItemViewModel> {
        if (title.isBlank()) {
            return BindingListAdapter(context, R.layout.list_item_detail_inner_ellipsize, items)
        }
        return BindingListAdapter(context, R.layout.list_item_detail_inner_oneline, items)
    }
}
