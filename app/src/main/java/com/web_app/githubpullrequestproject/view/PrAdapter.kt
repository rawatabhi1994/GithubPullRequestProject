package com.web_app.githubpullrequestproject.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.web_app.githubpullrequestproject.R
import com.web_app.githubpullrequestproject.models.PrModel
import kotlinx.android.synthetic.main.item_list.view.*
import com.web_app.githubpullrequestproject.util.getProgressDrawable
import com.web_app.githubpullrequestproject.util.loadImage


class PrAdapter(var pullRequest: ArrayList<PrModel>):RecyclerView.Adapter<PrAdapter.PullListRequestViewHolder>() {

    fun updatePullRequestList(newPullRequest: List<PrModel>) {
        pullRequest.clear()
        pullRequest.addAll(newPullRequest)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = PullListRequestViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
    )

    override fun getItemCount() = pullRequest.size

    override fun onBindViewHolder(holder: PullListRequestViewHolder, position: Int) {
        holder.bind(pullRequest[position])
    }

    class PullListRequestViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val title = view.title
        private val created_date = view.created_date
        private val closed_date = view.closed_date
        private val imageView = view.imageView
        private val username = view.user
        private val progressDrawable = getProgressDrawable(view.context)


        fun bind(pullRequest: PrModel) {
            title.text = pullRequest.title
            created_date.text = pullRequest.created_date
            closed_date.text = pullRequest.closed_date
            username.text = pullRequest.user.name
            imageView.loadImage(pullRequest.user.avatar_url, progressDrawable)
        }
    }
}