package ru.cyber_eagle_owl.kotlindz5.adapters.viewholders

import android.view.View
import dto.Post
import dto.posttypes.PostType
import kotlinx.android.synthetic.main.repost_item.view.*
import ru.cyber_eagle_owl.kotlindz5.R
import ru.cyber_eagle_owl.kotlindz5.adapters.MainRecyclerViewAdapter

class RepostViewHolder(adapter: MainRecyclerViewAdapter, view: View) :
    PostViewHolder(adapter, view) {

    override fun initViews() {
        with(itemView) {
            likeCountIcon = repostLikeCountIcon
            commentCountIcon = repostCommentCountIcon
            sharedCountIcon = repostSharedCountIcon
        }
    }

    override fun bind(post: Post) {
        with(postFiller) {
            initForReost(itemView)
            fillPost(post)
        }
        val sourceContainer = itemView.repostSourceContainer
        sourceContainer?.run {
            when (post.source?.postType) {
                PostType.POST -> {
                    layoutResource = R.layout.post_item
                    with(postFiller) {
                        initForPost(this@run.inflate())
                        fillPost(post.source)
                    }
                }
                PostType.EVENT -> {
                    layoutResource = R.layout.event_post_item
                    with(postFiller) {
                        initForEvent(this@run.inflate())
                        fillEvent(post.source)
                    }
                }
                PostType.VIDEO -> {
                    layoutResource = R.layout.video_post_item
                    with(postFiller) {
                        initForVideoPost(this@run.inflate())
                        fillVideoPost(post.source)
                    }
                }
                PostType.ADD -> throw java.lang.IllegalStateException("Нельзя репостить рекламу!")
                PostType.REPOST -> throw java.lang.IllegalStateException("Пока что нельзя репостить репосты!")
                else -> throw IllegalStateException("Неизвестный тип поста!")
            }
        }
    }
}