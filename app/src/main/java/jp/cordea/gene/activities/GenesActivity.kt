package jp.cordea.gene.activities

import android.arch.lifecycle.*
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import jp.cordea.gene.App
import jp.cordea.gene.R
import jp.cordea.gene.databinding.ActivityGenesBinding
import jp.cordea.gene.viewmodels.DetailViewModel
import jp.cordea.gene.viewmodels.GenesListItemViewModel
import jp.cordea.gene.viewmodels.GenesViewModel
import javax.inject.Inject

class GenesActivity : AppCompatActivity(), LifecycleRegistryOwner {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProviders.of(this, factory).get(GenesViewModel::class.java)
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

        val binding = DataBindingUtil.setContentView<ActivityGenesBinding>(this, R.layout.activity_genes)
        binding.vm = viewModel

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.listView.adapter = adapter

        subscribe()
        viewModel.extractQuery(intent)

        viewModel.fetchGenes()
    }

    private fun subscribe() {
        viewModel.getSelectedId().observe(this, Observer<String> {
            it?.let {
                startActivity(DetailViewModel.createIntent(this, it))
            }
        })

        viewModel.getItems().observe(this, Observer<List<GenesListItemViewModel>> {
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
