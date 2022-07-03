package com.web_app.githubpullrequestproject.models

import com.google.gson.annotations.SerializedName

data class PrModel( val title:String?,
                     @SerializedName("created_at")
                     val created_date:String?,
                     @SerializedName("closed_at")
                     val closed_date:String?,
                     val user:User)