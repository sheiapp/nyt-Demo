package com.demo.nytdemo.utils.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.demo.nytdemo.R
import com.demo.nytdemo.model.Result
import com.demo.nytdemo.utils.ItemClickHandler
import kotlinx.android.synthetic.main.recycler_item_view.view.*

class RecyclerViewAdapter(
   var elements: MutableList<Result>,
   var itemClickHandler:ItemClickHandler
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private val data = elements
    private val onClickHandler=itemClickHandler
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        setupView(holder,data[position])
        holder.itemView.setOnClickListener {
            onClickHandler.onItemClick(data[position])
        }

    }
    private fun setupView(
        holder: ViewHolder,
        result: Result
    ) {
        if (result.title.isNullOrEmpty()){
            holder.itemView.title.visibility=View.GONE
        }
        if (result.byline.isNullOrEmpty()){
            holder.itemView.author.visibility=View.GONE
        }
        if (result.published_date.isNullOrEmpty()){
            holder.itemView.date.visibility=View.GONE
        }
        holder.itemView.title.text=result.title
        holder.itemView.author.text=result.byline
        holder.itemView.date.text=result.published_date
        if (result.media.isNullOrEmpty()){
            return
        }
        settingThumbnails(result.media[0].media[0].url,holder.itemView.img)
    }
    private fun settingThumbnails(url: String, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(url)
            .transition(
                DrawableTransitionOptions.withCrossFade(
                    DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
                )
            )
            .into(imageView)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}