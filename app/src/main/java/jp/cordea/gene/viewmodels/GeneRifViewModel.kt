package jp.cordea.gene.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import jp.cordea.gene.R
import jp.cordea.gene.activities.GeneRifActivity
import jp.cordea.gene.adapters.BindingListAdapter
import jp.cordea.gene.api.response.GeneRif
import org.parceler.Parcels
import javax.inject.Inject

/**
 * Created by Yoshihiro Tanaka on 2017/06/02.
 */
class GeneRifViewModel @Inject constructor() : ViewModel() {

    fun getItems(): LiveData<List<GeneRifListItemViewModel>> {
        return items
    }

    private val items = MutableLiveData<List<GeneRifListItemViewModel>>()

    fun extractGenerifs(intent: Intent) {
        val generifs = Parcels.unwrap<List<GeneRif>>(intent.getParcelableExtra(GeneRifKey))
        items.value = generifs.map { GeneRifListItemViewModel(it) }
    }

    fun getAdapter(context: Context): BindingListAdapter<GeneRifListItemViewModel> {
        return BindingListAdapter(context, R.layout.list_item_gene_rif, emptyList())
    }

    companion object {
        private val GeneRifKey = "GeneRifKey"

        fun createIntent(context: Context, generifs: List<GeneRif>): Intent {
            return Intent(context, GeneRifActivity::class.java).apply {
                putExtra(GeneRifKey, Parcels.wrap(generifs))
            }
        }
    }

}
