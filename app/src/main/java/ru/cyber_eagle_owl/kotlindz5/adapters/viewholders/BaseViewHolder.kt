package ru.cyber_eagle_owl.kotlindz5.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import dto.Post
import ru.cyber_eagle_owl.kotlindz5.adapters.MainRecyclerViewAdapter

abstract class BaseViewHolder(val adapter: MainRecyclerViewAdapter, view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(post: Post)
}