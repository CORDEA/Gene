package jp.cordea.gene

import android.app.Application
import jp.cordea.gene.di.AppComponent
import jp.cordea.gene.di.AppModule
import jp.cordea.gene.di.DaggerAppComponent

/**
 * Created by Yoshihiro Tanaka on 2017/06/03.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        component =
                DaggerAppComponent
                        .builder()
                        .appModule(AppModule(this))
                        .build()
    }

    companion object {
        lateinit var component: AppComponent
    }

}
