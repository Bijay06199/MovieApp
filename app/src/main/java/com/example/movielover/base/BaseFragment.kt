package com.example.movielover.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.example.movielover.data.prefs.SharedPrefUtil
import com.example.movielover.utils.extension.setupUI
import com.example.movielover.utils.extension.showAlert
import com.example.movielover.utils.extension.showErrorAlert
import com.example.movielover.utils.extension.showToast
import com.example.movielover.utils.viewUtils.ProgressDialogHelper
import org.koin.android.ext.android.inject

abstract class BaseFragment<
        DATA_BINDING : ViewDataBinding, VIEW_MODEL : BaseViewModel> : Fragment() {

    protected lateinit var viewDataBinding: DATA_BINDING
    protected var mViewModel: VIEW_MODEL? = null
    private var progressDialog: Dialog? = null

    protected val prefManager: SharedPrefUtil by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        performDataBinding(inflater, container)
        progressDialog = ProgressDialogHelper.progressDialog(requireActivity())
        initObservers()
        return viewDataBinding.root
    }

    private fun performDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)

        this.mViewModel = mViewModel ?: getViewModel()

        viewDataBinding.apply {
            setVariable(getBindingVariable(), mViewModel)
            lifecycleOwner = viewLifecycleOwner
            executePendingBindings()
        }
        activity?.setupUI(viewDataBinding.root)
    }

    private fun initObservers() {
        mViewModel?.apply {
            alertDialogEvent.observe(viewLifecycleOwner) { alertDialogEvent ->
                alertDialogEvent?.let {
                    requireActivity().showAlert(it)
                }
            }
            errorAlertDialogEvent.observe(viewLifecycleOwner) { errorMessage ->
                errorMessage?.let {
                    requireActivity().showErrorAlert(it)
                }
            }
            showToastEvent.observe(viewLifecycleOwner) { showToastEvent ->
                showToastEvent?.let {
                    context?.showToast(it)
                }
            }
            showLoading.observe(viewLifecycleOwner) { showLoading ->
                if (showLoading)
                    showLoading()
                else
                    hideLoading()
            }
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
    abstract fun getBindingVariable(): Int




}