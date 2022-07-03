package com.web_app.githubpullrequestproject.repo

import com.web_app.githubpullrequestproject.models.PrModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface PrApi {
    @GET("repos/rawatabhi1994//pulls?state=closed")
    suspend fun getPullRequest(): Response<List<PrModel>>
}