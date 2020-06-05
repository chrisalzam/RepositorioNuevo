package com.r2devpros.databindingtest

import android.util.Log
import android.util.SparseArray
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(), Observable {
    var title: String = ""

    var name: String = ""
    var lastName: String = ""

    @Bindable
    var age: String = ""
        set(value) {
            field = value

            notifyPropertyChanged(BR.age)
            notifyChange()
        }

    val ageLessThan18: Boolean
        get() = (age.isNotEmpty() && age.toInt() < 18)

    val legalAge: Boolean
        get() = age.isNotEmpty() && age.toInt() >= 21

    val notLegalAge: Boolean
        get() = age.isNotEmpty() && age.toInt() < 21


    @get:Bindable
    var valuesFromServer: List<String> = emptyList()
        set(value) {
            field = value
            notifyPropertyChanged(BR.valuesFromServer)
        }


    //region Observable Implementation
    @Transient
    private var mCallbacks: PropertyChangeRegistry? = null

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        synchronized(this) {
            if (mCallbacks == null) {
                return
            }
        }
        mCallbacks?.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        synchronized(this) {
            if (mCallbacks == null) {
                mCallbacks = PropertyChangeRegistry()
            }
        }
        mCallbacks?.add(callback)
    }

    private fun notifyChange() {
        synchronized(this) {
            if (mCallbacks == null) {
                return
            }
        }
        mCallbacks?.notifyCallbacks(this, 0, null)
    }

    private val onChangeObservers = SparseArray<MutableList<() -> Unit>>()

    init {
        this.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (onChangeObservers[propertyId] != null) {
                    onChangeObservers[propertyId].forEach { it.invoke() }
                }
            }
        })
    }

    fun notifyPropertyChanged(fieldId: Int) {
        synchronized(this) {
            if (mCallbacks == null) {
                return
            }
        }
        mCallbacks?.notifyCallbacks(this, fieldId, null)
    }

    fun onPropertyChanged(propertyId: Int, action: () -> Unit) {
        if (onChangeObservers[propertyId] != null) {
            onChangeObservers[propertyId].add(action)
        } else {
            val observers = ArrayList<() -> Unit>()
            observers.add(action)
            onChangeObservers.put(propertyId, observers)
        }
    }
    //endregion

    private val formalName: String
        get() = "$name $lastName"

    fun getValuesFromButton() {
        Log.d(
            "MainViewModel_TAG",
            "getValuesFromButton: title: $title, name: $name, lastName: $lastName, age: $age"
        )
    }

    fun setValuesFromServer() {
        name = "Juan"
        lastName = "Perez"
        age = "18"

        notifyChange()

        Log.d(
            "MainViewModel_TAG",
            "setValuesFromServer: title: $title, name: $name, lastName: $lastName, age: $age"
        )

        Runnable {
            Thread.sleep(3000)
            valuesFromServer = listOf("19", "22", "33", "45")
        }.run()
    }
}