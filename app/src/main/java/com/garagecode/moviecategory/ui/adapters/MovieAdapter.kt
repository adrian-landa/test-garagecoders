package com.garagecode.moviecategory.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.garagecode.moviecategory.R
import com.garagecode.moviecategory.ui.models.UIMovie
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * @author Luis L.
 *         Description:
 *         created on 26/09/2019
 */
class MovieAdapter : ListAdapter<UIMovie, MovieAdapter.ViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itm = getItem(position)
        itm?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: UIMovie) {
            val ctx = itemView.context
            itemView.tvMovieTitle.text = ctx.getString(R.string.template_title, item.title)
            itemView.tvMovieSubtitle.text = ctx.getString(R.string.template_subtitle, item.subtitle)
            itemView.tvMovieYear.text = ctx.getString(R.string.template_year, item.year)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<UIMovie>() {
            override fun areItemsTheSame(oldItem: UIMovie, newItem: UIMovie): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: UIMovie, newItem: UIMovie): Boolean =
                oldItem == newItem
        }
    }

}