package com.example.cardviewpanelapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cardviewpanelapp.databinding.ApplicationItemLayoutBinding
import com.example.cardviewpanelapp.model.MyApplications
import com.example.cardviewpanelapp.presentation.base.BaseRVAdapter
import com.example.cardviewpanelapp.presentation.recyclerview.MyApplicationsItemViewModel

class RVApplicationsAdapter(
    listener: (View, MyApplications) -> Unit
) : BaseRVAdapter<MyApplications, MyApplicationsItemViewModel, ApplicationItemLayoutBinding>(listener) {
    override fun inflateDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ApplicationItemLayoutBinding = ApplicationItemLayoutBinding.inflate(inflater, container, false)

    override fun getBindItem(itemViewModel: MyApplicationsItemViewModel): MyApplications? =
        itemViewModel.myApplicationsModel
}