package com.example.cardviewpanelapp.presentation.home

import com.example.cardviewpanelapp.model.Application
import com.example.cardviewpanelapp.presentation.base.BaseViewModel

class MyApplicationsItemViewModel : BaseViewModel() {
    var myApplicationsModel: Application? = null

    val name: String?
        get() = myApplicationsModel?.name

    val iconUrl: String?
        get() = myApplicationsModel?.iconUrl

    val iconResourceName: String?
        get() = myApplicationsModel?.iconResourceName
}