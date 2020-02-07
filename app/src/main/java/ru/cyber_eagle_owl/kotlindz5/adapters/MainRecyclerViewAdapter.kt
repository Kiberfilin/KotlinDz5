package ru.cyber_eagle_owl.kotlindz5.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dto.Post
import dto.posttypes.PostType
import ru.cyber_eagle_owl.kotlindz5.R
import ru.cyber_eagle_owl.kotlindz5.adapters.viewholders.BaseViewHolder
import ru.cyber_eagle_owl.kotlindz5.adapters.viewholders.PostViewHolder
import java.lang.IllegalArgumentException

class MainRecyclerViewAdapter(val allPosts: List<Post>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            PostType.POST.value -> PostViewHolder(
                this, LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
            )
            else -> throw IllegalArgumentException("Такого PostType (viewType = $viewType) не существует!")
        }

    override fun getItemCount() = allPosts.size

    override fun getItemViewType(position: Int) = allPosts[position].postType.value

    override fun getItemId(position: Int) = allPosts[position].id

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as BaseViewHolder) {
            bind(allPosts[position])
        }
    }
}