package jp.cordea.gene.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by Yoshihiro Tanaka on 2017/06/04.
 */
class GeneViewModelFactory @Inject constructor(
        private val viewModels: @JvmSuppressWildcards Map<Class<*>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(p0: Class<T>?): T {
        p0?.let { model ->
            var viewModel = viewModels[model]
            if (viewModel == null) {
                viewModel =
                        viewModels
                                .filter { model.isAssignableFrom(it.key) }
                                .map { it.value }
                                .firstOrNull()
            }
            viewModel?.let {
                return it.get() as T
            }
        }
        throw IllegalStateException()
    }

}
