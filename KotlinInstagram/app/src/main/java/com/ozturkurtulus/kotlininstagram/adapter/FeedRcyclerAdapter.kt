package com.ozturkurtulus.kotlininstagram.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ozturkurtulus.kotlininstagram.databinding.RecyclerrowBinding
import com.ozturkurtulus.kotlininstagram.model.Post
import com.squareup.picasso.Picasso

class FeedRcyclerAdapter (val postlist : ArrayList <Post>) : RecyclerView.Adapter<FeedRcyclerAdapter.PostHolder>() {

    class PostHolder(val binding: RecyclerrowBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val binding = RecyclerrowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostHolder(binding)
    }

    override fun getItemCount(): Int {
        return postlist.size
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.binding.recyclerEmailText.text = postlist.get(position).email
        holder.binding.recyclerCommentText.text = postlist.get(position).comment
        Picasso.get().load(postlist.get(position).downloadUrl).into(holder.binding.recyclerImageView)
    }
}