package com.example.movielover.base

import androidx.lifecycle.ViewModel
import com.example.movielover.utils.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {

    val alertDialogEvent = SingleLiveEvent<String>()
    val errorAlertDialogEvent = SingleLiveEvent<String>()
    val validationAlertDialogEvent = SingleLiveEvent<String>()
    val showToastEvent = SingleLiveEvent<String>()
    val showLoading = SingleLiveEvent<Boolean>()


    fun showToast(message: String) {
        showToastEvent.postValue(message)
    }

    fun showAlertDialog(message: String?) {
        alertDialogEvent.postValue(message!!)
    }

    fun showErrorDialog(message: String) {
        errorAlertDialogEvent.postValue(message)
    }
    fun showValidationDialog(message: String) {
        validationAlertDialogEvent.postValue(message)
    }

    fun showLoading(status: Boolean) {
        showLoading.postValue(status)

    }
}