package com.example.appmonitor

import android.graphics.drawable.Drawable
import android.util.Log

class PackageInfo {
    var appname = ""
    var pname = ""
    var classname = ""
    var versionCode = 0
    var icon: Drawable? = null
    fun prettyPrint() {
        Log.d("Applist ", "APP NAME: $appname, PACKAGE NAME: $pname, CLASS NAME: $classname, VERSION CODE: $versionCode")
    }
}