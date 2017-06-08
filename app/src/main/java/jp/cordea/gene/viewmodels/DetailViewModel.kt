package jp.cordea.gene.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.view.View
import jp.cordea.gene.R
import jp.cordea.gene.activities.DetailActivity
import jp.cordea.gene.adapters.DetailListAdapter
import jp.cordea.gene.api.response.GeneRif
import jp.cordea.gene.repositories.DetailRepository
import javax.inject.Inject

/**
 * Created by Yoshihiro Tanaka on 2017/06/01.
 */
class DetailViewModel @Inject constructor(
        private val context: Context, private val repository: DetailRepository) : ViewModel() {

    fun getSelectedGeneRifs(): LiveData<List<GeneRif>> {
        return selectedGeneRifs
    }

    private val selectedGeneRifs = MutableLiveData<List<GeneRif>>()

    val onClick = View.OnClickListener {
        repository.storeFavorite()
    }

    fun getItems(): LiveData<List<DetailListBaseViewModel>> {
        return Transformations.map(repository.annotation, {
            genChildItems(it)
        })
    }

    fun getTitle(): LiveData<String> {
        return Transformations.map(repository.annotation, {
            it.symbol
        })
    }

    fun getSubtitle(): LiveData<String> {
        return Transformations.map(repository.annotation, {
            it.name
        })
    }

    fun getIsFavorite(): LiveData<Boolean> {
        return repository.isFavorite
    }

    fun extractId(intent: Intent) {
        id = intent.getStringExtra(IdKey)
    }

    private lateinit var id: String

    fun judgeIsFavorite() {
        repository.initFavoriteState(id)
    }

    fun getAdapter(context: Context): DetailListAdapter {
        return DetailListAdapter(context)
    }

    fun fetchAnnotation() {
        repository.fetchAnnotation(id)
    }

    private fun genChildItems(item: jp.cordea.gene.api.response.Annotation): List<DetailListBaseViewModel> {
        val items = arrayListOf<DetailListBaseViewModel>()
        item.summary?.let {
            items.add(DetailListHeaderViewModel(item))
        }
        item.generif?.notEmpty {
            items.add(DetailListButtonViewModel(
                    context.getString(R.string.title_detail_generif), it))
        }
        items.add(DetailListItemViewModel(
                String(),
                arrayListOf(
                        DetailInnerListItemViewModel(
                                context,
                                context.getString(R.string.title_detail_genomic_pos),
                                item.genomicPos)
                ).apply {
                    item.genomicPosHg19?.let {
                        add(DetailInnerListItemViewModel(
                                context,
                                context.getString(R.string.title_detail_genomic_pos_hg19),
                                it))
                    }
                    item.genomicPosMm9?.let {
                        add(DetailInnerListItemViewModel(
                                context,
                                context.getString(R.string.title_detail_genomic_pos_mm9),
                                it))
                    }
                })
        )
        item.alias?.notEmpty {
            items.add(DetailListItemViewModel(
                    context.getString(R.string.title_detail_alias),
                    it.map { DetailInnerListItemViewModel(it) }
            ))
        }
        item.otherNames.notEmpty {
            items.add(DetailListItemViewModel(
                    context.getString(R.string.title_detail_other_names),
                    it.map { DetailInnerListItemViewModel(it) }
            ))
        }
        return items
    }

    private inline fun <T> List<T>.notEmpty(block: (List<T>) -> Unit) {
        if (this.isNotEmpty()) {
            block(this)
        }
    }

    companion object {
        private val IdKey = "IdKey"

        fun createIntent(context: Context, id: String): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(IdKey, id)
            }
        }
    }
}
