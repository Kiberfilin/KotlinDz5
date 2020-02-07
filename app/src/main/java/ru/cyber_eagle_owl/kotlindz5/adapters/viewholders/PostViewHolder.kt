package ru.cyber_eagle_owl.kotlindz5.adapters.viewholders

import android.content.Intent
import android.media.Image
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import dto.Post
import kotlinx.android.synthetic.main.post_item.view.*
import ru.cyber_eagle_owl.kotlindz5.R
import ru.cyber_eagle_owl.kotlindz5.adapters.MainRecyclerViewAdapter

class PostViewHolder(adapter: MainRecyclerViewAdapter, view: View) : BaseViewHolder(adapter, view) {
    init {
        with(itemView) {
            postLikeCountIcon.setOnClickListener { onLikeTaped(adapter, this) }
            postCommentCountIcon.setOnClickListener { onCommentTaped(adapter, this) }
            postSharedCountIcon.setOnClickListener { onShareTaped(adapter, this) }
        }
    }

    private fun onLikeTaped(adapter: MainRecyclerViewAdapter, itemView: View) {
        if (adapterPosition != RecyclerView.NO_POSITION) {
            with(adapter.allPosts[adapterPosition]) {
                likedByMe = !likedByMe
                if (likedByMe) likeCount++ else likeCount--
                //fillPost(this, itemView)
                adapter.notifyItemChanged(adapterPosition)
            }
        }
    }

    private fun onCommentTaped(adapter: MainRecyclerViewAdapter, itemView: View) {
        if (adapterPosition != RecyclerView.NO_POSITION) {
            with(adapter.allPosts[adapterPosition]) {
                commentedByMe = !commentedByMe
                if (commentedByMe) commentCount++ else commentCount--
                //fillPost(this, itemView)
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
        with(itemView) {
            fillPost(
                post,
                this,
                postCreatedDate,
                postContentText,
                postAuthorTV,
                postLikeCountText,
                postCommentCountText,
                postSharedCountText,
                postLikeCountIcon,
                postCommentCountIcon,
                postSharedCountIcon
            )
        }
    }

    private fun fillPost(
        postModel: Post,
        itemView: View,
        createdDate: TextView,
        contentText: TextView,
        authorTV: TextView,
        likeCountText: TextView,
        commentCountText: TextView,
        sharedCountText: TextView,
        likeCountIcon: ImageButton,
        commentCountIcon: ImageButton,
        sharedCountIcon: ImageButton
    ) {
        with(itemView) itemView@{
            with(postModel) {
                createdDate.text = created
                contentText.text = content
                authorTV.text = author
                fillCountText(likeCountText, likeCount, likedByMe, this@itemView)
                fillCountText(commentCountText, commentCount, commentedByMe, this@itemView)
                fillCountText(sharedCountText, shareCount, sharedByMe, this@itemView)
                iconManagement(
                    likeCountIcon,
                    likedByMe,
                    R.drawable.ic_favorite_active_24dp,
                    R.drawable.ic_favorite_inactive_24dp
                )
                iconManagement(
                    commentCountIcon,
                    commentedByMe,
                    R.drawable.ic_comment_active_24dp,
                    R.drawable.ic_comment_inactive_24dp
                )
                iconManagement(
                    sharedCountIcon,
                    sharedByMe,
                    R.drawable.ic_share_active_24dp,
                    R.drawable.ic_share_inactive_24dp
                )
            }
        }
    }

    private fun fillCountText(
        textView: TextView,
        count: Long,
        isTheTextColored: Boolean,
        view: View
    ) {
        if (count == 0L) {
            textView.visibility = View.INVISIBLE
        } else {
            textView.visibility = View.VISIBLE
        }
        with(textView) {
            text = count.toString()
            if (isTheTextColored) {
                setTextColor(ContextCompat.getColor(view.context, R.color.colorRed))
            } else {
                setTextColor(ContextCompat.getColor(view.context, R.color.colorTextSecondary))
            }
        }
    }

    private fun iconManagement(
        icon: ImageButton,
        isTheIconActive: Boolean,
        activeIcon: Int,
        inactiveIcon: Int
    ) {
        icon.setImageResource(if (isTheIconActive) activeIcon else inactiveIcon)
    }
}