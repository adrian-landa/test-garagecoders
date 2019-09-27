package com.garagecode.moviecategory.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.garagecode.moviecategory.R
import com.garagecode.moviecategory.interfaces.IRecyclerListener
import com.garagecode.moviecategory.ui.models.UICategory
import kotlinx.android.synthetic.main.item_category.view.*

/**
 * @author Luis L.
 *         Description:
 *         created on 26/09/2019
 */
class CategoryAdapter(private val listener: IRecyclerListener? = null) :
    ListAdapter<UICategory, CategoryAdapter.ViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itm = getItem(position)
        itm?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: UICategory) {
            itemView.tvCategoryName.text = item.name
            itemView.tvCategoryName.setTextColor(Color.parseColor(item.color))
            itemView.setOnClickListener { listener?.onItemClick(item) }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<UICategory>() {
            override fun areItemsTheSame(oldItem: UICategory, newItem: UICategory): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UICategory, newItem: UICategory): Boolean =
                oldItem == newItem
        }
    }

}