package jp.cordea.gene.activities

import android.arch.lifecycle.*
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import jp.cordea.gene.App
import jp.cordea.gene.R
import jp.cordea.gene.databinding.ActivityGeneRifBinding
import jp.cordea.gene.viewmodels.GeneRifListItemViewModel
import jp.cordea.gene.viewmodels.GeneRifViewModel
import javax.inject.Inject

class GeneRifActivity : AppCompatActivity(), LifecycleRegistryOwner {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProviders.of(this, factory).get(GeneRifViewModel::class.java)
    }

    private val adapter by lazy {
        viewModel.getAdapter(this)
    }

    private val lifecycle = LifecycleRegistry(this)

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycle
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.component.inject(this)

        val binding = DataBindingUtil.setContentView<ActivityGeneRifBinding>(this, R.layout.activity_gene_rif)
        binding.vm = viewModel

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        subscribe()
        viewModel.extractGenerifs(intent)

        binding.listView.adapter = adapter
    }

    private fun subscribe() {
        viewModel.getItems().observe(this, Observer<List<GeneRifListItemViewModel>> {
            it?.let {
                adapter.refreshItems(it)
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
