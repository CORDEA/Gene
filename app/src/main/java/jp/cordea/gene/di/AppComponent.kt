package jp.cordea.gene.di

import dagger.Component
import jp.cordea.gene.App
import jp.cordea.gene.activities.*
import javax.inject.Singleton

/**
 * Created by Yoshihiro Tanaka on 2017/06/04.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(app: App)

    fun inject(activity: MainActivity)

    fun inject(activity: GenesActivity)

    fun inject(activity: FavoritesActivity)

    fun inject(activity: DetailActivity)

    fun inject(activity: GeneRifActivity)

}