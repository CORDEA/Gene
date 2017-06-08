package jp.cordea.gene.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.widget.AdapterView
import jp.cordea.gene.R
import jp.cordea.gene.activities.FavoritesActivity
import jp.cordea.gene.adapters.BindingListAdapter
import jp.cordea.gene.repositories.FavoritesRepository
import javax.inject.Inject

/**
 * Created by Yoshihiro Tanaka on 2017/06/04.
 */
class FavoritesViewModel @Inject constructor(private val repository: FavoritesRepository) : ViewModel() {

    val onItemClick = AdapterView.OnItemClickListener { _, _, position, _ ->
        repository.getId(position)?.let {
            selectedId.value = it
        }
    }

    fun getSelectedId(): LiveData<String> {
        return selectedId
    }

    private val selectedId = MutableLiveData<String>()

    fun getItems(): LiveData<List<FavoritesListItemViewModel>> {
        return Transformations.map(repository.items, {
            it.map {
                FavoritesListItemViewModel(it)
            }
        })
    }

    fun getAdapter(context: Context): BindingListAdapter<FavoritesListItemViewModel> {
        return BindingListAdapter(context, R.layout.list_item_favorites)
    }

    fun fetchFavorites() {
        repository.fetchFavorites()
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, FavoritesActivity::class.java)
        }
    }

}
