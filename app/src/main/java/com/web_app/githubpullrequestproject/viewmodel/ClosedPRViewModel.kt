package com.web_app.githubpullrequestproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.web_app.githubpullrequestproject.models.PrModel
import com.web_app.githubpullrequestproject.repo.PrRepo
import com.web_app.githubpullrequestproject.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class ClosedPRViewModel(val repo: PrRepo) :  ViewModel() {

    private val prData: MutableLiveData<Resource<List<PrModel>>> = MutableLiveData()
    val prDataExposed:MutableLiveData<Resource<List<PrModel>>> = prData
    val  pullRequestLoadError = MutableLiveData<Boolean>()
    val loading  = MutableLiveData<Boolean>()
    fun refresh(){
        fetchPullRequest()
    }

    private fun fetchPullRequest() {
        prData.postValue(Resource.Loading())
        viewModelScope.launch {
            val response = repo.getClosedPr()
            prData.postValue(handlePrResponse(response))
        }
    }

    private fun handlePrResponse(response: Response<List<PrModel>>): Resource<List<PrModel>>? {
               if(response.isSuccessful){
                   response.body()?.let { resultResponse ->
                       return Resource.Success(resultResponse)
                   }
               }
             return Resource.Error(response.message())
    }
}