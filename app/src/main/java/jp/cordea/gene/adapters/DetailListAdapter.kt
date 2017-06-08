package jp.cordea.gene.adapters

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import jp.cordea.gene.BR
import jp.cordea.gene.R
import jp.cordea.gene.databinding.ListItemDetailBinding
import jp.cordea.gene.databinding.ListItemDetailButtonBinding
import jp.cordea.gene.databinding.ListItemDetailHeaderBinding
import jp.cordea.gene.viewmodels.*

/**
 * Created by Yoshihiro Tanaka on 2017/06/01.
 */
class DetailListAdapter(private val context: Context, private var items: List<DetailListBaseViewModel> = listOf()) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val item = items[viewType]
        if (item is DetailListHeaderViewModel) {
            return HeaderViewHolder(DataBindingUtil.inflate<ListItemDetailHeaderBinding>(
                    inflater, R.layout.list_item_detail_header, parent, false))
        } else if (item is DetailListButtonViewModel) {
            return ButtonViewHolder(DataBindingUtil.inflate<ListItemDetailButtonBinding>(
                    inflater, R.layout.list_item_detail_button, parent, false))
        } else {
            return ItemViewHolder(DataBindingUtil.inflate<ListItemDetailBinding>(
                    inflater, R.layout.list_item_detail, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val item = items[position]
        if (item is DetailListHeaderViewModel && holder is HeaderViewHolder) {
            bindCustomViewHolder<HeaderViewHolder>(holder, position)
        }
        if (item is DetailListItemViewModel && holder is ItemViewHolder) {
            bindCustomViewHolder<ItemViewHolder>(holder, position)
            holder.binding.listView.adapter = item.getAdapter(context)
        }
        if (item is DetailListButtonViewModel && holder is ButtonViewHolder) {
            bindCustomViewHolder<ButtonViewHolder>(holder, position)
            item.startGeneRifActivity = {
                context.startActivity(GeneRifViewModel.createIntent(context, it))
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : ViewHolder> bindCustomViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as? T)?.binding?.setVariable(BR.vm, items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun refreshItems(items: List<DetailListBaseViewModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    private open class ViewHolder(open val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    private class ItemViewHolder(override val binding: ListItemDetailBinding) : ViewHolder(binding)

    private class ButtonViewHolder(override val binding: ListItemDetailButtonBinding) : ViewHolder(binding)

    private class HeaderViewHolder(override val binding: ListItemDetailHeaderBinding) : ViewHolder(binding)

}
