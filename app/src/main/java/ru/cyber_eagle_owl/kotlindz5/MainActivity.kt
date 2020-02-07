package ru.cyber_eagle_owl.kotlindz5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import dto.Post
import dto.posttypes.PostType
import kotlinx.android.synthetic.main.activity_main.*
import ru.cyber_eagle_owl.kotlindz5.adapters.MainRecyclerViewAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prepareRecyclerView(testData())
    }

    private fun testData() = arrayListOf(
        Post(
            3234, "CATS", "All your base are belong to us", "1992",
            likeCount = 3,
            commentCount = 1,
            shareCount = 0,
            likedByMe = true,
            commentedByMe = true,
            sharedByMe = false,
            postType = PostType.POST
        ),
        Post(
            3234, "CATS", "All your base are belong to us", "1992",
            likeCount = 3,
            commentCount = 1,
            shareCount = 0,
            likedByMe = true,
            commentedByMe = false,
            sharedByMe = false,
            postType = PostType.POST
        ),
        Post(
            3234, "CATS", "All your base are belong to us", "1992",
            likeCount = 3,
            commentCount = 1,
            shareCount = 0,
            likedByMe = true,
            commentedByMe = false,
            sharedByMe = false,
            postType = PostType.POST
        ),
        Post(
            3234, "CATS", "All your base are belong to us", "1992",
            likeCount = 3,
            commentCount = 1,
            shareCount = 0,
            likedByMe = true,
            commentedByMe = false,
            sharedByMe = false,
            postType = PostType.POST
        ),
        Post(
            3234, "CATS", "All your base are belong to us", "1992",
            likeCount = 3,
            commentCount = 1,
            shareCount = 0,
            likedByMe = true,
            commentedByMe = false,
            sharedByMe = false,
            postType = PostType.POST
        )
    )

    private fun prepareRecyclerView(posts: List<Post>) {

        mainRv.layoutManager = LinearLayoutManager(this)
        mainRv.adapter = MainRecyclerViewAdapter(posts)
    }
}
