package jp.cordea.gene

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Yoshihiro Tanaka on 2017/06/06.
 */
class ListItemDecoration(private val context: Context) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        view?.let {
            parent?.let { parent ->
                val lp = it.layoutParams as RecyclerView.LayoutParams

                lp.topMargin =
                        if (parent.getChildAdapterPosition(it) == 0) {
                            context.resources.getDimension(R.dimen.card_margin).toInt()
                        } else {
                            0
                        }
            }
        }
    }
}
