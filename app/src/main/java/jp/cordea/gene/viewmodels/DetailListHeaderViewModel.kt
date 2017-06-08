package jp.cordea.gene.viewmodels

import jp.cordea.gene.api.response.Annotation

/**
 * Created by Yoshihiro Tanaka on 2017/06/01.
 */
class DetailListHeaderViewModel(item: Annotation) : DetailListBaseViewModel() {

    val summary = item.summary ?: ""

}