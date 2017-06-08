package jp.cordea.gene.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import javax.inject.Inject

/**
 * Created by Yoshihiro Tanaka on 2017/05/26.
 */
class MainViewModel @Inject constructor() : ViewModel() {

    var query: String = ""

    var startFavoriteActivity = { }

    val searchOnClick = View.OnClickListener {
        if (query.isNotBlank()) {
            requestQuery.value = query
        }
    }

    val favoriteOnClick = View.OnClickListener {
        startFavoriteActivity()
    }

    fun getRequestQuery(): LiveData<String> {
        return requestQuery
    }

    private val requestQuery = MutableLiveData<String>()

}