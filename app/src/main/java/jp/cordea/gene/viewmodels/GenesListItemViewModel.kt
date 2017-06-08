package jp.cordea.gene.viewmodels

import android.arch.lifecycle.ViewModel
import jp.cordea.gene.api.response.Gene

/**
 * Created by Yoshihiro Tanaka on 2017/05/26.
 */
class GenesListItemViewModel(item: Gene) : ViewModel() {

    val id = item.id

    val title = item.symbol

    val description = item.name

}