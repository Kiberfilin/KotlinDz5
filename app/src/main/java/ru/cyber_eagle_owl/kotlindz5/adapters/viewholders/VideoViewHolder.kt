package ru.cyber_eagle_owl.kotlindz5.adapters.viewholders

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import dto.Post
import dto.Video
import kotlinx.android.synthetic.main.video_post_item.view.*
import ru.cyber_eagle_owl.kotlindz5.adapters.MainRecyclerViewAdapter

class VideoViewHolder(adapter: MainRecyclerViewAdapter, view: View) :
    PostViewHolder(adapter, view) {
    lateinit var videoPreview: ImageButton

    override fun initViews() {
        with(itemView) {
            likeCountIcon = videoPostLikeCountIcon
            commentCountIcon = videoPostCommentCountIcon
            sharedCountIcon = videoPostSharedCountIcon
            videoPreview = videoPostVideoPreviewImg
        }
    }

    override fun setOnClickListeners() {
        super.setOnClickListeners()
        videoPreview.setOnClickListener { onVideoPreviewTaped(adapter) }
    }

    private fun onVideoPreviewTaped(adapter: MainRecyclerViewAdapter) {
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse((adapter.allPosts[adapterPosition] as Video).url)
        }

        if (intent.resolveActivity(itemView.context.packageManager) != null) {
            ContextCompat.startActivity(itemView.context, intent, null)
        }
    }

    override fun bind(post: Post) {
        with(postFiller) {
            initForVideoPost(itemView)
            fillVideoPost(post)
        }
    }
}