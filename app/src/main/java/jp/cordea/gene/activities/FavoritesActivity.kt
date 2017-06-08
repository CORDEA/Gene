package jp.cordea.gene.activities

import android.arch.lifecycle.*
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import jp.cordea.gene.App
import jp.cordea.gene.R
import jp.cordea.gene.databinding.ActivityFavoritesBinding
import jp.cordea.gene.viewmodels.DetailViewModel
import jp.cordea.gene.viewmodels.FavoritesListItemViewModel
import jp.cordea.gene.viewmodels.FavoritesViewModel
import javax.inject.Inject

class FavoritesActivity : AppCompatActivity(), LifecycleRegistryOwner {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProviders.of(this, factory).get(FavoritesViewModel::class.java)
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
        setContentView(R.layout.activity_favorites)
        App.component.inject(this)

        val binding = DataBindingUtil.setContentView<ActivityFavoritesBinding>(this, R.layout.activity_favorites)
        binding.vm = viewModel
        binding.listView.adapter = adapter

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        subscribe()

        viewModel.fetchFavorites()
    }

    private fun subscribe() {
        viewModel.getItems().observe(this, Observer<List<FavoritesListItemViewModel>> {
            it?.let {
                adapter.refreshItems(it)
            }
        })

        viewModel.getSelectedId().observe(this, Observer<String> {
            it?.let {
                startActivity(DetailViewModel.createIntent(this, it))
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
