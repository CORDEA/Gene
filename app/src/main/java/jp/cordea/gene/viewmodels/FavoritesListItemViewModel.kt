package jp.cordea.gene.viewmodels

import android.arch.lifecycle.ViewModel
import jp.cordea.gene.models.Favorite

/**
 * Created by Yoshihiro Tanaka on 2017/06/04.
 */
class FavoritesListItemViewModel(item: Favorite) : ViewModel() {

    val id = item.id

    val title = item.symbol

    val description = item.name

}
