package com.web_app.githubpullrequestproject.models

import com.google.gson.annotations.SerializedName

class Userdata (  @SerializedName("login")
                  val name: String,
                  val avatar_url:String?)