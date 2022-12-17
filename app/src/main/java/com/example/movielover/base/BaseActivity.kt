package com.example.movielover.base

import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.observe
import com.example.movielover.BR
import com.example.movielover.data.prefs.SharedPrefUtil
import com.example.movielover.utils.extension.*
import com.example.movielover.utils.viewUtils.ProgressDialogHelper
import org.koin.android.ext.android.inject

abstract class BaseActivity<
        DATA_BINDING : ViewDataBinding,
        VIEW_MODEL : BaseViewModel> :
    AppCompatActivity() {

    protected lateinit var viewDataBinding: DATA_BINDING
    protected var mViewModel: VIEW_MODEL? = null
    private var progressDialog: Dialog? = null
    protected val prefManager: SharedPrefUtil by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutId())
        progressDialog = ProgressDialogHelper.progressDialog(this)
        performDataBinding()
        initObservers()
        setupUI(viewDataBinding.root)

    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.mViewModel = mViewModel ?: getViewModel()
        viewDataBinding.apply {
            setVariable(getBindingVariable(), mViewModel)
            lifecycleOwner = this@BaseActivity
            executePendingBindings()
        }

    }

    private fun initObservers() {
        mViewModel?.apply {
            alertDialogEvent.observe(this@BaseActivity, { dialogMessage ->
                dialogMessage?.let {
                    this@BaseActivity.showAlert(it)
                }
            })
            errorAlertDialogEvent.observe(this@BaseActivity, { errorMessage ->
                errorMessage?.let {
                    this@BaseActivity.showErrorAlert(it)
                }
            })

            validationAlertDialogEvent.observe(this@BaseActivity, { errorMessage ->
                errorMessage?.let {
                    this@BaseActivity.showValidationAlert(it)
                }
            })
            showToastEvent.observe(this@BaseActivity) { toastMessage ->
                toastMessage?.let {
                    this@BaseActivity.showToast(it)
                }
            }

            showLoading.observe(this@BaseActivity, { showLoading ->
                if (showLoading) showLoading()
                else hideLoading()
            })
        }
    }

    fun hideLoading() {
        progressDialog?.dismiss()
    }

    fun showLoading() {
        progressDialog?.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        progressDialog?.dismiss()
    }

    override fun onPause() {
        super.onPause()
        progressDialog?.dismiss()
    }

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * Override for set view model
     * @return view model instance
     */
    abstract fun getViewModel(): VIEW_MODEL

    /**
     * Override for set binding variable
     * @return variable id
     */
    open fun getBindingVariable(): Int = BR.viewModel

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private var refreshTokenFailedReceiver: BroadcastReceiver? = null

    override fun onResume() {
        if (refreshTokenFailedReceiver == null) {
            refreshTokenFailedReceiver = RefreshTokenFailedReceiver()
            registerReceiver(refreshTokenFailedReceiver,
                IntentFilter().apply {
                })
        }
        super.onResume()
    }

    private inner class RefreshTokenFailedReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
                when (it.action) {

                }
            }
        }
    }


    fun logOut() {

        finishAffinity()
    }
}