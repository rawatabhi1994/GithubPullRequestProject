package com.web_app.githubpullrequestproject.repo

import com.web_app.githubpullrequestproject.models.PrModel
import io.reactivex.Single
import retrofit2.http.GET

interface PrApi {
    @GET("repos/prakashshuklahub/Pull-Request-GithubApi-Demo-App/pulls?state=closed")
    fun getPullRequest(): Single<List<PrModel>>
}