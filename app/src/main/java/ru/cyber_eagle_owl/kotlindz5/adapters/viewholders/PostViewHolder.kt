package ru.cyber_eagle_owl.kotlindz5.adapters.viewholders

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import dto.Post
import kotlinx.android.synthetic.main.post_item.view.*
import ru.cyber_eagle_owl.kotlindz5.adapters.MainRecyclerViewAdapter
import ru.cyber_eagle_owl.kotlindz5.adapters.viewholders.utils4holders.PostFiller

open class PostViewHolder(adapter: MainRecyclerViewAdapter, view: View) :
    BaseViewHolder(adapter, view) {

    protected val postFiller = PostFiller()

    lateinit var likeCountIcon: ImageButton
    lateinit var commentCountIcon: ImageButton
    lateinit var sharedCountIcon: ImageButton

    init {
        this.initViews()
        this.setOnClickListeners()
    }

    protected open fun initViews() {
        with(itemView) {
            likeCountIcon = postLikeCountIcon
            commentCountIcon = postCommentCountIcon
            sharedCountIcon = postSharedCountIcon
        }
    }

    protected open fun setOnClickListeners() {
        likeCountIcon.setOnClickListener { onLikeTaped(adapter) }
        commentCountIcon.setOnClickListener { onCommentTaped(adapter) }
        sharedCountIcon.setOnClickListener { onShareTaped(adapter, itemView) }
    }

    private fun onLikeTaped(adapter: MainRecyclerViewAdapter) {
        if (adapterPosition != RecyclerView.NO_POSITION) {
            with(adapter.allPosts[adapterPosition]) {
                likedByMe = !likedByMe
                if (likedByMe) likeCount++ else likeCount--
                adapter.notifyItemChanged(adapterPosition)
            }
        }
    }

    private fun onCommentTaped(adapter: MainRecyclerViewAdapter) {
        if (adapterPosition != RecyclerView.NO_POSITION) {
            with(adapter.allPosts[adapterPosition]) {
                commentedByMe = !commentedByMe
                if (commentedByMe) commentCount++ else commentCount--
                adapter.notifyItemChanged(adapterPosition)
            }
        }
    }

    private fun onShareTaped(adapter: MainRecyclerViewAdapter, itemView: View) {
        if (adapterPosition != RecyclerView.NO_POSITION) {
            val item = adapter.allPosts[adapterPosition]
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT, """
                                ${item.author} (${item.created})
    
                                ${item.content}
                            """.trimIndent()
                )
                type = "text/plain"
            }
            if (!item.sharedByMe) item.shareCount++
            item.sharedByMe = true
            adapter.notifyItemChanged(adapterPosition)
            itemView.context.startActivity(intent)
        }
    }

    override fun bind(post: Post) {
        postFiller.initForPost(itemView)
        postFiller.fillPost(post)
    }
}