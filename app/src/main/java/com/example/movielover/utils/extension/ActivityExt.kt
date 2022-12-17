package com.example.movielover.utils.extension

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.movielover.R
import com.example.movielover.utils.constants.AppContracts
import com.google.android.material.internal.CheckableImageButton
import com.google.android.material.textfield.TextInputEditText

fun Context.showAlert(message: String?) {
    ResourcesCompat.getFont(this, R.font.arial)
    AlertDialog.Builder(this)
        .setMessage(message)
        .setPositiveButton(AppContracts.Strings.ok) { dialog, _ ->
            dialog.dismiss()
        }
        .show()
}

//
fun Context.showErrorAlert(message: String) {
    AlertDialog.Builder(this)
        .setTitle(getString(R.string.error))
        .setPositiveButton(AppContracts.Strings.ok) { dialog, _ ->
            dialog.dismiss()
        }
        .setMessage(message)
        .show()
}

fun Context.showValidationAlert(message: String) {
    AlertDialog.Builder(this)
        .setTitle(getString(R.string.validation))
        .setPositiveButton(AppContracts.Strings.ok) { dialog, _ ->
            dialog.dismiss()
        }
        .setMessage(message)
        .show()
}

fun Context.showCustomDialog(
    title: String,
    message: String,
    positiveButtonName: String,
    positiveBtnClickListener: (() -> Unit)? = null
) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveButtonName) { dialog, _ ->
            positiveBtnClickListener?.invoke()
            dialog.dismiss()
        }.
        show()
}

//
fun Context.showCustomOptionDialog(
    title: String = getString(R.string.app_name),
    message: String,
    positiveButtonName: String = getString(R.string.ok),
    positiveBtnClickListener: (() -> Unit)? = null,
    negativeButtonName: String = getString(R.string.cancel),
    negativeBtnClickListener: (() -> Unit)? = null
) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveButtonName) { dialog, _ ->
            positiveBtnClickListener?.invoke()
            dialog.dismiss()
        }
        .setNegativeButton(negativeButtonName) { dialog, _ ->
            negativeBtnClickListener?.invoke()
            dialog.dismiss()
        }
        .show()
}

fun Context.showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}

fun Context.showToast(message: Int, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, length).show()
}

fun Fragment.showToast(message: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(requireContext(), message, length).show()
}

/**
 * Method for removing the keyboard if touched outside the editable view.
 *
 * @param view     root view
 */
@SuppressLint("ClickableViewAccessibility")
fun FragmentActivity.setupUI(view: View) {
    //Set up touch listener for non-text box views to hide keyboard.
    if (view !is EditText && view !is TextInputEditText && view !is CheckableImageButton) {

        view.setOnTouchListener { _, _ ->
            hideSoftKeyboard()
            view.clearFocus()
            false
        }
    }

    //If a layout container, iterate over children and seed recursion.
    if (view is ViewGroup) {
        for (i in 0 until view.childCount) {
            val innerView = view.getChildAt(i)
            setupUI(innerView)
        }
    }
}

fun FragmentActivity.hideSoftKeyboard() {
    // Check if no view has focus: and hide the soft keyboard
    val view = currentFocus
    //Checking if view!=null
    // to prevent java.lang.NullPointerException: Attempt to invoke virtual method 'android.os.IBinder android.view.View.getWindowToken()' on a null object reference
    view?.let {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun EditText.showSoftKeyboard() {
    postDelayed({
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }, 100)
}

fun EditText.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)

}