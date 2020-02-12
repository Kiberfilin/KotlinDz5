package ru.cyber_eagle_owl.kotlindz5.adapters.viewholders

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.core.content.ContextCompat
import dto.Add
import dto.Post
import kotlinx.android.synthetic.main.add_post_item.view.*
import ru.cyber_eagle_owl.kotlindz5.adapters.MainRecyclerViewAdapter

class AddViewHolder(adapter: MainRecyclerViewAdapter, view: View) : PostViewHolder(adapter, view) {

    private lateinit var addPost: View

    override fun initViews() {
        addPost = itemView.addPostItem
    }

    override fun setOnClickListeners() {
        addPost.setOnClickListener { onAddPostTaped(adapter) }
    }

    private fun onAddPostTaped(adapter: MainRecyclerViewAdapter) {
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse((adapter.allPosts[adapterPosition] as Add).url)
        }

        if (intent.resolveActivity(itemView.context.packageManager) != null) {
            ContextCompat.startActivity(itemView.context, intent, null)
        }
    }

    override fun bind(post: Post) {
        postFiller.run {
            initForAddPost(itemView)
            fillAddPost(post)
        }
    }
}