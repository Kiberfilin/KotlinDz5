package ru.cyber_eagle_owl.kotlindz5.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dto.Post
import dto.posttypes.PostType
import ru.cyber_eagle_owl.kotlindz5.R
import ru.cyber_eagle_owl.kotlindz5.adapters.viewholders.*
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class MainRecyclerViewAdapter(val allPosts: List<Post>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            PostType.POST.value -> PostViewHolder(
                this, LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
            )
            PostType.EVENT.value -> EventViewHolder(
                this,
                LayoutInflater.from(parent.context).inflate(R.layout.event_post_item, parent, false)
            )
            RepostType.POST_REPOST.value -> RepostViewHolder(
                this,
                LayoutInflater.from(parent.context).inflate(R.layout.repost_item, parent, false)
            )
            RepostType.EVENT_REPOST.value -> RepostViewHolder(
                this,
                LayoutInflater.from(parent.context).inflate(R.layout.repost_item, parent, false)
            )
            RepostType.VIDEO_REPOST.value -> RepostViewHolder(
                this,
                LayoutInflater.from(parent.context).inflate(R.layout.repost_item, parent, false)
            )
            PostType.ADD.value -> AddViewHolder(
                this,
                LayoutInflater.from(parent.context).inflate(R.layout.add_post_item, parent, false)
            )
            PostType.VIDEO.value -> VideoViewHolder(
                this,
                LayoutInflater.from(parent.context).inflate(R.layout.video_post_item, parent, false)
            )
            else -> throw IllegalArgumentException("Такого PostType (viewType = $viewType) не существует!")
        }

    override fun getItemCount() = allPosts.size

    override fun getItemId(position: Int) = allPosts[position].id

    override fun getItemViewType(position: Int) = when (allPosts[position].postType) {
        PostType.POST -> PostType.POST.value
        PostType.EVENT -> PostType.EVENT.value
        PostType.REPOST -> when (allPosts[position].source?.postType) {
            PostType.POST -> RepostType.POST_REPOST.value
            PostType.EVENT -> RepostType.EVENT_REPOST.value
            PostType.VIDEO -> RepostType.VIDEO_REPOST.value
            else -> throw IllegalStateException("Неверный source")
        }
        PostType.ADD -> PostType.ADD.value
        PostType.VIDEO -> PostType.VIDEO.value
        else -> throw IllegalStateException("Неверный тип поста")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as BaseViewHolder) {
            bind(allPosts[position])
        }
    }
}

enum class RepostType(val value: Int){
    POST_REPOST(21), EVENT_REPOST(22), VIDEO_REPOST(23)
}