package com.web_app.githubpullrequestproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.web_app.githubpullrequestproject.repo.PrRepo

class PrViewModelFactory(val prRepository: PrRepo
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ClosedPRViewModel(prRepository) as T
    }
}