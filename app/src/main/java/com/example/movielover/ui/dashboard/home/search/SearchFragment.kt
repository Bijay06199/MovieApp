package com.example.movielover.ui.dashboard.home.search

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.SearchView
import androidx.lifecycle.Observer
import com.example.movielover.BR
import com.example.movielover.R
import com.example.movielover.base.BaseFragment
import com.example.movielover.databinding.FragmentSearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : BaseFragment<FragmentSearchBinding,SearchViewModel>(),SearchAdapter.OnItemClicklistener {

    override fun getLayoutId(): Int =R.layout.fragment_search

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): SearchViewModel {
        val viewModel:SearchViewModel by viewModel()
        return viewModel
    }
    lateinit var searchAdapter: SearchAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initRecycleView()
    }

    private fun initRecycleView() {
        with(viewDataBinding){
            with(mViewModel!!){
                searchResponse.observe(viewLifecycleOwner, Observer {

                    searchAdapter = SearchAdapter(it.results,requireContext(),this@SearchFragment)
                    searchListRV.adapter = searchAdapter
                })
            }
        }
    }

    private fun initViews() {

        with(viewDataBinding){






            with(mViewModel!!){
                searchbar.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        getSearchMovies(query!!)

                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {

                        getSearchMovies(newText!!)
                        return false
                    }
                })

            }
        }
    }

    override fun itemClicked(position: Int, title: String, imageString: String, overview: String) {

    }

}