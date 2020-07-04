package com.example.cardviewpanelapp.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cardviewpanelapp.databinding.ApplicationItemLayoutBinding
import com.example.cardviewpanelapp.model.Application
import com.example.cardviewpanelapp.presentation.base.BaseRVAdapter

class RVApplicationsAdapter(
    listener: (View, Application) -> Unit
) : BaseRVAdapter<Application, MyApplicationsItemViewModel, ApplicationItemLayoutBinding>(listener) {
    override fun inflateDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ApplicationItemLayoutBinding = ApplicationItemLayoutBinding.inflate(inflater, container, false)

    override fun getBindItem(itemViewModel: MyApplicationsItemViewModel): Application? =
        itemViewModel.myApplicationsModel
}