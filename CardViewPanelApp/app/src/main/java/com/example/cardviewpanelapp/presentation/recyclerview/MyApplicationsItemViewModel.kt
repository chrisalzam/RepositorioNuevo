package com.example.cardviewpanelapp.presentation.recyclerview

import com.example.cardviewpanelapp.model.Application
import com.example.cardviewpanelapp.presentation.base.BaseViewModel

class MyApplicationsItemViewModel : BaseViewModel() {
    var     myApplicationsModel: Application? = null

    val appId: String?
        get() = myApplicationsModel?.appId

    val name: String?
        get() = myApplicationsModel?.name

    val deepLink: String?
        get() = myApplicationsModel?.deepLink

    val iconUrl: String?
        get() = myApplicationsModel?.iconUrl

    val iconResourceName: String?
        get() = myApplicationsModel?.iconResourceName

    val adminApp: Boolean?
        get() = myApplicationsModel?.adminApp
}