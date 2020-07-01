package com.example.cardviewpanelapp.presentation.recyclerview

import com.example.cardviewpanelapp.model.MyApplications
import com.example.cardviewpanelapp.presentation.base.BaseViewModel

class MyApplicationsItemViewModel : BaseViewModel() {
    var     myApplicationsModel: MyApplications? = null

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