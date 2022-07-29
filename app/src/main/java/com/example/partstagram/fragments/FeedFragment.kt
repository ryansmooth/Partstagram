package com.example.partstagram.fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.partstagram.Post
import com.example.partstagram.PostAdapter
import com.example.partstagram.R
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery

open class FeedFragment : Fragment() {

    lateinit var  PostView: RecyclerView

    lateinit var adapter : PostAdapter

    var allPosts : MutableList<Post> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        PostView = view.findViewById<RecyclerView>(R.id.PostView)
        adapter = PostAdapter(requireContext(),allPosts)
        PostView.adapter = adapter
        PostView.layoutManager = LinearLayoutManager(requireContext())

        queryPosts()
    }
   open fun queryPosts() {

        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        query.include(Post.KEY_USER)
        query.addDescendingOrder("created")
        query.findInBackground(object : FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {

                if (e != null) {

                    Log.e(TAG, "Error")
                } else {
                    if (posts != null) {
                        for (post in posts) {
                            Log.i(TAG, "POST" + post.getDescription() + " , username:" +
                                        post.getUser()?.username
                            )
                        }
                        allPosts.addAll(posts)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }
    companion object{
        const val TAG = "FeedFragment"
    }

}