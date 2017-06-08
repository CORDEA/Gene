package jp.cordea.gene.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import jp.cordea.gene.viewmodels.*

/**
 * Created by Yoshihiro Tanaka on 2017/06/04.
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ClassKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ClassKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ClassKey(FavoritesViewModel::class)
    abstract fun bindFavoritesViewModel(viewModel: FavoritesViewModel): ViewModel

    @Binds
    @IntoMap
    @ClassKey(GenesViewModel::class)
    abstract fun bindGenesViewModel(viewModel: GenesViewModel): ViewModel

    @Binds
    @IntoMap
    @ClassKey(GeneRifViewModel::class)
    abstract fun bindGeneRifViewModel(viewModel: GeneRifViewModel): ViewModel

    @Binds
    abstract fun bindGeneViewModelFactory(factory: GeneViewModelFactory): ViewModelProvider.Factory

}
