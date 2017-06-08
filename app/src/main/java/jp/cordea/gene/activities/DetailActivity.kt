package jp.cordea.gene.activities

import android.arch.lifecycle.*
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import jp.cordea.gene.App
import jp.cordea.gene.ListItemDecoration
import jp.cordea.gene.R
import jp.cordea.gene.api.response.GeneRif
import jp.cordea.gene.databinding.ActivityDetailBinding
import jp.cordea.gene.viewmodels.DetailListBaseViewModel
import jp.cordea.gene.viewmodels.DetailViewModel
import jp.cordea.gene.viewmodels.GeneRifViewModel
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), LifecycleRegistryOwner {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProviders.of(this, factory).get(DetailViewModel::class.java)
    }

    private val adapter by lazy {
        viewModel.getAdapter(this)
    }

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
    }

    private val lifecycle = LifecycleRegistry(this)

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycle
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.component.inject(this)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.vm = viewModel

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(ListItemDecoration(this))

        binding.recyclerView.adapter = adapter
        viewModel.extractId(intent)
        viewModel.judgeIsFavorite()

        subscribe()

        viewModel.fetchAnnotation()
    }

    private fun subscribe() {
        viewModel.getItems().observe(this, Observer<List<DetailListBaseViewModel>> {
            it?.let {
                adapter.refreshItems(it)
            }
        })
        viewModel.getTitle().observe(this, Observer<String> {
            it?.let {
                binding.toolbarLayout.title = it
            }
        })
        viewModel.getSubtitle().observe(this, Observer<String> {
            it?.let {
                binding.toolbarLayout.subtitle = it
            }
        })
        viewModel.getSelectedGeneRifs().observe(this, Observer<List<GeneRif>> {
            it?.let {
                startActivity(GeneRifViewModel.createIntent(this, it))
            }
        })
        viewModel.getIsFavorite().observe(this, Observer<Boolean> {
            it?.let {
                val res = if (it) R.drawable.ic_favorite_white_24dp
                else R.drawable.ic_favorite_border_white_24dp
                binding.fab.setImageDrawable(getDrawable(res))
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
