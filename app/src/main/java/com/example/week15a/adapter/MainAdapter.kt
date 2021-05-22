package com.example.week15a.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week15a.databinding.ItemListBinding
import com.example.week15a.model.Post

class MainAdapter(private var post: List<Post>, val listener: onAdapterClick): RecyclerView.Adapter<MainAdapter.PostViewHolder>() {

    inner class PostViewHolder(val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root)

    interface onAdapterClick{
        fun onClick(post: Post)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.binding.apply {
            tvFirstPlace.text = post[position].first_name
            tvFirstPlace.setOnClickListener {
                listener.onClick(post[position])
            }
            tvLastPlace.text = post[position].last_name
            tvLastPlace.setOnClickListener {
                listener.onClick(post[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return post.size
    }
}