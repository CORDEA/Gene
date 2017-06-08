package jp.cordea.gene.api

import com.squareup.moshi.Moshi
import jp.cordea.gene.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Yoshihiro Tanaka on 2017/06/06.
 */
abstract class ApiClientBase : Api {

    protected val moshi: Moshi = Moshi
            .Builder()
            .add(AnnotationAdapter())
            .build()

    protected val retrofit: Retrofit = Retrofit
            .Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
}
