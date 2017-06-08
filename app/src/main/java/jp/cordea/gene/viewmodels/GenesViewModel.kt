package jp.cordea.gene.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.widget.AdapterView
import jp.cordea.gene.R
import jp.cordea.gene.activities.GenesActivity
import jp.cordea.gene.adapters.BindingListAdapter
import jp.cordea.gene.repositories.GenesRepository
import javax.inject.Inject

/**
 * Created by Yoshihiro Tanaka on 2017/06/01.
 */
class GenesViewModel @Inject constructor(private val repository: GenesRepository) : ViewModel() {

    val onItemClick = AdapterView.OnItemClickListener { _, _, position, _ ->
        repository.getId(position)?.let {
            selectedId.value = it
        }
    }

    private lateinit var query: String

    fun getItems(): LiveData<List<GenesListItemViewModel>> {
        return Transformations.map(repository.items, {
            it.map {
                GenesListItemViewModel(it)
            }
        })
    }

    fun getSelectedId(): LiveData<String> {
        return selectedId
    }

    private val selectedId = MutableLiveData<String>()

    fun extractQuery(intent: Intent) {
        query = intent.getStringExtra(QueryKey)
    }

    fun getAdapter(context: Context): BindingListAdapter<GenesListItemViewModel> {
        return BindingListAdapter(context, R.layout.list_item_genes, arrayListOf())
    }

    fun fetchGenes() {
        return repository.fetchGenes(query)
    }

    companion object {
        private val QueryKey = "QueryKey"

        fun createIntent(context: Context, query: String): Intent {
            return Intent(context, GenesActivity::class.java).apply {
                putExtra(QueryKey, query)
            }
        }
    }
}
