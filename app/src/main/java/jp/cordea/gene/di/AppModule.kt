package jp.cordea.gene.di

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import jp.cordea.gene.BuildConfig
import jp.cordea.gene.api.Api
import jp.cordea.gene.api.ApiClient
import jp.cordea.gene.api.MockApiClient
import jp.cordea.gene.db.AppDatabase
import jp.cordea.gene.db.FavoriteDao
import kotlinx.coroutines.experimental.newSingleThreadContext
import javax.inject.Singleton
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by Yoshihiro Tanaka on 2017/06/04.
 */
@Module(includes = arrayOf(ViewModelModule::class))
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideThreadContext(): CoroutineContext {
        return newSingleThreadContext("FavoriteContext")
    }

    @Provides
    @Singleton
    fun provideApiClient(): Api {
        return if (BuildConfig.IS_MOCK) MockApiClient(context) else ApiClient()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "gene.db").build()
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(database: AppDatabase): FavoriteDao {
        return database.favoriteDao()
    }
}