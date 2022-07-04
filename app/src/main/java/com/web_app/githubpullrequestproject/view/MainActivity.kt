package com.web_app.githubpullrequestproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.web_app.githubpullrequestproject.R
import com.web_app.githubpullrequestproject.repo.PrRepo
import com.web_app.githubpullrequestproject.util.Resource
import com.web_app.githubpullrequestproject.viewmodel.ClosedPRViewModel
import com.web_app.githubpullrequestproject.viewmodel.PrViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ClosedPRViewModel

    private val pullRequestAdapter = PrAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val newsRepository = PrRepo()
        val viewModelProviderFactory = PrViewModelFactory(newsRepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(ClosedPRViewModel::class.java)

        viewModel.refresh()

        recyclerList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pullRequestAdapter
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
        observeViewModel()

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }
    }

    private fun observeViewModel() {
             viewModel.prDataExposed.observe(this,Observer { response ->
                 when(response){
                     is Resource.Success -> {
                          response.data?.let {
                              loading_view.visibility =  View.GONE
                              list_error.visibility = View.GONE
                              recyclerList.visibility = View.VISIBLE
                              pullRequestAdapter.updatePullRequestList(it)
                          }
                     }
                     is Resource.Error -> {

                         response.message?.let { message ->
                             list_error.visibility = View.VISIBLE
                             loading_view.visibility =  View.GONE
                         }
                     }
                     is Resource.Loading -> {
                         loading_view.visibility =  View.VISIBLE
                         list_error.visibility = View.GONE
                         recyclerList.visibility = View.GONE
                     }
                 }
             })
    }

}
