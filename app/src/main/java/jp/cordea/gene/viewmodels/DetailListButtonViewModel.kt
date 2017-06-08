package jp.cordea.gene.viewmodels

import android.view.View
import jp.cordea.gene.api.response.GeneRif

/**
 * Created by Yoshihiro Tanaka on 2017/06/02.
 */
class DetailListButtonViewModel(val title: String, val generifs: List<GeneRif>) : DetailListBaseViewModel() {

    var startGeneRifActivity: (List<GeneRif>) -> Unit = { }

    val onClick = View.OnClickListener {
        startGeneRifActivity(generifs)
    }

}
