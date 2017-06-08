package jp.cordea.gene.activities

import android.arch.lifecycle.*
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import jp.cordea.gene.App
import jp.cordea.gene.R
import jp.cordea.gene.databinding.ActivityMainBinding
import jp.cordea.gene.viewmodels.FavoritesViewModel
import jp.cordea.gene.viewmodels.GenesViewModel
import jp.cordea.gene.viewmodels.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(), LifecycleRegistryOwner {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
    }

    private val lifecycle = LifecycleRegistry(this)

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycle
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.component.inject(this)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.vm = viewModel

        viewModel.startFavoriteActivity = {
            startActivity(FavoritesViewModel.createIntent(this))
        }

        subscribe()
    }

    private fun subscribe() {
        viewModel.getRequestQuery().observe(this, Observer<String> {
            it?.let {
                startActivity(GenesViewModel.createIntent(this@MainActivity, it))
            }
        })
    }
}
