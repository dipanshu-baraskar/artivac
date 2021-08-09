package com.artivac

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CountryAdapter(private val context: Context, private val rows: ArrayList<Rows>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById<ImageView>(R.id.image)
        var title = itemView.findViewById<TextView>(R.id.title)
        var description = itemView.findViewById<TextView>(R.id.description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val rows=  rows[position]
        holder.title.text = rows.title
        holder.description.text = rows.description
        Glide.with(holder.itemView.context).load(rows.imageHref)
            .error(R.drawable.ic_baseline_error_24).into(holder.image)
    }

    override fun getItemCount(): Int {
        return rows.size
    }

    fun clear() {
        rows.clear()
        notifyDataSetChanged()
    }
}

