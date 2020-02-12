package ru.cyber_eagle_owl.kotlindz5.adapters.viewholders

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import dto.Coordinates
import dto.Event
import dto.Post
import kotlinx.android.synthetic.main.event_post_item.view.*
import ru.cyber_eagle_owl.kotlindz5.adapters.MainRecyclerViewAdapter

class EventViewHolder(adapter: MainRecyclerViewAdapter, view: View) :
    PostViewHolder(adapter, view) {

    private lateinit var postLocationIcon: ImageButton

    override fun initViews() {
        with(itemView) {
            likeCountIcon = eventPostLikeCountIcon
            commentCountIcon = eventPostCommentCountIcon
            sharedCountIcon = eventPostSharedCountIcon
            postLocationIcon = eventPostLocationIcon
        }
    }

    override fun setOnClickListeners() {
        super.setOnClickListeners()
        postLocationIcon.setOnClickListener { onLocationTaped(adapter) }
    }

    private fun onLocationTaped(adapter: MainRecyclerViewAdapter) {
        val (lat, lng) = (adapter.allPosts[adapterPosition] as Event).coordinates
            ?: Coordinates()
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse("geo:$lat,$lng")
        }

        if (intent.resolveActivity(itemView.context.packageManager) != null) {
            startActivity(itemView.context, intent, null)
        }
    }

    override fun bind(post: Post) {
        with(postFiller) {
            initForEvent(itemView)
            fillEvent(post)
        }
    }
}