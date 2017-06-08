package jp.cordea.gene.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ListView

/**
 * Created by Yoshihiro Tanaka on 2017/06/02.
 */
class AdjustedListView(context: Context, attr: AttributeSet) : ListView(context, attr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val spec = View.MeasureSpec.makeMeasureSpec(View.MEASURED_SIZE_MASK, View.MeasureSpec.AT_MOST)
        super.onMeasure(widthMeasureSpec, spec)
    }
}