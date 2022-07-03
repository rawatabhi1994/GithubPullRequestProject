package com.web_app.githubpullrequestproject.repo

class PrRepo {
    suspend fun getClosedPr() =
        RetrofitInstance.api.getPullRequest()
}